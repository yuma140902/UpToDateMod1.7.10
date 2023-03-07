package yuma140902.uptodatemod.blocks;

import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;

public abstract class BlockDeepslateOreBase extends BlockOre {
    /**
     * @param harvestLevel Harvest level: Wood: 0 Stone: 1 Iron: 2 Diamond: 3 Gold: 0
     */
    public BlockDeepslateOreBase(int harvestLevel) {
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(4.5F);
        this.setResistance(10.0F);
        this.setStepSound(BlockDeepslate.soundTypeDeepslate);
        this.setHarvestLevel("pickaxe", harvestLevel);
    }
}
