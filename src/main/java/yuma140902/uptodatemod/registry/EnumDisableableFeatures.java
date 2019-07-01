package yuma140902.uptodatemod.registry;

import javax.annotation.Nullable;

public enum EnumDisableableFeatures {
	stones("granite, andesite, diorite, polished ones, stairs, slabs, and walls"),
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
}
