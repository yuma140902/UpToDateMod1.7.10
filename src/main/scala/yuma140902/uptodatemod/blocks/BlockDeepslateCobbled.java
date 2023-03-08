package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;

public class BlockDeepslateCobbled extends Block implements IRegisterable {

    public BlockDeepslateCobbled() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(3.5F);
        this.setResistance(10.0F);
        this.setStepSound(BlockDeepslate.soundTypeDeepslate);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("cobbled_deepslate"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("cobbled_deepslate"));
        GameRegistry.registerBlock(this, "cobbled_deepslate");

        OreDictionary.registerOre("stoneCobbledDeepslate", this);
        OreDictionary.registerOre("cobblestone", this);
    }
}
