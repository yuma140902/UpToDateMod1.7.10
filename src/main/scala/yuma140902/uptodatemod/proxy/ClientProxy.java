package yuma140902.uptodatemod.proxy;

import java.util.List;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.client.renderer.RenderArmorStand;
import yuma140902.uptodatemod.client.renderer.RenderBlockBarrel;
import yuma140902.uptodatemod.client.renderer.RenderBlockGlazedTerracotta;
import yuma140902.uptodatemod.client.renderer.RenderBlockLantern;
import yuma140902.uptodatemod.client.renderer.RenderModBoat;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;
import yuma140902.uptodatemod.entity.item.EntityBoatAcacia;
import yuma140902.uptodatemod.entity.item.EntityBoatBirch;
import yuma140902.uptodatemod.entity.item.EntityBoatDarkOak;
import yuma140902.uptodatemod.entity.item.EntityBoatJungle;
import yuma140902.uptodatemod.entity.item.EntityBoatSpruce;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;
import yuma140902.uptodatemod.event_handlers.ClientEventHandler;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.resourcepack.UpToDateModResourcePack;
import yuma140902.uptodatemod.vrl.VRLException;
import yuma140902.uptodatemod.vrl.VanillaResourceLoader;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(ClientEventHandler.INSTANCE);
	}
	
	@Override
	public int getNewRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderers() {
		super.registerRenderers();
		if(EnumDisableableFeatures.boats.featureEnabled()) {
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatAcacia.class, new RenderModBoat(Type.ACACIA));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirch.class, new RenderModBoat(Type.BIRCH));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatDarkOak.class, new RenderModBoat(Type.DARK_OAK));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderModBoat(Type.JUNGLE));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderModBoat(Type.JUNGLE));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruce.class, new RenderModBoat(Type.SPRUCE));
		}
		if(EnumDisableableFeatures.armorStand.featureEnabled()) {
			RenderingRegistry.registerEntityRenderingHandler(EntityArmorStand.class, new RenderArmorStand());
		}
		
		if(EnumDisableableFeatures.glazedTerracotta.featureEnabled())
			RenderingRegistry.registerBlockHandler(new RenderBlockGlazedTerracotta());
		if(EnumDisableableFeatures.lantern.featureEnabled())
			RenderingRegistry.registerBlockHandler(new RenderBlockLantern());
		if(EnumDisableableFeatures.barrel.featureEnabled())
			RenderingRegistry.registerBlockHandler(new RenderBlockBarrel());
	}
	
	@Override
	public void loadVanillaResources() throws VRLException {
		new VanillaResourceLoader(ModUpToDateMod.INSTANCE.uptodatemodDirectory).load();
		
		List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
		defaultResourcePacks.add(new UpToDateModResourcePack());
	}
}
