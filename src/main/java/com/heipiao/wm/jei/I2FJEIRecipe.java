// package com.heipiao.wm.jei;

// import java.util.List;

// import com.heipiao.wm.recipes.I2FRecipe;

// import mezz.jei.api.constants.VanillaTypes;
// import mezz.jei.api.gui.IRecipeLayout;
// import mezz.jei.api.gui.drawable.IDrawable;
// import mezz.jei.api.helpers.IJeiHelpers;
// import mezz.jei.api.ingredients.IIngredients;
// import mezz.jei.api.recipe.category.IRecipeCategory;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
// import net.minecraft.util.ResourceLocation;
// import net.minecraft.util.text.ITextComponent;
// import net.minecraft.util.text.StringTextComponent;
// import net.minecraft.util.text.TranslationTextComponent;

// public class I2FJEIRecipe implements IRecipeCategory<I2FRecipe>{
//     private ResourceLocation id;
//     private IJeiHelpers helpers;
//     private final IDrawable background;
//     private final IDrawable icon;
//     public I2FJEIRecipe(ResourceLocation id, IJeiHelpers helpers, Item icon){
//         this.id=id;
//         this.helpers=helpers;
//         this.icon=this.helpers.getGuiHelper().createDrawableIngredient(new ItemStack(icon));
//         this.background=this.helpers.getGuiHelper().createBlankDrawable(128, 16);
//     }
//     @Override
//     public ResourceLocation getUid(){
//         return id;
//     }
//     @Override
//     public Class<? extends I2FRecipe> getRecipeClass(){
//         return I2FRecipe.class;
//     }
//     @Override
//     public String getTitle(){
//         String res=new StringBuilder()
//                     .append("recipes.")
//                     .append(id.getNamespace())
//                     .append(".")
//                     .append(id.getPath())
//                     .toString();
//         return res;
//     }
//     @Override
//     public IDrawable getBackground(){
//         return this.background;
//     }
//     @Override
//     public IDrawable getIcon(){
//         return this.icon;
//     }
//     @Override
//     public void setIngredients(I2FRecipe recipe, IIngredients ingredients){
//         System.out.println(recipe.getInputItem());
//         ingredients.setInput(VanillaTypes.ITEM, recipe.getInputItem());
//         ingredients.setOutput(VanillaTypes.FLUID, recipe.getOutputFluid());
//     }
//     @Override
//     public void setRecipe(IRecipeLayout recipeLayout, I2FRecipe recipe, IIngredients ingredients){
//         recipeLayout.getFluidStacks().init(0, false, 110, 0);
//         recipeLayout.getFluidStacks().set(0, recipe.getOutputFluid());
//         recipeLayout.getItemStacks().init(0, true, 0, -1);
//         recipeLayout.getItemStacks().set(0, recipe.getInputItem());
//     }
//     @Override
//     public List<ITextComponent> getTooltipStrings(I2FRecipe recipe, double mouseX, double mouseY) {
//         return List.of(new StringTextComponent(Integer.toString(recipe.getOutputFluid().getAmount())).appendSibling(new TranslationTextComponent("fluidamount.mb")));
//     }
// }
