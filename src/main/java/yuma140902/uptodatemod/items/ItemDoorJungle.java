package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.Recipes;
import yuma140902.uptodatemod.registry.RecipeRegister;
import yuma140902.uptodatemod.util.StringUtil;
import yuma140902.yumalib.api.IHasRecipes;
import yuma140902.yumalib.api.IRegisterable;

public class ItemDoorJungle extends net.minecraft.item.ItemDoor implements IRegisterable, IHasRecipes {
  public ItemDoorJungle()
  {
  	super(Material.wood);
  	this.maxStackSize = 64;
  }
  
  @Override
	public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName("door_jungle"));
		this.setTextureName(StringUtil.getDomainedTextureName("jungle_door"));
		GameRegistry.registerItem(this, "itemDoorJungle");
  }
  
  @Override
  public void registerRecipes() {
  	RecipeRegister.addShaped(
				new ItemStack(MyItems.itemDoorJungle, 3, 0),
				"##",
				"##",
				"##",
				'#', Recipes.PLANK_JUNGLE
				);
  }

  /**
   * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
   * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
   */
  @Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
  {
      if (p_77648_7_ != 1)
      {
          return false;
      }
      else
      {
          ++p_77648_5_;
          Block block;

          block = MyBlocks.doorJungle;

          if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
          {
              if (!block.canPlaceBlockAt(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
              {
                  return false;
              }
              else
              {
                  int i1 = MathHelper.floor_double((double)((p_77648_2_.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                  placeDoorBlock(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1, block);
                  --p_77648_1_.stackSize;
                  p_77648_3_.playSoundEffect(p_77648_4_ + 0.5D, p_77648_5_ + 0.5D, p_77648_6_ + 0.5D, "dig.wood", 1.0F, p_77648_3_.rand.nextFloat() * 0.1F + 0.9F);
                  return true;
              }
          }
          else
          {
              return false;
          }
      }
  }
}
