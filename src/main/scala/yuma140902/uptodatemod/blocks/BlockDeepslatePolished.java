package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.items.ItemBlockDeepslatePolished;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.blocks.CustomSoundTypeWithPlaceSound;

import java.util.List;

public class BlockDeepslatePolished extends Block implements IRegisterable, IHasRecipes {
    public static final int META_MAX = 1;
    public static final int META_POLISHED = 0;
    public static final int META_CHISELED = 1;

    public static final String[] names = new String[] {"", "chiseled"};

    protected SoundType soundTypePolishedDeepslate = new CustomSoundTypeWithPlaceSound(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "polished_deepslate");

    public BlockDeepslatePolished() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(3.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypePolishedDeepslate);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("polished_deepslate"));
        GameRegistry.registerBlock(this, ItemBlockDeepslatePolished.class,"polished_deepslate");

        OreDictionary.registerOre("blockPolishedDeepslate", new ItemStack(this, 1, META_POLISHED));
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons = new IIcon[META_MAX + 1];
        icons[META_POLISHED] = register.registerIcon(StringUtil.name.domainedTexture("polished_deepslate"));
        icons[META_CHISELED] = register.registerIcon(StringUtil.name.domainedTexture("chiseled_deepslate"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return icons[meta%(META_MAX+1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"unchecked"})
    public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
        for(int i = 0; i <= META_MAX; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addShaped(new ItemStack(this, 1, META_CHISELED),
                "#",
                "#",
                '#', MyBlocks.slabCobbledDeepslate);

        RecipeRegister.addShapedOre(new ItemStack(this, 4, META_POLISHED),
                "##",
                "##",
                '#', "stoneCobbledDeepslate");
    }
}
