package com.heipiao.wm.container;

import net.minecraftforge.registries.ForgeRegistries;
import com.heipiao.wm.Utils;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ContainerTypeRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.modid);
    public static final RegistryObject<MenuType<WitherExtractorContainer>> witherExtractorContainer = CONTAINERS.register("wither_extractor", ()->
        IForgeContainerType.create((int windowId, Inventory inv, FriendlyByteBuf buffer)->new WitherExtractorContainer(windowId, inv, buffer.readBlockPos(), inv.player.level, new SimpleContainerData(1)))
    );
}
