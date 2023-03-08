package yuma140902.uptodatemod.registry;

import javax.annotation.Nullable;

public enum EnumDisableableFeatures implements IDisableableFeature {
	stones("granite, andesite, diorite, polished ones, stairs, slabs, and walls"),
	deepslateStone,
	wood,
	smoothStone,
	armorStand,
	doors,
	woodenTrapdoors,
	fences,
	fenceGates,
	buttons,
	pressurePlates,
	boats,
	strippedLogs,
	ironTrapdoor,
	grassPath,
	coarseDirt,
	prismarineStuffs("prismarine block, prismarine bricks, dark prismarine, prismarine shard, and prismarine crystal"),
	sponge,
	redNetherBricks,
	netherWartBlock,
	endstoneBricks,
	purpurStuffs,
	boneBlockAndFossile,
	magmaBlock,
	redSandstone,
	mutton,
	concreteAndConcretePowder,
	glazedTerracotta,
	ironNugget,
	barrel,
	sweetBerry,
	witherRose,
	flower,
	plainDye,
	suspiciousStew,
	newNoteBlockInstruments,
	lantern,
	allKindsOfStairs,
	allKindsOfSlabs,
	allKindsOfWalls;
	
	private @Nullable String comment;
	public @Nullable String getComment() {
		return comment;
	}
	
	private EnumDisableableFeatures() {
		this(null);
	}
	private EnumDisableableFeatures(@Nullable String comment) {
		this.comment = comment;
	}
	
	@Override public boolean featureEnabled() {
		return DisabledFeaturesRegistry.INSTANCE.isEnabled(this);
	}
}
