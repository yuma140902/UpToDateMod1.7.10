package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;

import java.util.Random;

public class BlockDeepslateRedstoneOre extends BlockRedstoneOre implements IHasRecipes, IRegisterable {
    private final boolean lit;

    public BlockDeepslateRedstoneOre(boolean lit) {
        super(lit);
        this.lit = lit;
        if(!lit) this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(4.5F);
        this.setResistance(10.0F);
        this.setStepSound(BlockDeepslate.soundTypeDeepslate);
        this.setHarvestLevel("pickaxe", 2);
        if(lit) this.setLightLevel(0.625f);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        this.light(world, x, y, z);
        super.onBlockClicked(world, x, y, z, player);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        this.light(world, x, y, z);
        super.onEntityWalking(world, x, y, z, entity);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        this.light(world, x, y, z);
        return super.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    private void light(World world, int x, int y, int z) {
        this.spawnParticles(world, x, y, z);
        if (!this.lit) {
            world.setBlock(x, y, z, MyBlocks.deepslateRedstoneOreLit);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (this.lit) {
            world.setBlock(x, y, z, MyBlocks.deepslateRedstoneOre);
        }
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return this.quantityDropped(random) + random.nextInt(fortune + 1);
    }

    @Override
    public int quantityDropped(Random random) {
        return 4 + random.nextInt(2);
    }


    private final Random rand = new Random();

    @Override
    public int getExpDrop(IBlockAccess world, int meta, int fortune) {
        return 1 + rand.nextInt(5);
    }

    @Override
    public Item getItemDropped(int metadata, Random p_149650_2_, int fortune) {
        return Items.redstone;
    }

    @Override
    protected ItemStack createStackedBlock(int meta) {
        return new ItemStack(MyBlocks.deepslateRedstoneOre);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (this.lit) {
            this.spawnParticles(world, x, y, z);
        }
    }

    private void spawnParticles(World world, int x, int y, int z) {
        Random random = world.rand;

        for (int l = 0; l < 6; ++l) {
            double px = (x + random.nextFloat());
            double py = (y + random.nextFloat());
            double pz = (z + random.nextFloat());

            if (l == 0 && !world.getBlock(x, y + 1, z).isOpaqueCube()) {
                py = (y + 1) + 1.0 / 16.0;
            }

            if (l == 1 && !world.getBlock(x, y - 1, z).isOpaqueCube()) {
                py = (y + 0) - 1.0 / 16.0;
            }

            if (l == 2 && !world.getBlock(x, y, z + 1).isOpaqueCube()) {
                pz = (z + 1) + 1.0 / 16.0;
            }

            if (l == 3 && !world.getBlock(x, y, z - 1).isOpaqueCube()) {
                pz = (z + 0) - 1.0 / 16.0;
            }

            if (l == 4 && !world.getBlock(x + 1, y, z).isOpaqueCube()) {
                px = (x + 1) + 1.0 / 16.0;
            }

            if (l == 5 && !world.getBlock(x - 1, y, z).isOpaqueCube()) {
                px = (x + 0) - 1.0 / 16.0;
            }

            if (px < x || px > x + 1 || py < 0.0D || py > y + 1 || pz < z || pz > z + 1) {
                world.spawnParticle(McConst.Particle$.MODULE$.REDDUST(), px, py, pz, 0.0, 0.0, 0.0);
            }
        }
    }


    @Override
    public void registerRecipes() {
        RecipeRegister.addSmelting(this, new ItemStack(Items.redstone), McConst.EXP_IRON());
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("deepslate_redstone_ore"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("deepslate_redstone_ore"));
        if(!this.lit) {
            GameRegistry.registerBlock(this, "deepslate_redstone_ore");
        }
        else{
            GameRegistry.registerBlock(this, "deepslate_redstone_ore_lit");
        }

        OreDictionary.registerOre("oreRedstone", this);
    }
}
