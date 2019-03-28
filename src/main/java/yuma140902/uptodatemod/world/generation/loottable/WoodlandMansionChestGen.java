package yuma140902.uptodatemod.world.generation.loottable;

import java.util.Random;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class WoodlandMansionChestGen {
	private WoodlandMansionChestGen() {
		/*
		ChestGenHooks loottable = ChestGenHooks.getInfo(CATEGORY_NAME);
		loottable.setMin(8);
		loottable.setMax(8); //よくわからない
		for(WeightedRandomChestContent content : genChestContent()) {
			loottable.addItem(content);
		}
		*/
	}
	
	public static final WoodlandMansionChestGen instance = new WoodlandMansionChestGen();
	
	//public static final String CATEGORY_NAME = ModUpToDateMod.MOD_ID + ".woodlandMansionChest";
	
	/*private List<WeightedRandomChestContent> genChestContent(){ // TODO バランス、本がエンチャントされない
		List<WeightedRandomChestContent> list = new LinkedList<>();
		float bias1 = 1.0F;
		list.add(new WeightedRandomChestContent(Items.lead, 0, 1, 3, (int)(10*bias1)));
		list.add(new WeightedRandomChestContent(Items.golden_apple, 0, 1, 3, (int)(10*bias1)));
		list.add(new WeightedRandomChestContent(Items.golden_apple, 1, 1, 3, (int)(2*bias1)));
		list.add(new WeightedRandomChestContent(Items.record_13, 0, 1, 1, (int)(10*bias1)));
		list.add(new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, (int)(10*bias1)));
		list.add(new WeightedRandomChestContent(Items.name_tag, 0, 1, 3, (int)(10*bias1)));
		list.add(new WeightedRandomChestContent(Items.chainmail_chestplate, 0, 1, 1, (int)(5*bias1)));
		list.add(new WeightedRandomChestContent(Items.diamond_hoe, 0, 1, 1, (int)(5*bias1)));
		list.add(new WeightedRandomChestContent(Items.diamond_chestplate, 0, 1, 1, (int)(5*bias1)));
		list.add(new WeightedRandomChestContent(Items.book, 0, 1, 3, (int)(10*bias1))); // ランダムにエンチャントされるはず
		
		float bias2 = 1.0F;
		list.add(new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 4, (int)(5*bias2)));
		list.add(new WeightedRandomChestContent(Items.bread, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.wheat, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.bucket, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.redstone, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.coal, 0, 1, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, (int)(10*bias2)));
		//list.add(new WeightedRandomChestContent(MyItems.beetrootSeeds, 0, 2, 4, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.bone, 0, 1, 8, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.gunpowder, 0, 1, 8, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.rotten_flesh, 0, 1, 8, (int)(10*bias2)));
		list.add(new WeightedRandomChestContent(Items.string, 0, 1, 8, (int)(10*bias2)));
		
		return list;
	}*/
	
	public TileEntityChest setLoot(TileEntityChest tileentity, Random random) {
		ChestGenHooks loottable = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
		WeightedRandomChestContent.generateChestContents(random, loottable.getItems(random), tileentity, loottable.getCount(random));
		
		return tileentity;
	}
}
