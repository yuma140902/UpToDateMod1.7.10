package yuma140902.uptodatemod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.uptodatemod.blocks.BlockStone;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.event_handlers.StripWoodHandler;
import yuma140902.uptodatemod.integration.Plugins;
import yuma140902.uptodatemod.loot.MobLoot;
import yuma140902.uptodatemod.network.ArmorStandInteractHandler;
import yuma140902.uptodatemod.network.ArmorStandInteractMessage;
import yuma140902.uptodatemod.network.NoteBlockPlayHandler;
import yuma140902.uptodatemod.network.NoteBlockPlayMessage;
import yuma140902.uptodatemod.proxy.CommonProxy;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.uptodatemod.util.UpToDateModConstants;
import yuma140902.uptodatemod.vrl.VRLException;
import yuma140902.uptodatemod.world.generation.MyMinableGenerator;
import yuma140902.yumalib.api.blocks.CustomSoundType;
import yuma140902.yumalib.api.client.renderer.RenderIDProvider;
import yuma140902.yumalib.api.context.InitModContext;
import yuma140902.yumalib.api.registry.Contexts;
import yuma140902.yumalib.api.registry.UpdateCheckerRegistry;
import yuma140902.yumalib.api.update.IUpdateChecker;
import yuma140902.yumalib.api.update.TsvUpdateChecker;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(modid = ModUpToDateMod.MOD_ID, name = ModUpToDateMod.MOD_NAME, version = ModUpToDateMod.MOD_VERSION, useMetadata = true, guiFactory = UpToDateModConstants.MOD_CONFIG_GUI_FACTORY,
		 dependencies = "after:etfuturum;after:ProjectE;after:Thaumcraft;required-after:yumalib"
)
public class ModUpToDateMod {
	@Mod.Metadata(ModUpToDateMod.MOD_ID)
	public static ModMetadata modMetadata;
	
	@Mod.Instance(ModUpToDateMod.MOD_ID)
	public static ModUpToDateMod INSTANCE;
	
	@SidedProxy(modId = ModUpToDateMod.MOD_ID, clientSide = UpToDateModConstants.PROXY_CLIENT, serverSide = UpToDateModConstants.PROXY_SERVER)
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper networkWrapper;
	
	public static final String MOD_ID                       = "uptodate";
	public static final String MOD_NAME                     = "UpToDateMod";
	public static final String MOD_TEXTURE_DOMAIN           = "uptodate";
	public static final String MOD_UNLOCALIZED_ENTRY_DOMAIN = "uptodate";
	public static final String MINECRAFT_VERSION            = "1.7.10";
	public static final String MOD_VERSION                  = "2.3.12";
	public static final String MOD_VERSIONS_TSV_URL         = "https://raw.githubusercontent.com/yuma140902/UpdateJSON_Forge/master/UpToDateModVersions.tsv";
	public static final Logger LOGGER                       = LogManager.getLogger(MOD_NAME);
	
	public Path uptodatemodDirectory;
	
	public static int glazedTerracottaRenderId;
	public static int lanternRenderId;
	public static int barrelRenderId;
	
	private void loadModMetadata(ModMetadata modMetadata) {
		modMetadata.modId = MOD_ID;
		modMetadata.name = MOD_NAME;
		modMetadata.version = MOD_VERSION;
		modMetadata.authorList.add("yuma140902");
		modMetadata.description = "Adds new Minecraft 1.8.0+ features\n\nGerman translation by DariusDarkBum";
		modMetadata.url = "https://www.curseforge.com/minecraft/mc-mods/uptodatemod";
		modMetadata.autogenerated = false;
	}
	
	private void tweakVanilla() {
		Items.wooden_door.setMaxStackSize(64);
		Items.iron_door.setMaxStackSize(64);
		BlockTrapDoor.disableValidation = true;
		Items.blaze_rod.setFull3D();
		
		Blocks.trapped_chest.setCreativeTab(CreativeTabs.tabRedstone);
		
		setFinalField(ItemFood.class, Items.carrot, 3, "healAmount", "field_77853_b");
		setFinalField(ItemFood.class, Items.baked_potato, 5, "healAmount", "field_77853_b");
		
		Blocks.packed_ice.setHarvestLevel("pickaxe", 0);
		Blocks.ladder.setHarvestLevel("axe", 0);
		Blocks.melon_block.setHarvestLevel("axe", 0);
		
		Blocks.netherrack.setStepSound(new CustomSoundType(MOD_TEXTURE_DOMAIN, "netherrack"));
	}
	
	private static void setFinalField(Class<?> clazz, Object that, Object newValue, String... fieldNames) {
		try {
			Field field = ReflectionHelper.findField(clazz, fieldNames);
			field.setAccessible(true);
			
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			
			field.set(that, newValue);
		} catch (Exception e) {
			LOGGER.warn("Failed to tweak a property.");
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws VRLException {
		loadModMetadata(modMetadata);
		ModConfigCore.loadConfig(event);
		LOGGER.info("preInit");
		
		Contexts.setContext(new InitModContext(MOD_NAME, StringUtil.name));
		
		if (ModConfigCore.General.doCheckUpdate()) {
			IUpdateChecker updateChecker = new TsvUpdateChecker(MOD_NAME, "https://www.curseforge.com/minecraft/mc-mods/uptodatemod", MOD_VERSIONS_TSV_URL, MOD_VERSION, ModConfigCore.General.updateChannel());
			UpdateCheckerRegistry.INSTANCE.register(updateChecker);
		}
		
		this.uptodatemodDirectory = Paths.get("uptodatemod").toAbsolutePath();
		proxy.loadVanillaResources();
		
		
		tweakVanilla();
		MyBlocks.register();
		MyItems.register();
		
		proxy.registerTileEntities();
		MyGuis.register();
		
		MobLoot.registerBasicMobLoots();
		StripWoodHandler.registerBasicWoodStripping();
		
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		networkWrapper.registerMessage(ArmorStandInteractHandler.class, ArmorStandInteractMessage.class, 0, Side.SERVER);
		networkWrapper.registerMessage(NoteBlockPlayHandler.class, NoteBlockPlayMessage.class, 1, Side.CLIENT);
		
		Contexts.removeContext();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.info("init");
		Recipes.removeVanillaRecipes();
		Recipes.register();
		
		proxy.registerEntities();
		if (EnumDisableableFeatures.glazedTerracotta.featureEnabled())
			glazedTerracottaRenderId = RenderIDProvider.getNewRenderId();
		if(EnumDisableableFeatures.lantern.featureEnabled())
			lanternRenderId = RenderIDProvider.getNewRenderId();
		if (EnumDisableableFeatures.barrel.featureEnabled())
			barrelRenderId = RenderIDProvider.getNewRenderId();
		proxy.registerRenderers();
		
		
		if (EnumDisableableFeatures.stones.featureEnabled()) {
			MyMinableGenerator.Config stoneConfig = new MyMinableGenerator.Config(ModConfigCore.WorldGen.genStones(), 33, 10, 0, 80, ModConfigCore.WorldGen.stonesBlackList());
			
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_GRANITE, stoneConfig);
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_DIORITE, stoneConfig);
			WorldGenerators.myMinableGenerator.addOreGenerator((Block) MyBlocks.stone, BlockStone.META_ANDESITE, stoneConfig);
			

		}
		if(EnumDisableableFeatures.magmaBlock.featureEnabled()) {
			MyMinableGenerator.Config magmaConfig
					= new MyMinableGenerator.Config(ModConfigCore.WorldGen.genMagmaBlock(), 33, 5, 0, 40, ModConfigCore.WorldGen.magmaBlockBlackList(), Blocks.netherrack);
			WorldGenerators.myMinableGenerator.addOreGenerator(MyBlocks.magmaBlock, magmaConfig);
		}
		if(EnumDisableableFeatures.deepslateStone.featureEnabled()){
			MyMinableGenerator.Config config = new MyMinableGenerator.Config(ModConfigCore.WorldGen.genDeepslate(), 60, 10, 0, 16, ModConfigCore.WorldGen.deepslateBlackList());
			WorldGenerators.myMinableGenerator.addOreGenerator(MyBlocks.deepslate, config);
		}
		WorldGenerators.register();
		
		proxy.registerEventHandlers();
		
		Plugins.tweakMods();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.info("postInit");
		Plugins.tweakModsPost();
		Plugins.logPluginStats();
	}
}
