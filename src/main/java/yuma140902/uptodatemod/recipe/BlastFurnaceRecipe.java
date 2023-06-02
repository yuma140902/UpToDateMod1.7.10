package yuma140902.uptodatemod.recipe;

import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

public class BlastFurnaceRecipe {
	@Nonnull
	private ItemStack material;
	@Nonnull
	private ItemStack product;
	private float experience;
	
	public BlastFurnaceRecipe(@Nonnull ItemStack material, @Nonnull ItemStack product, float experience) {
		this.material = material;
		this.product = product;
		this.experience = experience;
	}
	
	public boolean matches(ItemStack material) {
		return ItemStackComparer.areItemStacksEqual(this.material, material);
	}
	
	@Nonnull
	public ItemStack getProduct() {
		return product;
	}
	
	@Nonnull
	public ItemStack getMaterial() {
		return material;
	}
	
	public float getExperience() {
		return experience;
	}
}
