package yuma140902.uptodatemod.recipes;

import javax.annotation.Nullable;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.api.recipes.IStonecutterRecipe;
import yuma140902.uptodatemod.util.ItemStackUtil;

public class StonecutterRecipe implements IStonecutterRecipe {

	private ItemStack material;
	private int materialNum;
	private ItemStack product;
	private int productNum;
	private IStonecutterRecipe reverse = null;
	
	public StonecutterRecipe(ItemStack material, int materialNum, ItemStack product, int productNum) {
		this.material = material;
		this.materialNum = materialNum;
		this.product = product;
		this.productNum = productNum;
	}
	
	@Override
	public ItemStack getProduct() {
		return this.product;
	}
	
	@Override
	public int getProductNum() {
		return productNum;
	}
	
	@Override
	public ItemStack getMaterial() {
		return this.material;
	}
	
	@Override
	public int getMaterialNum() {
		return materialNum;
	}
	
	@Override
	public boolean matches(ItemStack materialCand) {
		return ItemStackUtil.equalsItemMeta(materialCand, this.material);
	}
	
	@Override
	@Nullable
	public ItemStack getCraftingResult(ItemStack material) {
		if(!matches(material)) return null;
		
		ItemStack product = this.product.copy();
		
		if(material.stackSize >= materialNum) {
			product.stackSize = productNum;
			material.stackSize -= materialNum;
			return product;
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean hasReverseRecipe() {
		return true;
	}
	
	@Override
	public IStonecutterRecipe getReverseRecipe() {
		if(reverse == null) {
			reverse = new StonecutterRecipe(product, productNum, material, materialNum);
		}
		return reverse;
	}
	
}
