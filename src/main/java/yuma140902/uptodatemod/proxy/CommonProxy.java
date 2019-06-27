package yuma140902.uptodatemod.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;
import yuma140902.uptodatemod.entity.item.EntityBoatAcacia;
import yuma140902.uptodatemod.entity.item.EntityBoatBirch;
import yuma140902.uptodatemod.entity.item.EntityBoatDarkOak;
import yuma140902.uptodatemod.entity.item.EntityBoatJungle;
import yuma140902.uptodatemod.entity.item.EntityBoatSpruce;
import yuma140902.uptodatemod.event_handlers.CommonEventHandler;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;

public class CommonProxy {
	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(CommonEventHandler.INSTANCE);
	}
	
	public void registerEntities() {
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.boats)) {
			EntityRegistry.registerModEntity(EntityBoatAcacia.class, "boat_acacia", 0, ModUpToDateMod.INSTANCE, 128, 5, true);
			EntityRegistry.registerModEntity(EntityBoatBirch.class, "boat_birch", 1, ModUpToDateMod.INSTANCE, 128, 5, true);
			EntityRegistry.registerModEntity(EntityBoatDarkOak.class, "boat_dark_oak", 2, ModUpToDateMod.INSTANCE, 128, 5, true);
			EntityRegistry.registerModEntity(EntityBoatJungle.class, "boat_jungle", 3, ModUpToDateMod.INSTANCE, 128, 5, true);
			EntityRegistry.registerModEntity(EntityBoatSpruce.class, "boat_spruce", 4, ModUpToDateMod.INSTANCE, 128, 5, true);
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.armorStand)) {
			EntityRegistry.registerModEntity(EntityArmorStand.class, "wooden_armorstand", 5, ModUpToDateMod.INSTANCE, 64, 1, true);
		}
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBarrel.class, TileEntityBarrel.tileEntityId);
	}
	
	public int getNewRenderId() {
		return -1;
	}
	
	public void registerRenderers() { }
}
