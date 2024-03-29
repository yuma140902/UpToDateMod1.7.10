package yuma140902.uptodatemod.proxy;

import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.entity.item.*;
import yuma140902.uptodatemod.event_handlers.CommonEventHandler;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.tileentity.TileEntityBarrel;
import yuma140902.uptodatemod.vrl.VRLException;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

import static yuma140902.uptodatemod.config.ModConfigCore.Deprecated.*;

public class CommonProxy {
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(CommonEventHandler.INSTANCE);
        FMLCommonHandler.instance().bus().register(CommonEventHandler.INSTANCE);
    }

    public void registerEntities() {
        if (EnumDisableableFeatures.boats.featureEnabled()) {
            EntityRegistry.registerModEntity(EntityBoatAcacia.class, "boat_acacia", idBoatAcacia(), ModUpToDateMod.INSTANCE, 128, 5, true);
            EntityRegistry.registerModEntity(EntityBoatBirch.class, "boat_birch", idBoatBirch(), ModUpToDateMod.INSTANCE, 128, 5, true);
            EntityRegistry.registerModEntity(EntityBoatDarkOak.class, "boat_dark_oak", idBoatDarkOak(), ModUpToDateMod.INSTANCE, 128, 5, true);
            EntityRegistry.registerModEntity(EntityBoatJungle.class, "boat_jungle", idBoatJungle(), ModUpToDateMod.INSTANCE, 128, 5, true);
            EntityRegistry.registerModEntity(EntityBoatSpruce.class, "boat_spruce", idBoatSpruce(), ModUpToDateMod.INSTANCE, 128, 5, true);
        }
        if (EnumDisableableFeatures.armorStand.featureEnabled()) {
            EntityRegistry.registerModEntity(EntityArmorStand.class, "wooden_armorstand", idArmorStand(), ModUpToDateMod.INSTANCE, 64, 1, true);
        }
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityBarrel.class, TileEntityBarrel.tileEntityId());
    }

    public int getNewRenderId() {
        return -1;
    }

    public void registerRenderers() {
    }

    public void loadVanillaResources() throws VRLException {
    }
}
