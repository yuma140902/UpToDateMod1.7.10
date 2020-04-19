package yuma140902.uptodatemod.blocks;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.items.ItemBlockMultiName;
import yuma140902.yumalib.api.util.BlockPos;

public class BlockSponge extends Block implements IRegisterable, IHasRecipes {
	
	public static final Material spongeMaterial = new Material(MapColor.yellowColor);
	
	private static final String[] names = new String[] {"", "wet"};
	
	public static final int META_DRY = 0, META_WET = 1;
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockSponge() {
		super(spongeMaterial);
		setHardness(0.6f);
		setResistance(0.6f);
		setStepSound(soundTypeGrass);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void register() {
		setBlockName(StringUtil.name.domainedUnlocalized("sponge"));
		GameRegistry.registerBlock(this, Companion.class, "sponge");
	}
	
	@Override
	public void registerRecipes() {
		RecipeRegister.addSmelting(new ItemStack(this, 1, META_WET), new ItemStack(this, 1, META_DRY), 0.1f);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[2];
		icons[META_DRY] = register.registerIcon(StringUtil.name.domainedTexture("sponge"));
		icons[META_WET] = register.registerIcon(StringUtil.name.domainedTexture("wet_sponge"));
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta%2];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(this, 1, META_DRY));
		list.add(new ItemStack(this, 1, META_WET));
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		tryAbsorb(world, x, y, z, world.getBlockMetadata(x, y, z));
	}
	

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == META_WET && world.provider.dimensionId == -1) {
			world.setBlockMetadataWithNotify(x, y, z, META_DRY, 3);
			Random rand = world.rand;
			world.playSoundEffect((double)x+0.5D, (double)y+0.5D, (double)z+0.5D, "random.fizz", 1.1F, 2.6F + (rand.nextFloat() - rand.nextFloat()) * 0.8F);
			for(int i=0; i<rand.nextInt(10)+5; ++i) {
				double posX = x+0.5D + (rand.nextDouble()-0.5D)*0.5D;
				double posY = y+1.0D + (rand.nextDouble()-0.5D)*0.2D;
				double posZ = z+0.5D + (rand.nextDouble()-0.5D)*0.5D;
				double velY = rand.nextDouble() * 0.05D;
				world.spawnParticle(McConst.Particle.CLOUD, posX, posY, posZ, 0, velY, 0);
			}
		}
		else {
			tryAbsorb(world, x, y, z, meta);
		}
	}
	
	/**
	 * @return 水を吸ったら{@link META_WET}、そうでなければ{@link META_DRY}。
	 */
	private static int tryAbsorb(World world, int x, int y, int z, int meta) {
		boolean absorbed;
		if(meta == META_DRY)
			absorbed = absorb(world, new BlockPos(x, y, z));
		else
			absorbed = true;
		
		return absorbed ? META_WET : META_DRY;
	}
	
	private static boolean absorb(World world, BlockPos pos) {
		// スポンジに隣接している水を順に取り除く
		// ただし、スポンジから6ブロック以上離れているものは吸い取らない
		// 吸い取る水は最大で64個
		Queue<Pair<BlockPos, Integer>> queue = new LinkedList<>();
		queue.add(new ImmutablePair<>(pos, 0));
		int blockRemoved = 0;
		
		while(!queue.isEmpty()) {
			Pair<BlockPos, Integer> tuple = queue.poll();
			BlockPos blockPosOrig = tuple.getLeft();
			int distanceFromSponge = tuple.getRight();
			
			for(final ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
				BlockPos blockpos = blockPosOrig.offset(direction);
				Block block = world.getBlock(blockpos.x(), blockpos.y(), blockpos.z());
				if (block == Blocks.water || block == Blocks.flowing_water) {
					world.setBlock(blockpos.x(), blockpos.y(), blockpos.z(), Blocks.air, 0, 3);
          ++blockRemoved;
          if (distanceFromSponge < 6) {
             queue.add(new ImmutablePair<>(blockpos, distanceFromSponge + 1));
          }
				}
			}
			
			if (blockRemoved > 64) {
				break;
			}
		}
		
		if(blockRemoved > 0) world.setBlock(pos.x(), pos.y(), pos.z(), MyBlocks.sponge, BlockSponge.META_WET, 2);
		return blockRemoved > 0;
	}
	
	public static class Companion extends ItemBlockMultiName {
		public Companion(Block block) {
			super(block, BlockSponge.names);
		}
	}
}
