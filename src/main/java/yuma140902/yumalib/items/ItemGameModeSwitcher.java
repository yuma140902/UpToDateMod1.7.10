package yuma140902.yumalib.items;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.CommandGameMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import yuma140902.yumalib.YumaLibCreativeTab;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.registry.Contexts;

public class ItemGameModeSwitcher extends Item implements IRegisterable {
	
	public ItemGameModeSwitcher() {
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(Contexts.DEFAULT.nameProvider().domainedUnlocalized("gm_switcher"));
		this.setTextureName(Contexts.DEFAULT.nameProvider().domainedTexture("gm_switcher"));
		GameRegistry.registerItem(this, "gm_switcher");
		YumaLibCreativeTab.setToTab(this);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean isAdvMode) {
		list.add("[EXPERIMENTAL]");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(!(player instanceof EntityPlayerMP)) return itemstack;
		
		try {
			if(player.capabilities.isCreativeMode) {
				new CommandGameMode().processCommand(player, new String[] {"survival"});
			}
			else {
				new CommandGameMode().processCommand(player, new String[] {"creative"});
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return itemstack;
	}
}
