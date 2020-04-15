package yuma140902.uptodatemod;

import static yuma140902.uptodatemod.registry.EnumDisableableFeatures.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.item.Item;
import yuma140902.uptodatemod.items.ItemArmorStand;
import yuma140902.uptodatemod.items.ItemBoatAcacia;
import yuma140902.uptodatemod.items.ItemBoatBirch;
import yuma140902.uptodatemod.items.ItemBoatDarkOak;
import yuma140902.uptodatemod.items.ItemBoatJungle;
import yuma140902.uptodatemod.items.ItemBoatSpruce;
import yuma140902.uptodatemod.items.ItemCookedMutton;
import yuma140902.uptodatemod.items.ItemDoorAcacia;
import yuma140902.uptodatemod.items.ItemDoorBirch;
import yuma140902.uptodatemod.items.ItemDoorDarkOak;
import yuma140902.uptodatemod.items.ItemDoorJungle;
import yuma140902.uptodatemod.items.ItemDoorSpruce;
import yuma140902.uptodatemod.items.ItemIronNugget;
import yuma140902.uptodatemod.items.ItemPlainDye;
import yuma140902.uptodatemod.items.ItemPrismarineCrystals;
import yuma140902.uptodatemod.items.ItemPrismarineShard;
import yuma140902.uptodatemod.items.ItemRawMutton;
import yuma140902.uptodatemod.items.ItemSuspiciousStew;
import yuma140902.uptodatemod.items.ItemSweetBerries;
import yuma140902.uptodatemod.registry.DisabledFeaturesRegistry;
import yuma140902.uptodatemod.registry.EnumDisableableFeatures;
import yuma140902.yumalib.api.IRegisterable;

public final class MyItems {
	private MyItems() {}
	
	private static boolean isEnabled(EnumDisableableFeatures feature) {
		return DisabledFeaturesRegistry.INSTANCE.isEnabled(feature);
	}
	
	private static void add(@Nullable Item item) {
		if(item != null) list.add(item);
	}
	
	private static List<Item> list = new ArrayList<Item>();
	
	public static Iterator<Item> iterator(){
		return list.iterator();
	}
	
	public static void register() {
		Iterator<Item> iterator = iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			if(item instanceof IRegisterable) {
				((IRegisterable) item).register();
			}
		}
	}
	
	public static final ItemDoorAcacia itemDoorAcacia;
	public static final ItemDoorBirch itemDoorBirch;
	public static final ItemDoorDarkOak itemDoorDarkOak;
	public static final ItemDoorJungle itemDoorJungle;
	public static final ItemDoorSpruce itemDoorSpruce;
	
	public static final ItemPrismarineCrystals prismarineCrystal;
	public static final ItemPrismarineShard prismarineShard;
	
	public static final ItemBoatAcacia boatAcacia;
	public static final ItemBoatBirch boatBirch;
	public static final ItemBoatDarkOak boatDarkOak;
	public static final ItemBoatJungle boatJungle;
	public static final ItemBoatSpruce boatSpruce;
	
	public static final ItemIronNugget ironNugget;
	
	public static final ItemRawMutton rawMutton;
	public static final ItemCookedMutton cookedMutton;
	
	public static final ItemArmorStand armorStand;
	
	public static final ItemSweetBerries sweetBerries;
	
	public static final ItemPlainDye dye;
	
	public static final ItemSuspiciousStew suspiciousStew;
	
	/*
	 * MyItemsのstaticイニシャライザはMyBlocksのstaticイニシャライザよりも後に実行されるようにすること
	 */
	static {
		ModUpToDateMod.LOGGER.info("Items init");
		
		if(isEnabled(doors)) {
			add(itemDoorAcacia = new ItemDoorAcacia());
			add(itemDoorBirch = new ItemDoorBirch());
			add(itemDoorDarkOak = new ItemDoorDarkOak());
			add(itemDoorJungle = new ItemDoorJungle());
			add(itemDoorSpruce = new ItemDoorSpruce());
		}
		else {
			itemDoorAcacia = null;
			itemDoorBirch = null;
			itemDoorDarkOak = null;
			itemDoorJungle = null;
			itemDoorSpruce = null;
		}
		
		if(isEnabled(prismarineStuffs)) {
			add(prismarineCrystal = new ItemPrismarineCrystals());
			add(prismarineShard = new ItemPrismarineShard());
		}
		else {
			prismarineCrystal = null;
			prismarineShard = null;
		}
		
		if(isEnabled(boats)) {
			add(boatAcacia = new ItemBoatAcacia());
			add(boatBirch = new ItemBoatBirch());
			add(boatDarkOak = new ItemBoatDarkOak());
			add(boatJungle = new ItemBoatJungle());
			add(boatSpruce = new ItemBoatSpruce());
		}
		else {
			boatAcacia = null;
			boatBirch = null;
			boatDarkOak = null;
			boatJungle = null;
			boatSpruce = null;
		}
		
		add(ironNugget = isEnabled(EnumDisableableFeatures.ironNugget) ? new ItemIronNugget() : null);
		
		if(isEnabled(mutton)) {
			add(rawMutton = new ItemRawMutton());
			add(cookedMutton = new ItemCookedMutton());
		}
		else {
			rawMutton = null;
			cookedMutton = null;
		}
		
		add(armorStand = isEnabled(EnumDisableableFeatures.armorStand) ? new ItemArmorStand() : null);
		add(sweetBerries = isEnabled(sweetBerry) ? new ItemSweetBerries() : null);
		add(dye = isEnabled(plainDye) ? new ItemPlainDye() : null);
		add(suspiciousStew = isEnabled(EnumDisableableFeatures.suspiciousStew) ? new ItemSuspiciousStew() : null);
	}

}
