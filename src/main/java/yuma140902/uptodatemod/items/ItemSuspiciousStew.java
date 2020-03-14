package yuma140902.uptodatemod.items;

import javax.annotation.Nonnull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import yuma140902.mcmodlib.api.IHasRecipes;
import yuma140902.mcmodlib.api.IRegisterable;
import yuma140902.mcmodlib.api.items.ItemFoodMultiMeta;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockNewFlower;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.Stat;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemSuspiciousStew extends ItemFoodMultiMeta implements IRegisterable, IHasRecipes {
	
	@Nonnull
	private static final String[] flowerNames = {
		"poppy", //ポピー
		"blue_orchid", //ヒスイラン
		"allium", //アリウム(レンゲソウ)
		"houstonia", //ヒナソウ
		"dandelion", //タンポポ
		"lily_of_the_valley", //スズラン
		"cornflower", //ヤグルマギク
		"tulip", //チューリップ
		"oxeye_daisy", //フランスギク
		"wither_rose", //ウィザーローズ
	};
	
	private static final int SEC = 30; // seconds to duration
	
	private static final int
		POTION = 0, DURATION = 1, AMPLIFIER = 2;
	// { potionId, duration, amplifier }
	@Nonnull
	public static final int[][] metaToPotionData = {
		{ Potion.nightVision.id, 			5*SEC, 						0 }, //ポピー
		{ Potion.field_76443_y.id, 		10/* 0.3sec */, 	0 }, //saturation, 満腹度回復; ヒスイラン
		{ Potion.fireResistance.id, 	3*SEC, 						0 }, //アリウム(レンゲソウ)
		{ Potion.blindness.id, 				7*SEC, 						0 }, //ヒナソウ
		{ Potion.field_76443_y.id, 		10/* 0.3sec */, 	0 }, //saturation, 満腹度回復; タンポポ
		{ Potion.poison.id, 						11*SEC, 					0 }, //スズラン
		{ Potion.jump.id, 							5*SEC, 						0 }, //ヤグルマギク
		{ Potion.weakness.id, 					8*SEC, 						0 }, //チューリップ
		{ Potion.regeneration.id, 			7*SEC, 						0 }, //フランスギク
		{ Potion.wither.id, 						7*SEC, 						0 }, //ウィザーローズ
	};
	
	public ItemSuspiciousStew() {
		super(6, 0.6f, "suspicious_stew", flowerNames, StringUtil.domainedTextureNames("suspicious_stew"));
		this.setAlwaysEdible();
		this.setMaxStackSize(1);
	}
	
	@Override
	public void registerRecipes() {
		redFlower(0, Stat.META_REDFLOWER_POPPY);
		redFlower(1, Stat.META_REDFLOWER_BLUE_ORCHID);
		redFlower(2, Stat.META_REDFLOWER_ALLIUM);
		redFlower(3, Stat.META_REDFLOWER_HOUSTONIA);
		addStewRecipe(4, new ItemStack(Blocks.yellow_flower));
		addStewRecipe(5, new ItemStack(MyBlocks.flower, 1, BlockNewFlower.LILY));
		addStewRecipe(6, new ItemStack(MyBlocks.flower, 1, BlockNewFlower.CORNFLOWER));
		redFlower(7, Stat.META_REDFLOWER_RED_TULIP);
		redFlower(7, Stat.META_REDFLOWER_WHITE_TULIP);
		redFlower(7, Stat.META_REDFLOWER_PINK_TULIP);
		redFlower(7, Stat.META_REDFLOWER_ORANGE_TULIP);
		redFlower(8, Stat.META_REDFLOWER_OXEYE_DAISY);
		addStewRecipe(9, new ItemStack(MyBlocks.witherRose));
	}
	
	private void redFlower(int stewMeta, int flowerMeta) {
		addStewRecipe(stewMeta, new ItemStack(Blocks.red_flower, 1, flowerMeta));
	}
	
	private void addStewRecipe(int stewMeta, ItemStack flower) {
		RecipeRegister.addShapeless(new ItemStack(this, 1, stewMeta), Items.bowl, Blocks.brown_mushroom, Blocks.red_mushroom, flower);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		// アイテム名はmeta値に関わらず「怪しげなシチュー」で統一する
		// そのためitemstackを無視
		return super.getUnlocalizedName();
	}
	
	
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
		super.onEaten(itemstack, world, player);
    return new ItemStack(Items.bowl);
	}
	
	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			int meta = itemstack.getItemDamage();
			int[] potionData = metaToPotionData[meta];
			int potion = potionData[POTION];
			int duration = potionData[DURATION];
			int amplifier = potionData[AMPLIFIER];
			player.addPotionEffect(new PotionEffect(potion, duration, amplifier));
		}
	}
}
