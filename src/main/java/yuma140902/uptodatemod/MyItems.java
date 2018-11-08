package yuma140902.uptodatemod;

import net.minecraft.item.Item;
import yuma140902.uptodatemod.entity.item.EntityModBoatBase.Type;
import yuma140902.uptodatemod.items.ItemDoorAcacia;
import yuma140902.uptodatemod.items.ItemDoorBirch;
import yuma140902.uptodatemod.items.ItemDoorDarkOak;
import yuma140902.uptodatemod.items.ItemDoorJungle;
import yuma140902.uptodatemod.items.ItemDoorSpruce;
import yuma140902.uptodatemod.items.ItemModBoatBase;
import yuma140902.uptodatemod.items.ocean_monument.PrismarineCrystals;
import yuma140902.uptodatemod.items.ocean_monument.PrismarineShard;

public final class MyItems {
	private MyItems() {}
	
	public static void register() {
		((IRegisterable) itemDoorAcacia).register();
		((IRegisterable) itemDoorBirch).register();
		((IRegisterable) itemDoorDarkOak).register();
		((IRegisterable) itemDoorJungle).register();
		((IRegisterable) itemDoorSpruce).register();
		
		((IRegisterable) prismarineCrystal).register();
		prismarineShard.register();
		
		boatAcacia.register();
		boatBirch.register();
		boatDarkOak.register();
		boatJungle.register();
		boatSpruce.register();
	}
	
	public static final Item itemDoorAcacia = new ItemDoorAcacia();
	public static final Item itemDoorBirch = new ItemDoorBirch();
	public static final Item itemDoorDarkOak = new ItemDoorDarkOak();
	public static final Item itemDoorJungle = new ItemDoorJungle();
	public static final Item itemDoorSpruce = new ItemDoorSpruce();
	
	public static final Item prismarineCrystal = new PrismarineCrystals();
	public static final PrismarineShard prismarineShard = new PrismarineShard();
	
	public static final ItemModBoatBase boatAcacia = new ItemModBoatBase("item_boat_acacia", Type.ACACIA);
	public static final ItemModBoatBase boatBirch = new ItemModBoatBase("item_boat_birch", Type.BIRCH);
	public static final ItemModBoatBase boatDarkOak = new ItemModBoatBase("item_boat_dark_oak", Type.DARK_OAK);
	public static final ItemModBoatBase boatJungle = new ItemModBoatBase("item_boat_jungle", Type.JUNGLE);
	public static final ItemModBoatBase boatSpruce = new ItemModBoatBase("item_boat_spruce", Type.SPRUCE);
}
