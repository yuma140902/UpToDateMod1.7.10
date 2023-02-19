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

public class BlockDeepslateCoalOre extends BlockDeepslateOreBase implements IHasRecipes, IRegisterable {
    public BlockDeepslateCoalOre() {
        super(0);
    }

    @Override
    public Item getItemDropped(int metadata, Random p_149650_2_, int fortune)
    {
        return Items.coal;
    }

    private final Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
        return MathHelper.getRandomIntegerInRange(rand, 0, 2);
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(this, new ItemStack(Items.coal), McConst.EXP_GEM);
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_coal_ore"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate_coal_ore"));
        GameRegistry.registerBlock(this, "deepslate_coal_ore");

        OreDictionary.registerOre("oreCoal", this);
    }
}
