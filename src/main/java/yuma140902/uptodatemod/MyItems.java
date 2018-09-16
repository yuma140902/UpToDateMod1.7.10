package yuma140902.uptodatemod;

import net.minecraft.item.Item;
import yuma140902.uptodatemod.items.ItemDoorAcacia;
import yuma140902.uptodatemod.items.ItemDoorBirch;
import yuma140902.uptodatemod.items.ItemDoorDarkOak;
import yuma140902.uptodatemod.items.ItemDoorJungle;
import yuma140902.uptodatemod.items.ItemDoorSpruce;
import yuma140902.uptodatemod.items.ocean_monument.PrismarineCrystals;

public final class MyItems {
	private MyItems() {}
	
	public static void register() {
		((IRegisterable) itemDoorAcacia).register();
		((IRegisterable) itemDoorBirch).register();
		((IRegisterable) itemDoorDarkOak).register();
		((IRegisterable) itemDoorJungle).register();
		((IRegisterable) itemDoorSpruce).register();
		((IRegisterable) prismarineCrystal).register();
	}
	
	public static final Item itemDoorAcacia = new ItemDoorAcacia();
	public static final Item itemDoorBirch = new ItemDoorBirch();
	public static final Item itemDoorDarkOak = new ItemDoorDarkOak();
	public static final Item itemDoorJungle = new ItemDoorJungle();
	public static final Item itemDoorSpruce = new ItemDoorSpruce();
	
	public static final Item prismarineCrystal = new PrismarineCrystals();
}
