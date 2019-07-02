package yuma140902.uptodatemod.event_handlers;

import java.util.Random;
import java.util.Set;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.network.NoteBlockPlayMessage;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.registry.EnumNoteBlockInstrument;
import yuma140902.uptodatemod.util.Stat;

public class CommonEventHandler {
	private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	@SubscribeEvent
	public void onUseHoeEvent(UseHoeEvent event) {
		BlockCoarseDirt.onUseHoeEvent(event);
	}
	
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		Entity entity = event.entityLiving;
		Random rand = event.entityLiving.worldObj.rand;
		
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.mutton) && 
				entity instanceof EntitySheep) {
			if(entity.isBurning()) {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.cookedMutton, rand.nextInt(2) + 1)));
			}
			else {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.rawMutton, rand.nextInt(2) + 1)));
			}
		}
	}
	
	private boolean isShovel(ItemStack itemstack) {
		if(itemstack != null && itemstack.getItem() != null) {
			Set<String> toolClasses = itemstack.getItem().getToolClasses(itemstack);
			return toolClasses != null && toolClasses.contains("shovel");
		}
		return false;
	}
	
	private boolean isAxe(ItemStack itemstack) {
		if(itemstack != null && itemstack.getItem() != null) {
			Set<String> toolClasses = itemstack.getItem().getToolClasses(itemstack);
			return toolClasses != null && toolClasses.contains("axe");
		}
		return false;
	}
	
	@SubscribeEvent
	public void onPlayerUsedItem(PlayerInteractEvent event) {
		if(event.action != Action.RIGHT_CLICK_BLOCK) return;
		
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		ItemStack heldItem = event.entityPlayer.getHeldItem();
		if(heldItem == null) return;
		
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.grassPath) &&
				isShovel(heldItem) && world.getBlock(x, y, z) == Blocks.grass) {
			Block blockAbove = world.getBlock(x, y + 1, z);
			if(blockAbove != null && blockAbove.isOpaqueCube()) return;
			
			world.setBlock(x, y, z, MyBlocks.grassPath);
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "dig.grass", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.strippedLogs) &&
				isAxe(heldItem)) {
			Block block = world.getBlock(x, y, z);
			int log =  block == Blocks.log ? 1 : block == Blocks.log2 ? 2 : 0;
			if(log == 0) return;
			
			int meta = world.getBlockMetadata(x, y, z);
			int axis = meta >>> 2;
			meta = meta & 0b0011;
			
			Block strippedLog = null;
			switch(meta) {
				case Stat.LOG_META_OAK: //LOG_META_OAK == LOG2_META_ACACIA == 0
					strippedLog = log == 1 ? MyBlocks.strippedLogOak : MyBlocks.strippedLogAcacia; break;
				case Stat.LOG_META_SPRUCE: //LOG_META_SPRUCE == LOG2_META_DARK_OAK == 1
					strippedLog = log == 1 ? MyBlocks.strippedLogSpruce : MyBlocks.strippedLogDarkOak; break;
				case Stat.LOG_META_BIRCH:	strippedLog = MyBlocks.strippedLogBirch; break;
				case Stat.LOG_META_JUNGLE:	strippedLog = MyBlocks.strippedLogJungle; break;
				default: return;
			}
			
			world.setBlock(x, y, z, strippedLog);
			world.setBlockMetadataWithNotify(x, y, z, axis << 2, 3);
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "dig.cloth", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
	}
	
	@SubscribeEvent
	public void onNoteBlockPlay(NoteBlockEvent.Play event) {
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;
		int dimId = world.provider.dimensionId;
		int noteId = event.getVanillaNoteId();
		EnumNoteBlockInstrument instrument = null;
		
		Block blockUnder = world.getBlock(x, y-1, z);
		
		boolean matched = false;
		for(int i = 0; i < EnumNoteBlockInstrument.getLength(); ++i) {
			instrument = EnumNoteBlockInstrument.fromId(i);
			if(instrument.matches(blockUnder)) {
				matched = true;
				break;
			}
		}
		
		if(!matched) {
			return;
		}
		
		ModUpToDateMod.networkWrapper.sendToAllAround(new NoteBlockPlayMessage(instrument, noteId, dimId, x, y, z), new TargetPoint(dimId, x, y, z, 32));
		
		event.setCanceled(true);
	}
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(ModUpToDateMod.MOD_ID.equals(event.modID))
			ModConfigCore.syncConfig();
	}
}
