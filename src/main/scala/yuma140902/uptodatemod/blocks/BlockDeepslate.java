package yuma140902.uptodatemod.blocks;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.blocks.CustomSoundTypeWithPlaceSound;

import java.util.ArrayList;

public class BlockDeepslate extends BlockRotatedPillar implements IRegisterable, IHasRecipes {

    public final static SoundType soundTypeDeepslate = new CustomSoundTypeWithPlaceSound(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "deepslate");

    public BlockDeepslate() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(3.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeDeepslate);
        this.setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
        return true;
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate"));
        GameRegistry.registerBlock(this, "deepslate");

        OreDictionary.registerOre("stoneDeepslate", this);
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return Lists.newArrayList(new ItemStack(MyBlocks.cobbledDeepslate, 1));
    }


    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(
                MyBlocks.cobbledDeepslate,
                new ItemStack(this, 1),
                McConst.EXP_STONE());
    }

    @Override
    protected IIcon getSideIcon(int p_150163_1_) {
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        field_150164_N = register.registerIcon(getTextureName());
    }
}
