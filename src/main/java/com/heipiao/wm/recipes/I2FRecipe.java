package com.heipiao.wm.recipes;

import java.util.Set;
import java.util.stream.Stream;
import com.google.gson.JsonObject;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
public class I2FRecipe implements Recipe<Container>{
    private final ItemStack inputItem;
    private final FluidStack outputFluid;
    private final ResourceLocation id;
    private I2FRecipe(ResourceLocation recipeId, JsonObject json) {
        // id=new ResourceLocation(json.get("id").getAsString());
        id = recipeId;
        ResourceLocation itemId = new ResourceLocation(json.getAsJsonObject("input").get("id").getAsString());
        int itemCount = json.getAsJsonObject("input").get("count").getAsInt();
        inputItem=new ItemStack(ForgeRegistries.ITEMS.getValue(itemId),itemCount);
        ResourceLocation fluidId = new ResourceLocation(json.getAsJsonObject("output").get("id").getAsString());
        int fluidAmount = json.getAsJsonObject("output").get("amount").getAsInt();
        outputFluid=new FluidStack(ForgeRegistries.FLUIDS.getValue(fluidId),fluidAmount);
    }
    public I2FRecipe(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        outputFluid=buffer.readFluidStack();
        inputItem=buffer.readItem();
        id=buffer.readResourceLocation();
    }
    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.withSize(1, new Ingredient(Stream.empty()){
            @Override
            public boolean test(ItemStack stack) {
                return stack.equals(inputItem,true);
            }
        });
    }
    @Override
    public boolean matches(Container inv, Level worldIn) {
        return inv.hasAnyOf(Set.of(inputItem.getItem()));
    }
    @Override
    public ItemStack getResultItem(){
        return ItemStack.EMPTY;
    }
    @Override
    public boolean canCraftInDimensions(int width, int height){
        return true;
    }
    @Override
    public ResourceLocation getId() {
        return this.id;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.WITHER_EXTRACTOR.get();
    }
    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.WITHER_EXTRACTOR_TYPE;
    }
    public FluidStack getOutputFluid() {
        return outputFluid;
    }
    public ItemStack getInputItem() {
        return inputItem;
    }
    @Override
    public ItemStack assemble(Container p_44001_) {
        return ItemStack.EMPTY.copy();
    }
    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<I2FRecipe>{
        @Override
        public I2FRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            return new I2FRecipe(recipeId, json);
        }
        @Override
        public I2FRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            return new I2FRecipe(recipeId, buffer);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buffer, I2FRecipe recipe) {
            buffer.writeFluidStack(recipe.getOutputFluid());
            buffer.writeItem(recipe.getInputItem());
            buffer.writeResourceLocation(recipe.getId());
        }
    }
}
