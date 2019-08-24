package yuma140902.uptodatemod.registry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.api.recipes.IStonecutterRecipe;
import yuma140902.uptodatemod.api.register.IStonecutterRecipeRegistry;
import yuma140902.uptodatemod.recipes.StonecutterRecipe;
import yuma140902.uptodatemod.util.ItemStackUtil;

public class StonecutterRecipeRegistry implements IStonecutterRecipeRegistry {
	private StonecutterRecipeRegistry() {}
	
	@Nonnull
	public static IStonecutterRecipeRegistry INSTANCE = new StonecutterRecipeRegistry();
	
	@SuppressWarnings("null")
	@Nonnull
	private static Iterator<IStonecutterRecipe> emptyIterator = new ArrayList<IStonecutterRecipe>().iterator();
	
	private final ArrayList<IStonecutterRecipe> list = new ArrayList<>();
	private final Hashtable<Item, List<IStonecutterRecipe>> listByMaterial = new Hashtable<>();
	
	@Override
	public void addRecipe(IStonecutterRecipe recipe) {
		addSingleRecipe(recipe);
		if(recipe.hasReverseRecipe()) {
			addSingleRecipe(recipe.getReverseRecipe());
		}
	}
	
	@Override
	public void addRecipe(ItemStack material, int materialNum, ItemStack product, int productNum) {
		addRecipe(material, materialNum, product, productNum, true);
	}
	
	@Override
	public void addRecipe(ItemStack material, int materialNum, ItemStack product, int productNum, boolean addReverseRecipe) {
		IStonecutterRecipe recipe = createRecipeObj(material, materialNum, product, productNum);
		addSingleRecipe(recipe);
		if(addReverseRecipe && recipe.hasReverseRecipe()) {
			addSingleRecipe(recipe.getReverseRecipe());
		}
	}
	
	private void addSingleRecipe(IStonecutterRecipe recipe) {
		if(recipe == null) {
			return;
		}
		
		list.add(recipe);
		
		ItemStack material = recipe.getMaterial();
		if(material == null) 	return;
		Item materialItem = material.getItem();
		if(!listByMaterial.containsKey(materialItem)) {
			listByMaterial.put(materialItem, new ArrayList<IStonecutterRecipe>());
		}
		listByMaterial.get(materialItem).add(recipe);
	}
	
	@Override
	public IStonecutterRecipe createRecipeObj(ItemStack material, int materialNum, ItemStack product, int productNum) {
		return new StonecutterRecipe(material, materialNum, product, productNum);
	}
	
	@Override
	public void removeRecipe(IStonecutterRecipe recipe) {
		removeSingleRecipe(recipe);
		if(recipe.hasReverseRecipe()) {
			removeSingleRecipe(recipe.getReverseRecipe());
		}
	}
	
	private void removeSingleRecipe(final IStonecutterRecipe recipe) {
		if(recipe == null) {
			return;
		}
		list.removeIf(arg->areEqual(arg, recipe));
		
		ItemStack material = recipe.getMaterial();
		if(material == null) 	return;
		Item materialItem = material.getItem();
		if(!listByMaterial.containsKey(materialItem)) {
			return;
		}
		listByMaterial.get(materialItem).removeIf(arg->areEqual(arg, recipe));
	}
	
	@Override
	public void removeAll(ItemStack product) {
		if(product == null) {
			return;
		}
		
		ArrayList<IStonecutterRecipe> removeList = new ArrayList<>();
		
		for(IStonecutterRecipe recipe : list) {
			if(recipe.getProduct() == product) {
				removeList.add(recipe);
			}
		}
		
		for(IStonecutterRecipe recipe : list) {
			removeSingleRecipe(recipe);
		}
	}
	
	@Override
	public Iterator<IStonecutterRecipe> getRecipes(ItemStack material) {
		if(material == null) {
			return emptyIterator;
		}
		Item materialItem = material.getItem();
		List<IStonecutterRecipe> recipes = listByMaterial.get(materialItem);
		if(recipes == null) {
			return emptyIterator;
		}
		return recipes.stream().filter(recipe->recipe.matches(material)).iterator();
	}
	
	@Override
	public boolean areEqual(IStonecutterRecipe a, IStonecutterRecipe b) {
		return a.getMaterialNum() == b.getMaterialNum()
				&& a.getProductNum() == b.getProductNum()
				&& ItemStackUtil.equalsItemNbtMeta(a.getMaterial(), b.getMaterial())
				&& ItemStackUtil.equalsItemNbtMeta(a.getProduct(), b.getProduct());
	}
	
}
