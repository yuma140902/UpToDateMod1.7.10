package yuma140902.uptodatemod.event_handlers;

import java.util.Set;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.oredict.OreDictionary;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.blocks.BlockCoarseDirt;
import yuma140902.uptodatemod.blocks.BlockWitherRose;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.network.NoteBlockPlayMessage;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.registry.EnumNoteBlockInstrument;
import yuma140902.uptodatemod.registry.HoeEfficientBlockRegistry;

public class CommonEventHandler {
	private CommonEventHandler() {}
	
	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	
	@SubscribeEvent
	public void onUseHoeEvent(UseHoeEvent event) {
		BlockCoarseDirt.onUseHoeEvent(event);
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		if(EnumDisableableFeatures.witherRose.featureEnabled()) {
			BlockWitherRose.onLivingDeathEvent(event);
		}
	}
	
	private Item.ToolMaterial getHoeMaterial(ItemStack itemStack){
		if(itemStack == null || itemStack.getItem() == null) return null;
		if(itemStack.getItem() instanceof ItemHoe){
			ItemHoe hoe = (ItemHoe)itemStack.getItem();
			try {
				return Item.ToolMaterial.valueOf(hoe.getToolMaterialName());
			}catch(IllegalArgumentException ex){
				assert false : ex;
			}
		}
		return null;
	}
	
	@SubscribeEvent
	public void hoeBreakSpeed(PlayerEvent.BreakSpeed event){
		Item.ToolMaterial material = getHoeMaterial(event.entityPlayer.getHeldItem());
		if(material == null) return;
		if(HoeEfficientBlockRegistry.INSTANCE.isHoeEfficient(event.block)){
			event.newSpeed = event.originalSpeed * material.getEfficiencyOnProperMaterial();
		}
	}
	
	@SubscribeEvent
	public void onBreakBlock(BlockEvent.BreakEvent event){
		ItemStack heldItem = event.getPlayer().getHeldItem();
		if(heldItem == null || heldItem.getItem() == null) return;
		if(heldItem.getItem() instanceof ItemHoe) heldItem.damageItem(1, event.getPlayer());
	}
	
	private boolean isShovel(ItemStack itemstack) {
		if(itemstack != null && itemstack.getItem() != null) {
			Set<String> toolClasses = itemstack.getItem().getToolClasses(itemstack);
			return toolClasses != null && toolClasses.contains("shovel");
		}
		return false;
	}

	private boolean canBlockTurnIntoGlassPath(Block block) {
		return block == Blocks.grass || block == Blocks.dirt || block == MyBlocks.coarseDirt;
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
		
		if(EnumDisableableFeatures.grassPath.featureEnabled() &&
				isShovel(heldItem) && canBlockTurnIntoGlassPath(world.getBlock(x, y, z))  ) {
			Block blockAbove = world.getBlock(x, y + 1, z);
			if(blockAbove != null && blockAbove.isOpaqueCube()) return;
			
			world.setBlock(x, y, z, MyBlocks.grassPath);
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "dig.grass", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
		}
		
		StripWoodHandler.handle(event);
	}
	
	@SubscribeEvent
	public void onNoteBlockPlay(NoteBlockEvent.Play event) {
		if(!EnumDisableableFeatures.newNoteBlockInstruments.featureEnabled()) {
			return;
		}
		
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
		
		float f = (float)Math.pow(2.0D, (double)(noteId - 12) / 12.0D);
		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, instrument.getSoundName(), 3.0F, f);
		ModUpToDateMod.networkWrapper.sendToAllAround(new NoteBlockPlayMessage(instrument, noteId, dimId, x, y, z), new TargetPoint(dimId, x, y, z, 32));
		
		event.setCanceled(true);
	}
	
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if(ModUpToDateMod.MOD_ID.equals(event.modID))
			ModConfigCore.syncConfig();
	}
}
