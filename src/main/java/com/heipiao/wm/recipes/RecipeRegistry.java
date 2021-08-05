package com.heipiao.wm.recipes;

import com.heipiao.wm.Utils;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Utils.modid);
    public static final RegistryObject<RecipeSerializer<I2FRecipe>> WITHER_EXTRACTOR = RECIPES.register("wither_extractor",
        ()->new I2FRecipe.Serializer()
    );
    public static final RecipeType<I2FRecipe> WITHER_EXTRACTOR_TYPE = RecipeType.register("wither_extractor");
}
