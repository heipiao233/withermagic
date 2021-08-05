package com.heipiao.wm.fluids;

import com.heipiao.wm.Utils;
import com.heipiao.wm.blocks.Blocks;
import com.heipiao.wm.items.Items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Fluids{
    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("block/water_flow");
    public static final DeferredRegister<Fluid> FLUIDS=DeferredRegister.create(ForgeRegistries.FLUIDS,Utils.modid);
    public static RegistryObject<FlowingFluid> WITHER_FLUID_SOURCE=FLUIDS.register("wither_fluid",()->new ForgeFlowingFluid.Source(Fluids.WITHER_FLUID_PROPERTIES));
    public static RegistryObject<FlowingFluid> WITHER_FLUID_FLOWING=FLUIDS.register("wither_fluid_flowing",()->new ForgeFlowingFluid.Flowing(Fluids.WITHER_FLUID_PROPERTIES));
    public static ForgeFlowingFluid.Properties WITHER_FLUID_PROPERTIES=new ForgeFlowingFluid.Properties(
        WITHER_FLUID_SOURCE,
        WITHER_FLUID_FLOWING,
        FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE).color(0xff333333).density(250).viscosity(4000)
    ).bucket(Items.WITHER_FLUID_BUCKET).block(Blocks.WITHER_FLUID_BLOCK);
}
