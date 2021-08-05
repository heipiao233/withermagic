package com.heipiao.wm;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.heipiao.wm.blocks.Blocks;
import com.heipiao.wm.container.ContainerTypeRegistry;
import com.heipiao.wm.fluids.Fluids;
import com.heipiao.wm.items.Items;
import com.heipiao.wm.network.Networking;
import com.heipiao.wm.pollute.PolluteUtils;
import com.heipiao.wm.recipes.RecipeRegistry;
import com.heipiao.wm.screen.WitherExtractorScreen;
import com.heipiao.wm.tileentity.TileEntityTypeRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Utils.modid)
public class WM
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public WM() {
        LOGGER.info("WM loaded!");
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Blocks.BLOCKS.register(bus);
        Items.ITEMS.register(bus);
        ContainerTypeRegistry.CONTAINERS.register(bus);
        TileEntityTypeRegistry.TILE_ENTITIES.register(bus);
        RecipeRegistry.RECIPES.register(bus);
        Fluids.FLUIDS.register(bus);
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBusEventHandler {
        @SubscribeEvent
        public static void onClientSetupEvent(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ContainerTypeRegistry.witherExtractorContainer.get(), WitherExtractorScreen::new);
                ItemBlockRenderTypes.setRenderLayer(Fluids.WITHER_FLUID_SOURCE.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(Fluids.WITHER_FLUID_FLOWING.get(), RenderType.translucent());
            });
        }
        @SubscribeEvent
        public static void onCommonSetupEvent(FMLCommonSetupEvent event) {
            event.enqueueWork(Networking::register);
            DispenseItemBehavior behavior = new DefaultDispenseItemBehavior() {
                private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();
       
                /**
                 * Dispense the specified stack, play the dispense sound and spawn particles.
                 */
                public ItemStack execute(BlockSource source, ItemStack stack) {
                   BucketItem bucketitem = (BucketItem)stack.getItem();
                   BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                   Level world = source.getLevel();
                   if (bucketitem.emptyContents((Player)null, world, blockpos, (BlockHitResult)null)) {
                      bucketitem.checkExtraContent(null, world, stack, blockpos);
                      return new ItemStack(net.minecraft.world.item.Items.BUCKET);
                   } else {
                      return this.defaultBehaviour.dispense(source, stack);
                   }
                }
             };
            DispenserBlock.registerBehavior(Items.WITHER_FLUID_BUCKET.get(), behavior);
        }
    }
    @Mod.EventBusSubscriber()
    public static class GameEventHandler {
        @SubscribeEvent
        public static void onGetTooltip(ItemTooltipEvent event){
            if(event.getPlayer()==null)return;
            if(event.getItemStack().getItem()==Items.TUTORIAL.get()){
                if(PolluteUtils.get(event.getPlayer().level)>=90000){
                    event.getToolTip().add(new TranslatableComponent("item.wm.tutorial.whathided"));
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerJoin(EntityJoinWorldEvent event){
            if(event.getEntity() instanceof ServerPlayer){
                PolluteUtils.updateToClient(event.getWorld(), (ServerPlayer)event.getEntity());
            }
        }
    }
}
