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

public class BlockDeepslateLapisOre extends BlockDeepslateOreBase implements IHasRecipes, IRegisterable {
    public BlockDeepslateLapisOre() {
        super(1);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Items.dye;
    }

    public int quantityDropped(Random random)
    {
        return 4 + random.nextInt(5);
    }


    private Random rand = new Random();
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
        return MathHelper.getRandomIntegerInRange(rand, 2, 5);
    }

    @Override
    public int damageDropped(int p_149692_1_) {
        return 4;
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(this, new ItemStack(Items.dye, 1, 4), McConst.EXP_GEM);
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_lapis_ore"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate_lapis_ore"));
        GameRegistry.registerBlock(this, "deepslate_lapis_ore");

        OreDictionary.registerOre("oreLapis", this);
    }
}
