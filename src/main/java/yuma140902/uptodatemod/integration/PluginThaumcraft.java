package yuma140902.uptodatemod.integration;

import com.google.common.base.Strings;
import cpw.mods.fml.common.Loader;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.blocks.*;

import java.util.stream.IntStream;

public class PluginThaumcraft implements ITweakingPostPlugin {
  public static final PluginThaumcraft INSTANCE = new PluginThaumcraft();

  @Override
  public String getModId() {
    return "Thaumcraft";
  }

  @Override
  public String getModName() {
    return "Thaumcraft";
  }

  private Boolean _isModLoadedCache = null;

  @Override
  public boolean isModLoaded() {
    if (_isModLoadedCache == null) {
      return _isModLoadedCache = Loader.isModLoaded(getModId());
    }
    return _isModLoadedCache.booleanValue();
  }

  @Override
  public boolean isIntegrationEnabled() {
    return isModLoaded();
  }

  private static void registerObjectTagNullable(String oreDict, AspectList aspects){
    if(Strings.isNullOrEmpty(oreDict) || aspects == null) return;
    ThaumcraftApi.registerObjectTag(oreDict, aspects);
  }

  private static void registerObjectTagNullable(ItemStack itemStack, AspectList aspects){
    if(itemStack == null || itemStack.getItem() == null || aspects == null) return;
    ThaumcraftApi.registerObjectTag(itemStack, aspects);
  }

  private static void registerObjectTagNullable(ItemStack itemStack, int[] meta, AspectList aspects){
    if(itemStack == null || itemStack.getItem() == null || aspects == null) return;
    ThaumcraftApi.registerObjectTag(itemStack, meta, aspects);
  }

  @Override
  public void tweakModPost() {
    AspectList doorAspects = new AspectList(new ItemStack(Items.wooden_door));
    registerObjectTagNullable(new ItemStack(MyItems.itemDoorAcacia), doorAspects);
    registerObjectTagNullable(new ItemStack(MyItems.itemDoorBirch), doorAspects);
    registerObjectTagNullable(new ItemStack(MyItems.itemDoorDarkOak), doorAspects);
    registerObjectTagNullable(new ItemStack(MyItems.itemDoorSpruce), doorAspects);
    registerObjectTagNullable(new ItemStack(MyItems.itemDoorJungle), doorAspects);

    AspectList boatAspects = new AspectList(new ItemStack(Items.boat));
    registerObjectTagNullable(new ItemStack(MyItems.boatAcacia), boatAspects);
    registerObjectTagNullable(new ItemStack(MyItems.boatBirch), boatAspects);
    registerObjectTagNullable(new ItemStack(MyItems.boatDarkOak), boatAspects);
    registerObjectTagNullable(new ItemStack(MyItems.boatSpruce), boatAspects);
    registerObjectTagNullable(new ItemStack(MyItems.boatJungle), boatAspects);

    AspectList fenceAspects = new AspectList(new ItemStack(Blocks.fence));
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceAcacia), fenceAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceBirch), fenceAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceDarkOak), fenceAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceJungle), fenceAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceSpruce), fenceAspects);

    AspectList fenceGateAspects = new AspectList(new ItemStack(Blocks.fence_gate));
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceGateAcacia), fenceGateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceGateBirch), fenceGateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceGateDarkOak), fenceGateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceGateJungle), fenceGateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.fenceGateSpruce), fenceGateAspects);

    AspectList trapdoorAspects = new AspectList(new ItemStack(Blocks.trapdoor));
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorAcacia), trapdoorAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorBirch), trapdoorAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorDarkOak), trapdoorAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorJungle), trapdoorAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorSpruce), trapdoorAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.trapDoorIron), trapdoorAspects.copy().remove(Aspect.TREE).add(Aspect.METAL, 1));

    AspectList buttonAspects = new AspectList(new ItemStack(Blocks.wooden_button));
    registerObjectTagNullable(new ItemStack(MyBlocks.buttonAcacia), buttonAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.buttonBirch), buttonAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.buttonDarkOak), buttonAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.buttonJungle), buttonAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.buttonSpruce), buttonAspects);

    AspectList pressurePlateAspects = new AspectList(new ItemStack(Blocks.wooden_pressure_plate));
    registerObjectTagNullable(new ItemStack(MyBlocks.pressurePlateAcacia), pressurePlateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.pressurePlateBirch), pressurePlateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.pressurePlateDarkOak), pressurePlateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.pressurePlateJungle), pressurePlateAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.pressurePlateSpruce), pressurePlateAspects);

    registerObjectTagNullable(new ItemStack(MyItems.cookedMutton), new AspectList(new ItemStack(Items.cooked_beef)));
    registerObjectTagNullable(new ItemStack(MyItems.rawMutton), new AspectList(new ItemStack(Items.beef)));


    registerObjectTagNullable(new ItemStack(MyItems.prismarineCrystal), new AspectList().add(Aspect.WATER, 1).add(Aspect.CRYSTAL, 1));
    registerObjectTagNullable(new ItemStack(MyItems.prismarineShard), new AspectList().add(Aspect.WATER, 1).add(Aspect.CRYSTAL, 1));
    AspectList prismarineAspects = new AspectList().add(Aspect.WATER, 1).add(Aspect.EARTH, 1);
    AspectList prismarineBrickAspects = prismarineAspects.copy().add(Aspect.ORDER, 1);
    AspectList prismarineDarkAspects = prismarineAspects.copy().add(Aspect.DARKNESS, 1);
    registerObjectTagNullable("blockPrismarine", prismarineAspects);
    registerObjectTagNullable("blockPrismarineBrick", prismarineBrickAspects);
    registerObjectTagNullable("blockPrismarineDark", prismarineDarkAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.seaLantern), prismarineAspects.copy().add(Aspect.LIGHT, 1));
    registerObjectTagNullable(new ItemStack(MyBlocks.sponge, 1, 0), new AspectList().add(Aspect.VOID, 1));
    registerObjectTagNullable(new ItemStack(MyBlocks.sponge, 1, 1), new AspectList().add(Aspect.WATER, 1).add(Aspect.VOID, 1));


    registerObjectTagNullable(new ItemStack(MyBlocks.redNetherBricks), new AspectList(new ItemStack(Blocks.nether_brick)));
    registerObjectTagNullable(new ItemStack(MyBlocks.netherWartBlock), new AspectList(new ItemStack(Items.nether_wart)));
    registerObjectTagNullable(new ItemStack(MyBlocks.magmaBlock), new AspectList(new ItemStack(Blocks.lava)));
    registerObjectTagNullable(new ItemStack(MyBlocks.smoothQuartz), new AspectList(new ItemStack(Blocks.quartz_block)));


    AspectList endStoneBricksAspects = new AspectList(new ItemStack(Blocks.end_stone)).copy().add(Aspect.ORDER, 1);
    AspectList purpurAspects = endStoneBricksAspects.copy().add(Aspect.PLANT, 1);
    registerObjectTagNullable(new ItemStack(MyBlocks.endStoneBricks), endStoneBricksAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.purpurBlock), purpurAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.purpurPillar), purpurAspects);


    AspectList sandstoneAspects = new AspectList(new ItemStack(Blocks.sandstone));
    registerObjectTagNullable(new ItemStack(MyBlocks.smoothSandstone), sandstoneAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.redSandStone), sandstoneAspects);

    AspectList concreteAspects = new AspectList().add(Aspect.SENSES, 1);
    registerObjectTagNullable(new ItemStack(MyBlocks.concreteBlock), IntStream.rangeClosed(0, BlockConcrete.META_MAX).toArray(), concreteAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.concretePowder), IntStream.rangeClosed(0, BlockConcretePowder.META_MAX).toArray(), concreteAspects);

    AspectList glazedTerracottaAspects = new AspectList().add(Aspect.SENSES, 2).add(Aspect.ENTROPY, 1);
    BlockGlazedTerracotta[] glazedTerracottaList = {
            MyBlocks.glazedTerracottaBlack,
            MyBlocks.glazedTerracottaBlue,
            MyBlocks.glazedTerracottaBrown,
            MyBlocks.glazedTerracottaCyan,
            MyBlocks.glazedTerracottaGray,
            MyBlocks.glazedTerracottaGreen,
            MyBlocks.glazedTerracottaLightBlue,
            MyBlocks.glazedTerracottaLightGray,
            MyBlocks.glazedTerracottaLime,
            MyBlocks.glazedTerracottaMagenta,
            MyBlocks.glazedTerracottaOrange,
            MyBlocks.glazedTerracottaPink,
            MyBlocks.glazedTerracottaPurple,
            MyBlocks.glazedTerracottaRed,
            MyBlocks.glazedTerracottaWhite,
            MyBlocks.glazedTerracottaYellow
    };
    for(final BlockGlazedTerracotta block : glazedTerracottaList){
      registerObjectTagNullable(new ItemStack(block), glazedTerracottaAspects);
    }

    registerObjectTagNullable(new ItemStack(MyBlocks.grassPath), new AspectList().add(Aspect.EARTH, 1).add(Aspect.TRAVEL, 1));
    registerObjectTagNullable(new ItemStack(MyBlocks.coarseDirt), new AspectList().add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1));
    registerObjectTagNullable(new ItemStack(MyBlocks.boneBlock), new AspectList().add(Aspect.DEATH, 9).add(Aspect.HUNGER, 4));

    registerObjectTagNullable(new ItemStack(MyBlocks.barrel), new AspectList(new ItemStack(Blocks.chest)));

    AspectList stoneAspects = new AspectList(new ItemStack(Blocks.stone));
    registerObjectTagNullable(new ItemStack(MyBlocks.stone), IntStream.rangeClosed(1, BlockStone.META_MAX).toArray(), stoneAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.slabStone), stoneAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.smoothStone), stoneAspects.copy().add(Aspect.ORDER, 1));

    AspectList flowerAspects = new AspectList(new ItemStack(Blocks.red_flower));
    registerObjectTagNullable(new ItemStack(MyBlocks.flower), IntStream.rangeClosed(0, BlockNewFlower.MAX_META).toArray(), flowerAspects);
    registerObjectTagNullable(new ItemStack(MyBlocks.witherRose), flowerAspects.copy().add(Aspect.DEATH, 2));

    registerObjectTagNullable(new ItemStack(MyItems.sweetBerries), new AspectList(new ItemStack(Items.apple)));

    registerObjectTagNullable(new ItemStack(MyBlocks.lantern), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.METAL, 1));

    registerObjectTagNullable(new ItemStack(MyItems.armorStand), new AspectList().add(Aspect.TREE, 1).add(Aspect.ARMOR, 2));

    registerObjectTagNullable(new ItemStack(MyItems.suspiciousStew), new AspectList().add(Aspect.HUNGER, 1).add(Aspect.MAGIC, 1));
  }
}
