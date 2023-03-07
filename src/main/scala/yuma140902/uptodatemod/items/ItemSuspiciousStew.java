package yuma140902.uptodatemod.items;

import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockNewFlower;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.ModYumaLib;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.items.ItemFoodMultiMeta;
import yuma140902.yumalib.api.util.Name;
import yuma140902.yumalib.api.util.ScalaCompat;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

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
    // { potionId, duration, amplifier }
    @Nonnull
    public static final int[][] potionInfos = {
            {Potion.nightVision.id, 5 * SEC, 0}, //ポピー
            {Potion.field_76443_y.id, 10/* 0.3sec */, 0}, //saturation, 満腹度回復; ヒスイラン
            {Potion.fireResistance.id, 3 * SEC, 0}, //アリウム(レンゲソウ)
            {Potion.blindness.id, 7 * SEC, 0}, //ヒナソウ
            {Potion.field_76443_y.id, 10/* 0.3sec */, 0}, //saturation, 満腹度回復; タンポポ
            {Potion.poison.id, 11 * SEC, 0}, //スズラン
            {Potion.jump.id, 5 * SEC, 0}, //ヤグルマギク
            {Potion.weakness.id, 8 * SEC, 0}, //チューリップ
            {Potion.regeneration.id, 7 * SEC, 0}, //フランスギク
            {Potion.wither.id, 7 * SEC, 0}, //ウィザーローズ
    };
    private static final int
            POTION = 0, DURATION = 1, AMPLIFIER = 2;


    public ItemSuspiciousStew() {
        super(6, 0.6f, new Name("suspicious_stew"), flowerNames, ScalaCompat.array(StringUtil.name.domainedTextures_(ScalaCompat.seq(new String[]{"suspicious_stew"})), new String[]{}));
        this.setAlwaysEdible();
        this.setMaxStackSize(1);
    }

    @Override
    public void registerRecipes() {
        redFlower(0, McConst.Meta$.MODULE$.REDFLOWER_POPPY());
        redFlower(1, McConst.Meta$.MODULE$.REDFLOWER_BLUE_ORCHID());
        redFlower(2, McConst.Meta$.MODULE$.REDFLOWER_ALLIUM());
        redFlower(3, McConst.Meta$.MODULE$.REDFLOWER_HOUSTONIA());
        addStewRecipe(4, new ItemStack(Blocks.yellow_flower));
        addStewRecipe(5, new ItemStack(MyBlocks.flower, 1, BlockNewFlower.LILY));
        addStewRecipe(6, new ItemStack(MyBlocks.flower, 1, BlockNewFlower.CORNFLOWER));
        redFlower(7, McConst.Meta$.MODULE$.REDFLOWER_RED_TULIP());
        redFlower(7, McConst.Meta$.MODULE$.REDFLOWER_WHITE_TULIP());
        redFlower(7, McConst.Meta$.MODULE$.REDFLOWER_PINK_TULIP());
        redFlower(7, McConst.Meta$.MODULE$.REDFLOWER_ORANGE_TULIP());
        redFlower(8, McConst.Meta$.MODULE$.REDFLOWER_OXEYE_DAISY());
        addStewRecipe(9, new ItemStack(MyBlocks.witherRose));

        if (ModYumaLib.proxy.isYuma140902()) {
            ItemStack goldenStew = new ItemStack(this);
            NBT.write(goldenStew, Potion.digSpeed.id, 50 * SEC, 19);
            NBT.write(goldenStew, Potion.jump.id, 50 * SEC, 4);
            NBT.write(goldenStew, Potion.moveSpeed.id, 50 * SEC, 5);
            NBT.write(goldenStew, Potion.regeneration.id, 50 * SEC, 9);
            NBT.write(goldenStew, Potion.heal.id, 50 * SEC, 9);
            NBT.write(goldenStew, Potion.invisibility.id, 50 * SEC, 0);
            NBT.write(goldenStew, Potion.fireResistance.id, 50 * SEC, 0);
            NBT.write(goldenStew, Potion.damageBoost.id, 50 * SEC, 9);
            NBT.write(goldenStew, Potion.nightVision.id, 50 * SEC, 0);
            NBT.write(goldenStew, Potion.waterBreathing.id, 50 * SEC, 0);
            NBT.write(goldenStew, Potion.field_76443_y.id, 50 * SEC, 9);
            NBT.write(goldenStew, Potion.field_76434_w.id, 50 * SEC, 29);
            NBT.write(goldenStew, Potion.field_76444_x.id, 50 * SEC, 19);
            assert goldenStew.getTagCompound() != null;
            goldenStew.getTagCompound().setTag("ench", new NBTTagList());  // エンチャントのエフェクトをつける
            ItemStack notchApple = new ItemStack(Items.golden_apple, 1, 1);
            RecipeRegister.addShaped(goldenStew,
                    "AAA",
                    "ASA",
                    "AAA",
                    'A', notchApple,
                    'S', Items.mushroom_stew);
        }
    }

    private void redFlower(int stewId, int flowerMeta) {
        addStewRecipe(stewId, new ItemStack(Blocks.red_flower, 1, flowerMeta));
    }

    private void addStewRecipe(int stewId, ItemStack flower) {
        RecipeRegister.addShapeless(stew(stewId), Items.bowl, Blocks.brown_mushroom, Blocks.red_mushroom, flower);
    }

    private ItemStack stew(int stewId) {
        ItemStack stew = new ItemStack(this);
        int[] potionInfo = potionInfos[stewId];
        int potionId = potionInfo[POTION];
        int duration = potionInfo[DURATION];
        int amplifier = potionInfo[AMPLIFIER];
        NBT.write(stew, potionId, duration, amplifier);
        return stew;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        // アイテム名はmeta値に関わらず「怪しげなシチュー」で統一する
        // そのためitemstackを無視
        return super.getUnlocalizedName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < potionInfos.length; ++i) {
            list.add(stew(i));
        }
    }


    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player) {
        super.onEaten(itemstack, world, player);
        return new ItemStack(Items.bowl);
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            PotionEffect[] effects = NBT.getPotionEffects(itemstack);
            for (final PotionEffect effect : effects) {
                player.addPotionEffect(effect);
            }
        }
    }


    public static class NBT {

        private final static String EFFECTS = "Effects";
        private final static String EFFECT_ID = "EffectId";
        private final static String EFFECT_DURATION = "EffectDuration";
        private final static String EFFECT_AMPLIFIER = "EffectAmplifier";
        private final static int NBTTAG_COMPOUND = new NBTTagCompound().getId();

        /**
         * @return NBTが書き込まれたItemStack。引数のitemstackと同じもの
         */
        private static ItemStack write(ItemStack itemstack, int potionId, int duration, int amplifier) {
            if (itemstack == null) return null;
            NBTTagCompound tag = itemstack.getTagCompound();
            if (tag == null) tag = new NBTTagCompound();
            NBTTagList effects = tag.getTagList(EFFECTS, NBTTAG_COMPOUND);
            if (effects == null) effects = new NBTTagList();

            NBTTagCompound effectTag = new NBTTagCompound();
            effectTag.setInteger(EFFECT_ID, potionId);
            effectTag.setInteger(EFFECT_DURATION, duration);
            effectTag.setInteger(EFFECT_AMPLIFIER, amplifier);

            effects.appendTag(effectTag);
            tag.setTag(EFFECTS, effects);

            itemstack.setTagCompound(tag);
            return itemstack;
        }

        /**
         * 読み込んだPotionIdが妥当かどうかなどのチェックはしていないことに注意
         *
         * @return NBTタグがなかったらnull。PotionIdが妥当かどうかなどのチェックはしていない
         */
        private static PotionEffect[] read(ItemStack itemstack) {
            if (itemstack == null) return null;
            NBTTagCompound tag = itemstack.getTagCompound();
            if (tag == null) return null;
            NBTTagList effects = tag.getTagList(EFFECTS, NBTTAG_COMPOUND);
            if (effects == null) return null;

            PotionEffect[] potionEffects = new PotionEffect[effects.tagCount()];
            for (int i = 0; i < potionEffects.length; ++i) {
                NBTTagCompound effectTag = effects.getCompoundTagAt(i);
                int potionId = effectTag.getInteger(EFFECT_ID);
                int duration = effectTag.getInteger(EFFECT_DURATION);
                int amplifier = effectTag.getInteger(EFFECT_AMPLIFIER);
                PotionEffect potionEffect = new PotionEffect(potionId, duration, amplifier);
                potionEffects[i] = potionEffect;
            }

            return potionEffects;
        }

        public static PotionEffect[] getPotionEffects(ItemStack itemstack) {
            PotionEffect[] effects = read(itemstack);
            if (effects != null) return effects;

            // ここから下はメタデータでポーション効果を管理していた古い仕様に対応するためのもの
            int meta = itemstack.getItemDamage();
            if (0 <= meta && meta < potionInfos.length) {
                int[] potionInfo = potionInfos[meta];
                int potion = potionInfo[POTION];
                int duration = potionInfo[DURATION];
                int amplifier = potionInfo[AMPLIFIER];
                return new PotionEffect[]{new PotionEffect(potion, duration, amplifier)};
            }

            return new PotionEffect[0];
        }
    }
}
