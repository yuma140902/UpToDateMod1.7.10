package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

public class BlockDeepslateGoldOre extends BlockDeepslateOreBase implements IHasRecipes, IRegisterable {
    public BlockDeepslateGoldOre() {
        super(2);
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(this, new ItemStack(Items.gold_ingot), McConst.EXP_IRON());
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_gold_ore"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate_gold_ore"));
        GameRegistry.registerBlock(this, "deepslate_gold_ore");

        OreDictionary.registerOre("oreGold", this);
    }
}
