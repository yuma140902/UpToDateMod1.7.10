package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

import java.util.Random;

public class BlockDeepslateEmeraldOre extends BlockDeepslateOreBase implements IHasRecipes, IRegisterable {
    public BlockDeepslateEmeraldOre() {
        super(2);
    }

    @Override
    public Item getItemDropped(int metadata, Random p_149650_2_, int fortune)
    {
        return Items.emerald;
    }

    private final Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(this, new ItemStack(Items.emerald), McConst.EXP_GEM());
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_emerald_ore"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate_emerald_ore"));
        GameRegistry.registerBlock(this, "deepslate_emerald_ore");

        OreDictionary.registerOre("oreEmerald", this);
    }
}
