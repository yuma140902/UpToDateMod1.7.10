package yuma140902.uptodatemod.event_handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockFullWood;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.yumalib.api.blockstate.EnumVanillaLog;
import yuma140902.yumalib.api.blockstate.EnumVanillaLog2;
import yuma140902.yumalib.api.blockstate.VanillaRotatedPillarState;
import yuma140902.yumalib.api.blockstate.VanillaRotatedPillarState.GenericBlockType;
import yuma140902.yumalib.api.blockstate.VanillaRotatedPillarState.Axis;
import yuma140902.yumalib.api.blockstate.VanillaRotatedPillarState.NoBlockType;
import yuma140902.yumalib.api.util.BlockWithMetadata;

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
		
		BlockWithMetadata state = BlockWithMetadata.fromCoord(world, x, y, z);
		for(final IWoodStrippingInfo info : registry) {
			BlockWithMetadata stateToBe = info.blockStateToPlace(state);
			if(stateToBe == null) continue;
			
			world.setBlock(x, y, z, stateToBe.block);
			world.setBlockMetadataWithNotify(x, y, z, stateToBe.meta, 3);
			
			String soundName = info.soundName();
			if(soundName != null) world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, soundName, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
	}
	
	public static void registerBasicWoodStripping() {
		register(WoodStrippingInfo.of(blockWithMeta -> {
			final Block block = blockWithMeta.block;
			if(block != Blocks.log && block != Blocks.log2){
				return null;  // minecraft:logでもminecraft:log2でもなかったら何もしない
			}
			
			final Block strippedLog;
			final Axis axis;
			if(block == Blocks.log){
				final VanillaRotatedPillarState<EnumVanillaLog> state = VanillaRotatedPillarState.fromMetadata(blockWithMeta.meta, EnumVanillaLog.class);
				axis = state.axis();
				switch (state.blockType()){
					case Oak:
						strippedLog = MyBlocks.strippedLogOak;
						break;
					case Spruce:
						strippedLog = MyBlocks.strippedLogSpruce;
						break;
					case Birch:
						strippedLog = MyBlocks.strippedLogBirch;
						break;
					case Jungle:
						strippedLog = MyBlocks.strippedLogJungle;
						break;
					default: return null;
				}
			}
			else{
				final VanillaRotatedPillarState<EnumVanillaLog2> state = VanillaRotatedPillarState.fromMetadata(blockWithMeta.meta, EnumVanillaLog2.class);
				axis = state.axis();
				switch (state.blockType()){
					case Acacia:
						strippedLog = MyBlocks.strippedLogAcacia;
						break;
					case DarkOak:
						strippedLog = MyBlocks.strippedLogDarkOak;
						break;
					default: return null;
				}
			}
			
			if(strippedLog == null) return null;
			final VanillaRotatedPillarState<NoBlockType> newState = new VanillaRotatedPillarState<>(NoBlockType.NONE, axis, NoBlockType.class);
			return new BlockWithMetadata(strippedLog, newState.metadata());
		}, "dig.cloth"));
		
		
		register(WoodStrippingInfo.of(blockWithMeta -> {
			if(blockWithMeta.block != MyBlocks.wood) return null;
			int meta = MathHelper.clamp_int(blockWithMeta.meta, 0, BlockFullWood.META_MAX);
			BlockGenericStrippedLog blockToPlace = BlockFullWood.getStrippedLog(meta);
			VanillaRotatedPillarState<NoBlockType> newState = new VanillaRotatedPillarState<>(NoBlockType.NONE, Axis.NO_AXIS, NoBlockType.class);
			return new BlockWithMetadata(blockToPlace, newState.metadata());
		}, "dig.cloth"));
	}
}
