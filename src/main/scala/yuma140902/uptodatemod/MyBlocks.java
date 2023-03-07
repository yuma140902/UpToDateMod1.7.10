package yuma140902.uptodatemod;

import yuma140902.uptodatemod.blocks.*;
import yuma140902.uptodatemod.blocks.generics.BlockGenericStrippedLog;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.uptodatemod.util.ColorUtil;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.McConst;
import yuma140902.yumalib.api.blocks.*;
import yuma140902.yumalib.api.util.BlockWithMetadata;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static yuma140902.uptodatemod.registry.EnumDisableableFeatures.*;

public final class MyBlocks {
    public static final yuma140902.uptodatemod.blocks.BlockStone stone;
    public static final BlockSmoothStone smoothStone;
    public static final BlockDeepslate deepslate;
    public static final BlockDeepslateCobbled cobbledDeepslate;
    public static final BlockDeepslateBricks deepslateBricks;
    public static final BlockDeepslatePolished polishedDeepslate;
    public static final BlockDeepslateIronOre deepslateIronOre;
    public static final BlockDeepslateGoldOre deepslateGoldOre;
    public static final BlockDeepslateDiamondOre deepslateDiamondOre;
    public static final BlockDeepslateEmeraldOre deepslateEmeraldOre;
    public static final BlockDeepslateCoalOre deepslateCoalOre;
    public static final BlockDeepslateLapisOre deepslateLapisOre;
    public static final BlockDeepslateRedstoneOre deepslateRedstoneOre;
    public static final BlockDeepslateRedstoneOre deepslateRedstoneOreLit;
    public static final BlockFullWood wood;
    public static final BlockGenericStrippedLog strippedLogAcacia;
    public static final BlockGenericStrippedLog strippedLogBirch;
    public static final BlockGenericStrippedLog strippedLogDarkOak;
    public static final BlockGenericStrippedLog strippedLogJungle;
    public static final BlockGenericStrippedLog strippedLogOak;
    public static final BlockGenericStrippedLog strippedLogSpruce;
    public static final BlockDoorAcacia doorAcacia;
    public static final BlockDoorBirch doorBirch;
    public static final BlockDoorDarkOak doorDarkOak;
    public static final BlockDoorJungle doorJungle;
    public static final BlockDoorSpruce doorSpruce;
    public static final BlockGenericFence fenceAcacia;
    public static final BlockGenericFence fenceBirch;
    public static final BlockGenericFence fenceDarkOak;
    public static final BlockGenericFence fenceJungle;
    public static final BlockGenericFence fenceSpruce;
    public static final BlockGenericFenceGate fenceGateAcacia;
    public static final BlockGenericFenceGate fenceGateBirch;
    public static final BlockGenericFenceGate fenceGateDarkOak;
    public static final BlockGenericFenceGate fenceGateJungle;
    public static final BlockGenericFenceGate fenceGateSpruce;
    public static final BlockCoarseDirt coarseDirt;
    public static final BlockPrismarine prismarineBlock;
    public static final BlockPrismarineBricks prismarineBricks;
    public static final BlockDarkPrismarine prismarineDark;
    public static final BlockSeaLantern seaLantern;
    public static final BlockSponge sponge;
    public static final BlockGenericTrapDoor trapDoorAcacia;
    public static final BlockGenericTrapDoor trapDoorBirch;
    public static final BlockGenericTrapDoor trapDoorDarkOak;
    public static final BlockGenericTrapDoor trapDoorJungle;
    public static final BlockGenericTrapDoor trapDoorSpruce;
    public static final BlockGenericTrapDoor trapDoorIron;
    public static final BlockGenericButton buttonAcacia;
    public static final BlockGenericButton buttonBirch;
    public static final BlockGenericButton buttonDarkOak;
    public static final BlockGenericButton buttonJungle;
    public static final BlockGenericButton buttonSpruce;
    public static final BlockGenericPressurePlate pressurePlateAcacia;
    public static final BlockGenericPressurePlate pressurePlateBirch;
    public static final BlockGenericPressurePlate pressurePlateDarkOak;
    public static final BlockGenericPressurePlate pressurePlateJungle;
    public static final BlockGenericPressurePlate pressurePlateSpruce;
    public static final BlockRedNetherBricks redNetherBricks;
    public static final BlockNetherWart netherWartBlock;
    public static final BlockMagma magmaBlock;
    public static final BlockSmoothQuartz smoothQuartz;
    public static final BlockEndStoneBricks endStoneBricks;
    public static final BlockPurpur purpurBlock;
    public static final BlockPurpurPillar purpurPillar;
    public static final BlockBone boneBlock;
    public static final BlockSmoothSandstone smoothSandstone;
    public static final BlockRedSandStone redSandStone;
    public static final BlockConcrete concreteBlock;
    public static final BlockConcretePowder concretePowder;
    public static final BlockGlazedTerracotta glazedTerracottaBlack;
    public static final BlockGlazedTerracotta glazedTerracottaBlue;
    public static final BlockGlazedTerracotta glazedTerracottaBrown;
    public static final BlockGlazedTerracotta glazedTerracottaCyan;
    public static final BlockGlazedTerracotta glazedTerracottaGray;
    public static final BlockGlazedTerracotta glazedTerracottaGreen;
    public static final BlockGlazedTerracotta glazedTerracottaLightBlue;
    public static final BlockGlazedTerracotta glazedTerracottaLightGray;
    public static final BlockGlazedTerracotta glazedTerracottaLime;
    public static final BlockGlazedTerracotta glazedTerracottaMagenta;
    public static final BlockGlazedTerracotta glazedTerracottaOrange;
    public static final BlockGlazedTerracotta glazedTerracottaPink;
    public static final BlockGlazedTerracotta glazedTerracottaPurple;
    public static final BlockGlazedTerracotta glazedTerracottaRed;
    public static final BlockGlazedTerracotta glazedTerracottaWhite;
    public static final BlockGlazedTerracotta glazedTerracottaYellow;
    public static final BlockGrassPath grassPath;
    public static final BlockGenericStairs stairsStone;
    public static final BlockGenericStairs stairsGranite;
    public static final BlockGenericStairs stairsDiorite;
    public static final BlockGenericStairs stairsAndesite;
    public static final BlockGenericStairs stairsPolishedGranite;
    public static final BlockGenericStairs stairsPolishedDiorite;
    public static final BlockGenericStairs stairsPolishedAndesite;
    public static final BlockGenericStairs stairsCobbledDeepslate;
    public static final BlockGenericStairs stairsDeepslateBricks;
    public static final BlockGenericStairs stairsDeepslateTiles;
    public static final BlockGenericStairs stairsPolishedDeepslate;
    public static final BlockGenericStairs stairsRedNetherBricks;
    public static final BlockGenericStairs stairsSmoothQuartz;
    public static final BlockGenericStairs stairsPrismarine;
    public static final BlockGenericStairs stairsPrismarineBricks;
    public static final BlockGenericStairs stairsDarkPrismarine;
    public static final BlockGenericStairs stairsPurpur;
    public static final BlockGenericStairs stairsSmoothSandstone;
    public static final BlockGenericStairs stairsRedSandstone;
    public static final BlockGenericStairs stairsSmoothRedSandstone;
    public static final BlockGenericStairs stairsEndStoneBricks;
    public static final BlockGenericStairs stairsMossyStoneBricks;
    public static final BlockGenericStairs stairsMossyCobbleStone;
    public static final BlockGenericSlab slabStone;
    public static final BlockGenericSlab slabGranite;
    public static final BlockGenericSlab slabDiorite;
    public static final BlockGenericSlab slabAndesite;
    public static final BlockGenericSlab slabPolishedGranite;
    public static final BlockGenericSlab slabPolishedDiorite;
    public static final BlockGenericSlab slabPolishedAndesite;
    public static final BlockGenericSlab slabCobbledDeepslate;
    public static final BlockGenericSlab slabPolishedDeepslate;
    public static final BlockGenericSlab slabDeepslateBricks;
    public static final BlockGenericSlab slabDeepslateTiles;
    public static final BlockGenericSlab slabRedNetherBricks;
    public static final BlockGenericSlab slabSmoothQuartz;
    public static final BlockGenericSlab slabEndStoneBricks;
    public static final BlockGenericSlab slabMossyStoneBricks;
    public static final BlockGenericSlab slabMossyCobbleStone;
    public static final BlockGenericSlab slabCutSandstone;
    public static final BlockGenericSlab slabSmoothSandstone;
    public static final BlockGenericSlab slabRedSandstone;
    public static final BlockGenericSlab slabCutRedSandstone;
    public static final BlockGenericSlab slabSmoothRedSandstone;
    public static final BlockGenericSlab slabPurpur;
    public static final BlockGenericSlab slabPrismarine;
    public static final BlockGenericSlab slabPrismarineBricks;
    public static final BlockGenericSlab slabDarkPrismarine;
    public static final BlockGenericWall wallBricks;
    public static final BlockGenericWall wallStoneBricks;
    public static final BlockGenericWall wallMossyStoneBricks;
    public static final BlockGenericWall wallCobbledDeepslate;
    public static final BlockGenericWall wallPolishedDeepslate;
    public static final BlockGenericWall wallDeepslateBricks;
    public static final BlockGenericWall wallDeepslateTiles;
    public static final BlockGenericWall wallSandstone;
    public static final BlockGenericWall wallRedSandstone;
    public static final BlockGenericWall wallNetherBricks;
    public static final BlockGenericWall wallRedNetherBricks;
    public static final BlockGenericWall wallEndStoneBricks;
    public static final BlockGenericWall wallAndesite;
    public static final BlockGenericWall wallDiorite;
    public static final BlockGenericWall wallGranite;
    public static final BlockGenericWall wallPrismarine;
    public static final BlockGenericWall wallPrismarineBrick;
    public static final BlockGenericWall wallDarkPrismarine;
    public static final BlockBarrel barrel;
    public static final BlockSweetBerryBush sweetBerryBush;
    public static final BlockWitherRose witherRose;
    public static final BlockNewFlower flower;
    public static final BlockUnlimitedPot unlimitedPot;
    public static final BlockLantern lantern;
    private static List<Block> list = new ArrayList<Block>();

    /*
     * !!! 注意 !!!
     * MyItemsのstaticイニシャライザが呼ばれてしまい、
     * 予期しないタイミングで変数の値がnullになってしまうため、
     * MyBlocksのstaticイニシャライザでMyItemsのプロパティを参照してはいけない。
     * また、各ブロックのコンストラクタでもMyItemsのプロパティを参照してはいけない。
     */
    /*
     * MyBlocksのstaticイニシャライザはMyItemsのstaticイニシャライザよりも先に実行されるようにすること
     */
    static {
        ModUpToDateMod.LOGGER.info("Blocks init");

        add(stone = stones.featureEnabled() ? new yuma140902.uptodatemod.blocks.BlockStone() : null);
        add(smoothStone = EnumDisableableFeatures.smoothStone.featureEnabled() ? new BlockSmoothStone() : null);
        if (deepslateStone.featureEnabled()) {
            add(deepslate = new BlockDeepslate());
            add(cobbledDeepslate = new BlockDeepslateCobbled());
            add(deepslateBricks = new BlockDeepslateBricks());
            add(polishedDeepslate = new BlockDeepslatePolished());
            add(deepslateIronOre = new BlockDeepslateIronOre());
            add(deepslateGoldOre = new BlockDeepslateGoldOre());
            add(deepslateDiamondOre = new BlockDeepslateDiamondOre());
            add(deepslateEmeraldOre = new BlockDeepslateEmeraldOre());
            add(deepslateCoalOre = new BlockDeepslateCoalOre());
            add(deepslateLapisOre = new BlockDeepslateLapisOre());
            add(deepslateRedstoneOre = new BlockDeepslateRedstoneOre(false));
            add(deepslateRedstoneOreLit = new BlockDeepslateRedstoneOre(true));
        } else {
            deepslate = null;
            cobbledDeepslate = null;
            deepslateBricks = null;
            polishedDeepslate = null;
            deepslateIronOre = null;
            deepslateGoldOre = null;
            deepslateDiamondOre = null;
            deepslateEmeraldOre = null;
            deepslateCoalOre = null;
            deepslateLapisOre = null;
            deepslateRedstoneOre = null;
            deepslateRedstoneOreLit = null;
        }

        add(wood = EnumDisableableFeatures.wood.featureEnabled() ? new BlockFullWood() : null);

        if (strippedLogs.featureEnabled()) {
            add(strippedLogAcacia = new BlockGenericStrippedLog("stripped_log_acacia", "stripped_acacia_log", McConst.Meta$.MODULE$.PLANK_ACACIA()));
            add(strippedLogBirch = new BlockGenericStrippedLog("stripped_log_birch", "stripped_birch_log", McConst.Meta$.MODULE$.PLANK_BIRCH()));
            add(strippedLogDarkOak = new BlockGenericStrippedLog("stripped_log_dark_oak", "stripped_dark_oak_log", McConst.Meta$.MODULE$.PLANK_DARKOAK()));
            add(strippedLogJungle = new BlockGenericStrippedLog("stripped_log_jungle", "stripped_jungle_log", McConst.Meta$.MODULE$.PLANK_JUNGLE()));
            add(strippedLogOak = new BlockGenericStrippedLog("stripped_log_oak", "stripped_oak_log", McConst.Meta$.MODULE$.PLANK_OAK()));
            add(strippedLogSpruce = new BlockGenericStrippedLog("stripped_log_spruce", "stripped_spruce_log", McConst.Meta$.MODULE$.PLANK_SPRUCE()));
        } else {
            strippedLogAcacia = null;
            strippedLogBirch = null;
            strippedLogDarkOak = null;
            strippedLogJungle = null;
            strippedLogOak = null;
            strippedLogSpruce = null;
        }

        if (doors.featureEnabled()) {
            add(doorAcacia = new BlockDoorAcacia());
            add(doorBirch = new BlockDoorBirch());
            add(doorDarkOak = new BlockDoorDarkOak());
            add(doorJungle = new BlockDoorJungle());
            add(doorSpruce = new BlockDoorSpruce());
        } else {
            doorAcacia = null;
            doorBirch = null;
            doorDarkOak = null;
            doorJungle = null;
            doorSpruce = null;
        }

        if (fences.featureEnabled()) {
            add(fenceAcacia = new BlockGenericFence("planks_acacia", "fence_acacia"));
            add(fenceBirch = new BlockGenericFence("planks_birch", "fence_birch"));
            add(fenceDarkOak = new BlockGenericFence("planks_big_oak", "fence_dark_oak"));
            add(fenceJungle = new BlockGenericFence("planks_jungle", "fence_jungle"));
            add(fenceSpruce = new BlockGenericFence("planks_spruce", "fence_spruce"));
        } else {
            fenceAcacia = null;
            fenceBirch = null;
            fenceDarkOak = null;
            fenceJungle = null;
            fenceSpruce = null;
        }

        if (fenceGates.featureEnabled()) {
            add(fenceGateAcacia = new BlockGenericFenceGate(McConst.Meta$.MODULE$.PLANK_ACACIA(), "fence_gate_acacia"));
            add(fenceGateBirch = new BlockGenericFenceGate(McConst.Meta$.MODULE$.PLANK_BIRCH(), "fence_gate_birch"));
            add(fenceGateDarkOak = new BlockGenericFenceGate(McConst.Meta$.MODULE$.PLANK_DARKOAK(), "fence_gate_dark_oak"));
            add(fenceGateJungle = new BlockGenericFenceGate(McConst.Meta$.MODULE$.PLANK_JUNGLE(), "fence_gate_jungle"));
            add(fenceGateSpruce = new BlockGenericFenceGate(McConst.Meta$.MODULE$.PLANK_SPRUCE(), "fence_gate_spruce"));
        } else {
            fenceGateAcacia = null;
            fenceGateBirch = null;
            fenceGateDarkOak = null;
            fenceGateJungle = null;
            fenceGateSpruce = null;
        }

        add(coarseDirt = EnumDisableableFeatures.coarseDirt.featureEnabled() ? new BlockCoarseDirt() : null);

        if (prismarineStuffs.featureEnabled()) {
            add(prismarineBlock = new BlockPrismarine());
            add(prismarineBricks = new BlockPrismarineBricks());
            add(prismarineDark = new BlockDarkPrismarine());
            add(seaLantern = new BlockSeaLantern());
        } else {
            prismarineBlock = null;
            prismarineBricks = null;
            prismarineDark = null;
            seaLantern = null;
        }
        add(sponge = EnumDisableableFeatures.sponge.featureEnabled() ? new BlockSponge() : null);

        if (woodenTrapdoors.featureEnabled()) {
            add(trapDoorAcacia = TrapdoorBuilder.ofWooden("trap_door_acacia", McConst.Meta$.MODULE$.PLANK_ACACIA()).build());
            add(trapDoorBirch = TrapdoorBuilder.ofWooden("trap_door_birch", McConst.Meta$.MODULE$.PLANK_BIRCH()).build());
            add(trapDoorDarkOak = TrapdoorBuilder.ofWooden("trap_door_dark_oak", McConst.Meta$.MODULE$.PLANK_DARKOAK()).build());
            add(trapDoorJungle = TrapdoorBuilder.ofWooden("trap_door_jungle", McConst.Meta$.MODULE$.PLANK_JUNGLE()).build());
            add(trapDoorSpruce = TrapdoorBuilder.ofWooden("trap_door_spruce", McConst.Meta$.MODULE$.PLANK_SPRUCE()).build());
        } else {
            trapDoorAcacia = null;
            trapDoorBirch = null;
            trapDoorDarkOak = null;
            trapDoorJungle = null;
            trapDoorSpruce = null;
        }
        add(trapDoorIron = ironTrapdoor.featureEnabled() ? TrapdoorBuilder.ofMetal("trap_door_iron").noRotateTex().build() : null);

        if (buttons.featureEnabled()) {
            add(buttonAcacia = new BlockGenericButton(new BlockWithMetadata(Blocks.planks, McConst.Meta$.MODULE$.PLANK_ACACIA()), "button_acacia"));
            add(buttonBirch = new BlockGenericButton(new BlockWithMetadata(Blocks.planks, McConst.Meta$.MODULE$.PLANK_BIRCH()), "button_birch"));
            add(buttonDarkOak = new BlockGenericButton(new BlockWithMetadata(Blocks.planks, McConst.Meta$.MODULE$.PLANK_DARKOAK()), "button_dark_oak"));
            add(buttonJungle = new BlockGenericButton(new BlockWithMetadata(Blocks.planks, McConst.Meta$.MODULE$.PLANK_JUNGLE()), "button_jungle"));
            add(buttonSpruce = new BlockGenericButton(new BlockWithMetadata(Blocks.planks, McConst.Meta$.MODULE$.PLANK_SPRUCE()), "button_spruce"));
        } else {
            buttonAcacia = null;
            buttonBirch = null;
            buttonDarkOak = null;
            buttonJungle = null;
            buttonSpruce = null;
        }

        if (pressurePlates.featureEnabled()) {
            add(pressurePlateAcacia = new BlockGenericPressurePlate(McConst.Meta$.MODULE$.PLANK_ACACIA(), "pressure_plate_acacia"));
            add(pressurePlateBirch = new BlockGenericPressurePlate(McConst.Meta$.MODULE$.PLANK_BIRCH(), "pressure_plate_birch"));
            add(pressurePlateDarkOak = new BlockGenericPressurePlate(McConst.Meta$.MODULE$.PLANK_DARKOAK(), "pressure_plate_dark_oak"));
            add(pressurePlateJungle = new BlockGenericPressurePlate(McConst.Meta$.MODULE$.PLANK_JUNGLE(), "pressure_plate_jungle"));
            add(pressurePlateSpruce = new BlockGenericPressurePlate(McConst.Meta$.MODULE$.PLANK_SPRUCE(), "pressure_plate_spruce"));
        } else {
            pressurePlateAcacia = null;
            pressurePlateBirch = null;
            pressurePlateDarkOak = null;
            pressurePlateJungle = null;
            pressurePlateSpruce = null;
        }

        add(redNetherBricks = EnumDisableableFeatures.redNetherBricks.featureEnabled() ? new BlockRedNetherBricks() : null);
        add(netherWartBlock = EnumDisableableFeatures.netherWartBlock.featureEnabled() ? new BlockNetherWart() : null);
        add(magmaBlock = EnumDisableableFeatures.magmaBlock.featureEnabled() ? new BlockMagma() : null);
        add(smoothQuartz = new BlockSmoothQuartz());

        add(endStoneBricks = EnumDisableableFeatures.endstoneBricks.featureEnabled() ? new BlockEndStoneBricks() : null);
        add(purpurBlock = purpurStuffs.featureEnabled() ? new BlockPurpur() : null);
        add(purpurPillar = purpurStuffs.featureEnabled() ? new BlockPurpurPillar() : null);

        add(boneBlock = boneBlockAndFossile.featureEnabled() ? new BlockBone() : null);

        add(smoothSandstone = new BlockSmoothSandstone());
        add(redSandStone = EnumDisableableFeatures.redSandstone.featureEnabled() ? new BlockRedSandStone() : null);

        if (concreteAndConcretePowder.featureEnabled()) {
            add(concreteBlock = new BlockConcrete());
            add(concretePowder = new BlockConcretePowder());
        } else {
            concreteBlock = null;
            concretePowder = null;
        }

        if (glazedTerracotta.featureEnabled()) {
            add(glazedTerracottaBlack = new BlockGlazedTerracotta(ColorUtil.META_BLACK()));
            add(glazedTerracottaBlue = new BlockGlazedTerracotta(ColorUtil.META_BLUE()));
            add(glazedTerracottaBrown = new BlockGlazedTerracotta(ColorUtil.META_BROWN()));
            add(glazedTerracottaCyan = new BlockGlazedTerracotta(ColorUtil.META_CYAN()));
            add(glazedTerracottaGray = new BlockGlazedTerracotta(ColorUtil.META_GRAY()));
            add(glazedTerracottaGreen = new BlockGlazedTerracotta(ColorUtil.META_GREEN()));
            add(glazedTerracottaLightBlue = new BlockGlazedTerracotta(ColorUtil.META_LIGHT_BLUE()));
            add(glazedTerracottaLightGray = new BlockGlazedTerracotta(ColorUtil.META_LIGHT_GRAY()));
            add(glazedTerracottaLime = new BlockGlazedTerracotta(ColorUtil.META_LIME()));
            add(glazedTerracottaMagenta = new BlockGlazedTerracotta(ColorUtil.META_MAGENTA()));
            add(glazedTerracottaOrange = new BlockGlazedTerracotta(ColorUtil.META_ORANGE()));
            add(glazedTerracottaPink = new BlockGlazedTerracotta(ColorUtil.META_PINK()));
            add(glazedTerracottaPurple = new BlockGlazedTerracotta(ColorUtil.META_PURPLE()));
            add(glazedTerracottaRed = new BlockGlazedTerracotta(ColorUtil.META_RED()));
            add(glazedTerracottaWhite = new BlockGlazedTerracotta(ColorUtil.META_WHITE()));
            add(glazedTerracottaYellow = new BlockGlazedTerracotta(ColorUtil.META_YELLOW()));
        } else {
            glazedTerracottaBlack = null;
            glazedTerracottaBlue = null;
            glazedTerracottaBrown = null;
            glazedTerracottaCyan = null;
            glazedTerracottaGray = null;
            glazedTerracottaGreen = null;
            glazedTerracottaLightBlue = null;
            glazedTerracottaLightGray = null;
            glazedTerracottaLime = null;
            glazedTerracottaMagenta = null;
            glazedTerracottaOrange = null;
            glazedTerracottaPink = null;
            glazedTerracottaPurple = null;
            glazedTerracottaRed = null;
            glazedTerracottaWhite = null;
            glazedTerracottaYellow = null;
        }

        add(grassPath = EnumDisableableFeatures.grassPath.featureEnabled() ? new BlockGrassPath() : null);

        if (allKindsOfStairs.featureEnabled()) {
            add(stairsStone = StairsBuilder.of(Blocks.stone, "stairs_stone").build());
            add(stairsGranite = StairsBuilder.of(MyBlocks.stone, "stairs_granite").meta(BlockStone.META_GRANITE).build());
            add(stairsDiorite = StairsBuilder.of(MyBlocks.stone, "stairs_diorite").meta(BlockStone.META_DIORITE).build());
            add(stairsAndesite = StairsBuilder.of(MyBlocks.stone, "stairs_andesite").meta(BlockStone.META_ANDESITE).build());
            add(stairsPolishedGranite = StairsBuilder.of(MyBlocks.stone, "stairs_polished_granite").meta(BlockStone.META_POLISHED_GRANITE).build());
            add(stairsPolishedDiorite = StairsBuilder.of(MyBlocks.stone, "stairs_polished_diorite").meta(BlockStone.META_POLISHED_DIORITE).build());
            add(stairsPolishedAndesite = StairsBuilder.of(MyBlocks.stone, "stairs_polished_andesite").meta(BlockStone.META_POLISHED_ANDESITE).build());
            add(stairsCobbledDeepslate = StairsBuilder.of(MyBlocks.cobbledDeepslate, "stairs_cobbled_deepslate").build());
            add(stairsPolishedDeepslate = StairsBuilder.of(MyBlocks.polishedDeepslate, "stairs_polished_deepslate").meta(BlockDeepslatePolished.META_POLISHED).build());
            add(stairsDeepslateBricks = StairsBuilder.of(MyBlocks.deepslateBricks, "stairs_deepslate_bricks").meta(BlockDeepslateBricks.META_BRICKS).build());
            add(stairsDeepslateTiles = StairsBuilder.of(MyBlocks.deepslateBricks, "stairs_deepslate_tiles").meta(BlockDeepslateBricks.META_TILES).build());
            add(stairsRedNetherBricks = StairsBuilder.of(MyBlocks.redNetherBricks, "stairs_red_nether_bricks").build());
            add(stairsSmoothQuartz = StairsBuilder.of(MyBlocks.smoothQuartz, "stairs_smooth_quartz").build());
            add(stairsPrismarine = StairsBuilder.of(MyBlocks.prismarineBlock, "stairs_prismarine").build());
            add(stairsPrismarineBricks = StairsBuilder.of(MyBlocks.prismarineBricks, "stairs_prismarine_bricks").build());
            add(stairsDarkPrismarine = StairsBuilder.of(MyBlocks.prismarineDark, "stairs_dark_prismarine").build());
            add(stairsPurpur = StairsBuilder.of(MyBlocks.purpurBlock, "stairs_purpur").build());
            add(stairsSmoothSandstone = StairsBuilder.of(MyBlocks.smoothSandstone, "stairs_smooth_sandstone").build());
            add(stairsRedSandstone = StairsBuilder.of(MyBlocks.redSandStone, "stairs_red_sandstone").meta(BlockRedSandStone.META_NORMAL).build());
            add(stairsSmoothRedSandstone = StairsBuilder.of(MyBlocks.redSandStone, "stairs_smooth_red_sandstone").meta(BlockRedSandStone.META_SMOOTH).build());
            add(stairsEndStoneBricks = StairsBuilder.of(MyBlocks.endStoneBricks, "stairs_end_stone_bricks").build());
            add(stairsMossyStoneBricks = StairsBuilder.of(Blocks.stonebrick, "stairs_mossy_stone_bricks").meta(1).build());
            add(stairsMossyCobbleStone = StairsBuilder.of(Blocks.mossy_cobblestone, "stairs_mossy_cobblestone").build());
        } else {
            stairsStone = null;
            stairsGranite = null;
            stairsDiorite = null;
            stairsAndesite = null;
            stairsPolishedGranite = null;
            stairsPolishedDiorite = null;
            stairsPolishedAndesite = null;
            stairsCobbledDeepslate = null;
            stairsPolishedDeepslate = null;
            stairsDeepslateBricks = null;
            stairsDeepslateTiles = null;
            stairsRedNetherBricks = null;
            stairsSmoothQuartz = null;
            stairsPrismarine = null;
            stairsPrismarineBricks = null;
            stairsDarkPrismarine = null;
            stairsPurpur = null;
            stairsSmoothSandstone = null;
            stairsRedSandstone = null;
            stairsSmoothRedSandstone = null;
            stairsEndStoneBricks = null;
            stairsMossyStoneBricks = null;
            stairsMossyCobbleStone = null;
        }

        if (allKindsOfSlabs.featureEnabled()) {
            add(slabStone = new BlockStoneSlab());
            add(slabGranite = SlabBuilder.of(MyBlocks.stone, "slab_granite").meta(BlockStone.META_GRANITE).build());
            add(slabDiorite = SlabBuilder.of(MyBlocks.stone, "slab_diorite").meta(BlockStone.META_DIORITE).build());
            add(slabAndesite = SlabBuilder.of(MyBlocks.stone, "slab_andesite").meta(BlockStone.META_ANDESITE).build());
            add(slabPolishedGranite
                    = SlabBuilder.of(MyBlocks.stone, "slab_polished_granite")
                    .meta(BlockStone.META_POLISHED_GRANITE)
                    .specialSideTexture(StringUtil.name.domainedTexture("polished_granite_slab_side")).build());
            add(slabPolishedDiorite
                    = SlabBuilder.of(MyBlocks.stone, "slab_polished_diorite")
                    .meta(BlockStone.META_POLISHED_DIORITE)
                    .specialSideTexture(StringUtil.name.domainedTexture("polished_diorite_slab_side")).build());
            add(slabPolishedAndesite
                    = SlabBuilder.of(MyBlocks.stone, "slab_polished_andesite")
                    .meta(BlockStone.META_POLISHED_ANDESITE)
                    .specialSideTexture(StringUtil.name.domainedTexture("polished_andesite_slab_side")).build());
            add(slabCobbledDeepslate = SlabBuilder.of(MyBlocks.cobbledDeepslate, "slab_cobbled_deepslate").build());
            add(slabPolishedDeepslate = SlabBuilder.of(MyBlocks.polishedDeepslate, "slab_polished_deepslate")
                    .meta(BlockDeepslatePolished.META_POLISHED).build());
            add(slabDeepslateBricks = SlabBuilder.of(MyBlocks.deepslateBricks, "slab_deepslate_bricks")
                    .meta(BlockDeepslateBricks.META_BRICKS).build());
            add(slabDeepslateTiles = SlabBuilder.of(MyBlocks.deepslateBricks, "slab_deepslate_tiles")
                    .meta(BlockDeepslateBricks.META_TILES).build());
            add(slabRedNetherBricks = SlabBuilder.of(MyBlocks.redNetherBricks, "slab_red_nether_bricks").build());
            add(slabSmoothQuartz = SlabBuilder.of(MyBlocks.smoothQuartz, "slab_smooth_quartz").build());
            add(slabEndStoneBricks = SlabBuilder.of(MyBlocks.endStoneBricks, "slab_end_stone_bricks").build());
            add(slabMossyStoneBricks = SlabBuilder.of(Blocks.stonebrick, "slab_mossy_stone_bricks").meta(1).build());
            add(slabMossyCobbleStone = SlabBuilder.of(Blocks.mossy_cobblestone, "slab_mossy_cobblestone").build());
            add(slabCutSandstone = SlabBuilder.of(Blocks.sandstone, "slab_cut_sandstone").meta(McConst.Meta$.MODULE$.SANDSTONE_CUT()).build());
            add(slabSmoothSandstone = SlabBuilder.of(MyBlocks.smoothSandstone, "slab_smooth_sandstone").build());
            add(slabRedSandstone = SlabBuilder.of(MyBlocks.redSandStone, "slab_red_sandstone").meta(BlockRedSandStone.META_NORMAL).build());
            add(slabCutRedSandstone = SlabBuilder.of(MyBlocks.redSandStone, "slab_cut_red_sandstone").meta(BlockRedSandStone.META_CUT).build());
            add(slabSmoothRedSandstone = SlabBuilder.of(MyBlocks.redSandStone, "slab_smooth_red_sandstone").meta(BlockRedSandStone.META_SMOOTH).build());
            add(slabPurpur = SlabBuilder.of(MyBlocks.purpurBlock, "slab_purpur").build());
            add(slabPrismarine = SlabBuilder.of(MyBlocks.prismarineBlock, "slab_prismarine").build());
            add(slabPrismarineBricks = SlabBuilder.of(MyBlocks.prismarineBricks, "slab_prismarine_bricks").build());
            add(slabDarkPrismarine = SlabBuilder.of(MyBlocks.prismarineDark, "slab_dark_prismairne").build());
        } else {
            slabStone = null;
            slabGranite = null;
            slabDiorite = null;
            slabAndesite = null;
            slabPolishedGranite = null;
            slabPolishedDiorite = null;
            slabPolishedAndesite = null;
            slabCobbledDeepslate = null;
            slabPolishedDeepslate = null;
            slabDeepslateBricks = null;
            slabDeepslateTiles = null;
            slabRedNetherBricks = null;
            slabSmoothQuartz = null;
            slabEndStoneBricks = null;
            slabMossyStoneBricks = null;
            slabMossyCobbleStone = null;
            slabCutSandstone = null;
            slabSmoothSandstone = null;
            slabRedSandstone = null;
            slabCutRedSandstone = null;
            slabSmoothRedSandstone = null;
            slabPurpur = null;
            slabPrismarine = null;
            slabPrismarineBricks = null;
            slabDarkPrismarine = null;
        }

        if (allKindsOfWalls.featureEnabled()) {
            add(wallBricks = WallBuilder.of(Blocks.brick_block, "wall_bricks").build());
            add(wallStoneBricks = WallBuilder.of(Blocks.stonebrick, "wall_stone_bricks").build());
            add(wallMossyStoneBricks = WallBuilder.of(Blocks.stonebrick, "wall_mossy_stone_bricks").meta(1).build());
            add(wallCobbledDeepslate = WallBuilder.of(MyBlocks.cobbledDeepslate, "wall_cobbled_deepslate").build());
            add(wallPolishedDeepslate = WallBuilder.of(MyBlocks.polishedDeepslate, "wall_polished_deepslate")
                    .meta(BlockDeepslatePolished.META_POLISHED).build());
            add(wallDeepslateBricks = WallBuilder.of(MyBlocks.deepslateBricks, "wall_deepslate_bricks")
                    .meta(BlockDeepslateBricks.META_BRICKS).build());
            add(wallDeepslateTiles = WallBuilder.of(MyBlocks.deepslateBricks, "wall_deepslate_tiles")
                    .meta(BlockDeepslateBricks.META_TILES).build());
            add(wallSandstone = WallBuilder.of(Blocks.sandstone, "wall_sandstone").build());
            add(wallRedSandstone = WallBuilder.of(MyBlocks.redSandStone, "wall_red_sandstone").build());
            add(wallNetherBricks = WallBuilder.of(Blocks.nether_brick, "wall_nether_bricks").build());
            add(wallRedNetherBricks = WallBuilder.of(MyBlocks.redNetherBricks, "wall_red_nether_bricks").build());
            add(wallEndStoneBricks = WallBuilder.of(MyBlocks.endStoneBricks, "wall_end_stone_bricks").build());
            add(wallAndesite = WallBuilder.of(MyBlocks.stone, "wall_andesite").meta(BlockStone.META_ANDESITE).build());
            add(wallDiorite = WallBuilder.of(MyBlocks.stone, "wall_diorite").meta(BlockStone.META_DIORITE).build());
            add(wallGranite = WallBuilder.of(MyBlocks.stone, "wall_granite").meta(BlockStone.META_GRANITE).build());
            add(wallPrismarine = WallBuilder.of(prismarineBlock, "wall_prismarine").build());
            add(wallPrismarineBrick = WallBuilder.of(prismarineBricks, "wall_prismarine_brick").build());
            add(wallDarkPrismarine = WallBuilder.of(prismarineDark, "wall_dark_prismarine").build());
        } else {
            wallBricks = null;
            wallStoneBricks = null;
            wallMossyStoneBricks = null;
            wallCobbledDeepslate = null;
            wallPolishedDeepslate = null;
            wallDeepslateBricks = null;
            wallDeepslateTiles = null;
            wallSandstone = null;
            wallRedSandstone = null;
            wallNetherBricks = null;
            wallRedNetherBricks = null;
            wallEndStoneBricks = null;
            wallAndesite = null;
            wallDiorite = null;
            wallGranite = null;
            wallPrismarine = null;
            wallPrismarineBrick = null;
            wallDarkPrismarine = null;
        }

        add(barrel = EnumDisableableFeatures.barrel.featureEnabled() ? new BlockBarrel() : null);

        add(sweetBerryBush = sweetBerry.featureEnabled() ? new BlockSweetBerryBush() : null);

        add(witherRose = EnumDisableableFeatures.witherRose.featureEnabled() ? new BlockWitherRose() : null);
        add(flower = EnumDisableableFeatures.flower.featureEnabled() ? new BlockNewFlower() : null);
        add(unlimitedPot = new BlockUnlimitedPot());

        add(lantern = EnumDisableableFeatures.lantern.featureEnabled() ? new BlockLantern() : null);
    }

    private MyBlocks() {
    }

    private static void add(Block block) {
        if (block != null) list.add(block);
    }

    public static Iterator<Block> iterator() {
        return list.iterator();
    }

    public static void register() {
        Iterator<Block> iterator = iterator();
        while (iterator.hasNext()) {
            Block block = iterator.next();
            if (block instanceof IRegisterable) {
                ((IRegisterable) block).register();
            }
        }
    }

}
