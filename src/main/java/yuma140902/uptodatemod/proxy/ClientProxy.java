package yuma140902.uptodatemod.proxy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraftforge.common.MinecraftForge;
import yuma140902.uptodatemod.client.renderer.RenderArmorStand;
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
import yuma140902.uptodatemod.launch.VanillaResourceLoader;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.resourcepack.UpToDateModResourcePack;

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
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.boats)) {
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatAcacia.class, new RenderModBoat(Type.ACACIA));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirch.class, new RenderModBoat(Type.BIRCH));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatDarkOak.class, new RenderModBoat(Type.DARK_OAK));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderModBoat(Type.JUNGLE));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderModBoat(Type.JUNGLE));
			RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruce.class, new RenderModBoat(Type.SPRUCE));
		}
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.armorStand)) {
			RenderingRegistry.registerEntityRenderingHandler(EntityArmorStand.class, new RenderArmorStand());
		}
		
		if(DisabledFeaturesRegistry.INSTANCE.isEnabled(EnumDisableableFeatures.glazedTerracotta))
			RenderingRegistry.registerBlockHandler(new RenderBlockGlazedTerracotta());
		RenderingRegistry.registerBlockHandler(new RenderBlockLantern());
	}
	
	@Override
	public void loadVanillaResources() {
		try {
			Path caches = Paths.get("uptodatemod/dl-cache");
			Path archives = Paths.get("uptodatemod/client-jars");
			Path assets = Paths.get("uptodatemod/assets/uptodate");
			
			VanillaResourceLoader.load(caches, archives, assets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
    defaultResourcePacks.add(new UpToDateModResourcePack());
	}
}
