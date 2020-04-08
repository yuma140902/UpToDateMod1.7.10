package yuma140902.uptodatemod.event_handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.util.BlockState;

public class StripWoodHandler {
	
	@Nonnull
	private static List<IWoodStrippingInfo> registry = new ArrayList<>();
	
	/**
	 * {@link IWoodStrippingInfo}を登録<br>
	 * 任意のタイミングで呼び出せます
	 */
	public static void register(@Nonnull IWoodStrippingInfo info) {
		registry.add(info);
	}
	
	private static boolean isAxe(ItemStack itemstack) {
		if(itemstack != null && itemstack.getItem() != null) {
			Set<String> toolClasses = itemstack.getItem().getToolClasses(itemstack);
			return toolClasses != null && toolClasses.contains("axe");
		}
		return false;
	}
	
	public static void handle(PlayerInteractEvent event) {
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		ItemStack heldItem = event.entityPlayer.getHeldItem();
		
		if(!DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.strippedLogs)) return;
		if(!isAxe(heldItem)) return;
		
		BlockState state = BlockState.fromCoord(world, x, y, z);
		for(final IWoodStrippingInfo info : registry) {
			BlockState stateToBe = info.blockStateToPlace(state);
			if(stateToBe == null) continue;
			
			world.setBlock(x, y, z, stateToBe.block);
			world.setBlockMetadataWithNotify(x, y, z, stateToBe.meta, 3);
			
			String soundName = info.soundName();
			if(soundName != null) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, soundName, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
	}
	
	public static void registerBasicWoodStripping() {
		register(WoodStrippingInfo.of(state -> {
			final Block block = state.block;
			final int log = block == Blocks.log ? 1 : block == Blocks.log2 ? 2 : 0;
			if(log == 0) return null;
			
			final int meta = state.meta;
			final int axis = meta >>> 2;  // ブロックの軸の情報を取り出す
			final int logType = meta & 0b0011;   // 木材の種類の情報を取り出す
			
			Block strippedLog = null;
			switch(logType) {
				case 0:
					assert 0 == McConst.Meta.LOG_OAK && McConst.Meta.LOG_OAK == McConst.Meta.LOG2_ACACIA;
					strippedLog = log == 1 ? MyBlocks.strippedLogOak : MyBlocks.strippedLogAcacia; 
					break;
				case 1:
					assert 1 == McConst.Meta.LOG_SPRUCE && McConst.Meta.LOG_SPRUCE == McConst.Meta.LOG2_DARK_OAK;
					strippedLog = log == 1 ? MyBlocks.strippedLogSpruce : MyBlocks.strippedLogDarkOak; 
					break;
				case McConst.Meta.LOG_BIRCH:	strippedLog = MyBlocks.strippedLogBirch; break;
				case McConst.Meta.LOG_JUNGLE:	strippedLog = MyBlocks.strippedLogJungle; break;
				default: return null;
			}
			
			assert strippedLog != null;
			return new BlockState(strippedLog, axis << 2);
		}, "dig.cloth"));
	}
}
