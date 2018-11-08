package yuma140902.uptodatemod;

import net.minecraft.item.Item;
import yuma140902.uptodatemod.items.ItemBoatAcacia;
import yuma140902.uptodatemod.items.ItemBoatBirch;
import yuma140902.uptodatemod.items.ItemBoatDarkOak;
import yuma140902.uptodatemod.items.ItemBoatJungle;
import yuma140902.uptodatemod.items.ItemBoatSpruce;
import yuma140902.uptodatemod.items.ItemDoorAcacia;
import yuma140902.uptodatemod.items.ItemDoorBirch;
import yuma140902.uptodatemod.items.ItemDoorDarkOak;
import yuma140902.uptodatemod.items.ItemDoorJungle;
import yuma140902.uptodatemod.items.ItemDoorSpruce;
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
	
	public static final ItemBoatAcacia boatAcacia = new ItemBoatAcacia();
	public static final ItemBoatBirch boatBirch = new ItemBoatBirch();
	public static final ItemBoatDarkOak boatDarkOak = new ItemBoatDarkOak();
	public static final ItemBoatJungle boatJungle = new ItemBoatJungle();
	public static final ItemBoatSpruce boatSpruce = new ItemBoatSpruce();
}
