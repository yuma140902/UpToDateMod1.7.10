package yuma140902.uptodatemod.blocks;

import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.util.BlockPos;
import yuma140902.yumalib.api.util.WorldUtils;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockMagma extends Block implements IRegisterable, IHasRecipes {

    public BlockMagma() {
        super(Material.rock);
        setLightLevel(0.2F);
        setHardness(0.5F);
        setStepSound(soundTypeStone);
        setTickRandomly(true);

        setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * 詳細は{@link BlockGrassPath}を参照
     */
    private static boolean needEdgeCollisionBox(World world, BlockPos pos, ForgeDirection direction) {
        pos = pos.offset(direction, 1);
        if (WorldUtils.getBlock(world, pos) != MyBlocks.grassPath && !WorldUtils.isAir(world, pos)) {
            return !WorldUtils.noCollisionBox(world, pos.offset(ForgeDirection.UP, 3)) ||
                    !WorldUtils.noCollisionBox(world, pos.offset(ForgeDirection.UP, 1));
        }
        return false;
    }

    @Override
    public void register() {
        this.setBlockName(StringUtil.name.domainedUnlocalized("magma_block"));
        this.setBlockTextureName(StringUtil.name.domainedTexture("magma_block"));
        GameRegistry.registerBlock(this, "magma_block");
    }

    @Override
    public MapColor getMapColor(int p_149728_1_) {
        return MapColor.netherrackColor;
    }

    /**
     * 詳細は{@link BlockGrassPath}を参照
     */
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        BlockPos pos = new BlockPos(x, y, z);

        float edge = 31f / 32f;

        // ベースとなる当たり判定
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 255f / 256f, 1.0f);
        super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);

        if (needEdgeCollisionBox(world, pos, ForgeDirection.EAST)) {  // EAST: +x
            this.setBlockBounds(edge, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (needEdgeCollisionBox(world, pos, ForgeDirection.WEST)) {  // WEST: -x
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f - edge, 1.0f, 1.0f);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (needEdgeCollisionBox(world, pos, ForgeDirection.SOUTH)) {  // SOUTH: +z
            this.setBlockBounds(0.0f, 0.0f, edge, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }
        if (needEdgeCollisionBox(world, pos, ForgeDirection.NORTH)) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f - edge);
            super.addCollisionBoxesToList(world, x, y, z, mask, list, entity);
        }

        // もとに戻す。戻さないと描画時の表示がおかしくなる
        setBlockBoundsForItemRender();
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!entity.isImmuneToFire() && !entity.isSneaking() && entity instanceof EntityLivingBase) {
            entity.attackEntityFrom(DamageSource.onFire, 1.0F);
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);

        Block blockAbove = world.getBlock(x, y + 1, z);

        if (blockAbove == Blocks.water || blockAbove == Blocks.flowing_water) {
            world.setBlockToAir(x, y + 1, z);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (world.isRaining()) {
            double posX = x + 0.5;
            double posY = y + 1.1;
            double posZ = z + 0.5;
            double velX = 0;
            double velY = 0;
            double velZ = 0;

            int count = rand.nextInt(8);
            for (int i = 0; i < count; ++i) {
                world.spawnParticle(McConst.Particle$.MODULE$.SMOKE(), posX + rand.nextDouble() - 0.5, posY, posZ + rand.nextDouble() - 0.5, velX, velY, velZ);
            }
        }
    }

    @Override
    public void registerRecipes() {
        RecipeRegister.addShaped(
                new ItemStack(MyBlocks.magmaBlock),
                "OO",
                "OO",
                'O', Items.magma_cream
        );
    }
}
