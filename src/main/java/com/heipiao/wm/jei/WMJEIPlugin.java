// package com.heipiao.wm.jei;

// import java.util.List;

// import com.heipiao.wm.blocks.Blocks;
// import com.heipiao.wm.items.Items;
// import com.heipiao.wm.recipes.I2FRecipe;
// import com.heipiao.wm.recipes.RecipeRegistry;

// import mezz.jei.api.IModPlugin;
// import mezz.jei.api.JeiPlugin;
// import mezz.jei.api.registration.IRecipeCatalystRegistration;
// import mezz.jei.api.registration.IRecipeCategoryRegistration;
// import mezz.jei.api.registration.IRecipeRegistration;
// import net.minecraft.client.Minecraft;
// import net.minecraft.item.ItemStack;
// import net.minecraft.util.ResourceLocation;

// @JeiPlugin
// public class WMJEIPlugin implements IModPlugin{
//     public WMJEIPlugin(){}
//     @Override
//     public ResourceLocation getPluginUid() {
//         return new ResourceLocation("wm", "jeiplugin");
//     }
//     @Override
//     public void registerRecipes(IRecipeRegistration registration) {
//         List<I2FRecipe> recipes;
//         if(Minecraft.getInstance().world!=null)recipes=Minecraft.getInstance().world.getRecipeManager().getRecipesForType(RecipeRegistry.WITHER_EXTRACTOR_TYPE);
//         else recipes=List.of();
//         registration.addRecipes(recipes, RecipeRegistry.WITHER_EXTRACTOR.getId());
//     }
//     @Override
//     public void registerCategories(IRecipeCategoryRegistration registration) {
//         registration.addRecipeCategories(new I2FJEIRecipe(RecipeRegistry.WITHER_EXTRACTOR.getId(),registration.getJeiHelpers(), Items.WITHER_EXTRACTOR_ITEM.get()));
//     }
//     @Override
//     public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
//         registration.addRecipeCatalyst(new ItemStack(Blocks.WITHER_EXTRACTOR.get()), RecipeRegistry.WITHER_EXTRACTOR.getId());
//     }
// }
