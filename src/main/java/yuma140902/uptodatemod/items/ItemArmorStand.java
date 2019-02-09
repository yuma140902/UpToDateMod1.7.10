package yuma140902.uptodatemod.items;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.entity.Rotations;
import yuma140902.uptodatemod.entity.item.EntityArmorStand;

public class ItemArmorStand extends Item implements IRegisterable, IHasRecipes {

	public ItemArmorStand() {
		setMaxStackSize(16);
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(ModUpToDateMod.MOD_ID + ".armor_stand");
		this.setTextureName(ModUpToDateMod.MOD_ID + ":armor_stand");
		GameRegistry.registerItem(this, "armor_stand");
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(
				new ItemStack(this),
				"III",
				" I ",
				"IOI",
				'I', Items.stick,
				'O', new ItemStack(Blocks.stone_slab, 1, 0)
				);
		
		GameRegistry.addRecipe(
				new ItemStack(this),
				"III",
				" I ",
				"IOI",
				'I', Items.stick,
				'O', MyBlocks.slabStone
				);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side == 0)
			return false;
		else {
			if (side == 1)
				y++;
			if (side == 2)
				z--;
			if (side == 3)
				z++;
			if (side == 4)
				x--;
			if (side == 5)
				x++;

			if (!player.canPlayerEdit(x, y, z, side, stack))
				return false;
			else {
				boolean flag1 = !world.isAirBlock(x, y, z) && !world.getBlock(x, y, z).isReplaceable(world, x, y, z);
				flag1 |= !world.isAirBlock(x, y + 1, z) && !world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z);

				if (flag1)
					return false;
				else {
					double d0 = x;
					double d1 = y;
					double d2 = z;
					List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));

					if (list.size() > 0)
						return false;
					else {
						if (!world.isRemote) {
							world.setBlockToAir(x, y, z);
							world.setBlockToAir(x, y + 1, z);
							EntityArmorStand stand = new EntityArmorStand(world, d0 + 0.5D, d1, d2 + 0.5D);
							float f3 = MathHelper.floor_float((MathHelper.wrapAngleTo180_float(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
							stand.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f3, 0.0F);
							applyRandomRotations(stand, world.rand);
							NBTTagCompound nbt = stack.getTagCompound();

							if (nbt != null && nbt.hasKey("EntityTag", 10)) {
								NBTTagCompound nbt1 = new NBTTagCompound();
								stand.writeToNBTOptional(nbt1);
								merge(nbt1, nbt.getCompoundTag("EntityTag"));
								stand.readFromNBT(nbt1);
							}

							world.spawnEntityInWorld(stand);
						}

						stack.stackSize--;
						return true;
					}
				}
			}
		}
	}

	private void applyRandomRotations(EntityArmorStand armorStand, Random rand) {
		Rotations rotations = armorStand.getHeadRotation();
		float f = rand.nextFloat() * 5.0F;
		float f1 = rand.nextFloat() * 20.0F - 10.0F;
		Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
		armorStand.setHeadRotation(rotations1);
		rotations = armorStand.getBodyRotation();
		f = rand.nextFloat() * 10.0F - 5.0F;
		rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
		armorStand.setBodyRotation(rotations1);
	}

	@SuppressWarnings("unchecked")
	public void merge(NBTTagCompound nbt, NBTTagCompound other) {
		Iterator<String> iterator = other.func_150296_c().iterator();

		while (iterator.hasNext()) {
			String s = iterator.next();
			NBTBase nbtbase = other.getTag(s);

			if (nbtbase.getId() == 10) {
				if (nbt.hasKey(s, 10)) {
					NBTTagCompound nbttagcompound1 = nbt.getCompoundTag(s);
					merge(nbttagcompound1, (NBTTagCompound) nbtbase);
				} else
					nbt.setTag(s, nbtbase.copy());
			} else
				nbt.setTag(s, nbtbase.copy());
		}
	}
}
