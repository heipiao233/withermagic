package com.heipiao.wm;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.heipiao.wm.blocks.Blocks;
import com.heipiao.wm.items.Items;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Utils.modid)
public class WM
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

    public WM() {
        LOGGER.info("loaded!");
        LOGGER.info("Registering blocks!");
        Blocks.BLOCKS.register(bus);
        Items.ITEMS.register(bus);
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
    }

}
