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
import yuma140902.uptodatemod.items.ItemBlockDeepslateBricks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.blocks.CustomSoundTypeWithPlaceSound;

import java.util.List;

public class BlockDeepslateBricks extends Block implements IRegisterable, IHasRecipes {
    public static final int META_MAX = 3;
    public static final int META_BRICKS = 0;
    public static final int META_CRACKED_BRICKS = 1;
    public static final int META_TILES = 2;
    public static final int META_CRACKED_TILES = 3;

    public static final String[] names = new String[] {"", "cracked", "tiles", "cracked_tiles"};

    protected SoundType soundTypeDeepslateBricks = new CustomSoundTypeWithPlaceSound(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "deepslate_bricks");

    public BlockDeepslateBricks() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(1.5F);
        this.setResistance(6.0F);
        this.setStepSound(soundTypeDeepslateBricks);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_bricks"));
        GameRegistry.registerBlock(this, ItemBlockDeepslateBricks.class, "deepslate_bticks");

        OreDictionary.registerOre("bricksDeepslate", new ItemStack(this, 1, META_BRICKS));
        OreDictionary.registerOre("bricksDeepslate", new ItemStack(this, 1, META_CRACKED_BRICKS));
        OreDictionary.registerOre("tilesDeepslate", new ItemStack(this, 1, META_TILES));
        OreDictionary.registerOre("tilesDeepslate", new ItemStack(this, 1, META_CRACKED_TILES));
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons = new IIcon[META_MAX + 1];

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        icons[META_BRICKS] = register.registerIcon(StringUtil.name.domainedTexture("deepslate_bricks"));
        icons[META_CRACKED_BRICKS] = register.registerIcon(StringUtil.name.domainedTexture("cracked_deepslate_bricks"));
        icons[META_TILES] = register.registerIcon(StringUtil.name.domainedTexture("deepslate_tiles"));
        icons[META_CRACKED_TILES] = register.registerIcon(StringUtil.name.domainedTexture("cracked_deepslate_tiles"));
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
        RecipeRegister.addShapedOre(new ItemStack(this, 4, META_BRICKS),
                "##",
                "##",
                '#', "blockPolishedDeepslate");
        RecipeRegister.addShapedOre(new ItemStack(this, 4, META_TILES),
                "##",
                "##",
                '#', "bricksDeepslate");

        RecipeRegister.addSmelting(
                new ItemStack(this, 1, META_BRICKS),
                new ItemStack(this, 1, META_CRACKED_BRICKS),
                McConst.EXP_STONE
        );
        RecipeRegister.addSmelting(
                new ItemStack(this, 1, META_TILES),
                new ItemStack(this, 1, META_CRACKED_TILES),
                McConst.EXP_STONE
        );
    }
}
