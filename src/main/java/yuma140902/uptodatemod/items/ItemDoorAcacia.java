package yuma140902.uptodatemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yuma140902.uptodatemod.IHasRecipes;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.Recipes;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemDoorAcacia extends net.minecraft.item.ItemDoor implements IRegisterable, IHasRecipes {
  public ItemDoorAcacia()
  {
  	super(Material.wood);
  	this.maxStackSize = 64;
  }
  
  public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName("door_acacia"));
		this.setTextureName(StringUtil.getDomainedTextureName("door_acacia"));
		GameRegistry.registerItem(this, "itemDoorAcacia");
  }
  
  @Override
  public void registerRecipes() {
  	GameRegistry.addRecipe(
				new ItemStack(MyItems.itemDoorAcacia, 3, 0),
				"##",
				"##",
				"##",
				'#', Recipes.PLANK_ACACIA
				);
  }

  /**
   * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
   * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
   */
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

          block = MyBlocks.doorAcacia;

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
