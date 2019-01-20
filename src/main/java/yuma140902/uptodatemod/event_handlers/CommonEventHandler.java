package yuma140902.uptodatemod.event_handlers;

import java.util.Random;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.config.ModConfigCore;
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
		
		if(entity instanceof EntitySheep) {
			if(entity.isBurning()) {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.cookedMutton, rand.nextInt(2) + 1)));
			}
			else {
				event.drops.add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(MyItems.rawMutton, rand.nextInt(2) + 1)));
			}
		}
	}
	
	private boolean isShovel(Item item) {
		return item instanceof ItemSpade;
	}
	
	private boolean isAxe(Item item) {
		return item instanceof ItemAxe;
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
		
		if(isShovel(heldItem.getItem()) && world.getBlock(x, y, z) == Blocks.grass) {
			Block blockAbove = world.getBlock(x, y + 1, z);
			if(blockAbove != null && blockAbove.isOpaqueCube()) return;
			
			world.setBlock(x, y, z, MyBlocks.grassPath);
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "dig.grass", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
		if(isAxe(heldItem.getItem())) {
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
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(ModUpToDateMod.MOD_ID.equals(event.modID))
			ModConfigCore.syncConfig();
	}
}
