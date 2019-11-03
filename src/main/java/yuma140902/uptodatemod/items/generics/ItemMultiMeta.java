package yuma140902.uptodatemod.items.generics;

import java.util.List;
import javax.annotation.Nonnull;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import yuma140902.uptodatemod.IRegisterable;
import yuma140902.uptodatemod.util.StringUtil;

public class ItemMultiMeta extends Item implements IRegisterable {
	@Nonnull protected final String name;
	@Nonnull protected final String[] names;
	protected final int MAX_META;
	@Nonnull protected final String[] textureNames;
	@Nonnull protected final IIcon[] icons;
	
	public ItemMultiMeta(@Nonnull String name, @Nonnull String[] names, @Nonnull String[] textureNames) {
		this.name = name;
		this.names = names;
		this.MAX_META = names.length-1;
		
		String[] sanitizedTextureNames;
		if(textureNames.length < names.length) {
			sanitizedTextureNames = new String[names.length];
			for(int i=0; i<sanitizedTextureNames.length; ++i) {
				sanitizedTextureNames[i] = i < textureNames.length ? textureNames[i] : sanitizedTextureNames[i];
			}
		}
		else {
			sanitizedTextureNames = textureNames;
		}
		this.textureNames = sanitizedTextureNames;
		
		icons = new IIcon[MAX_META+1];
		this.hasSubtypes = true;
	}
	
	@Override
	public void register() {
		this.setUnlocalizedName(StringUtil.getDomainedUnlocalizedName(name));
		GameRegistry.registerItem(this, name);
	}
	
	
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int meta=0; meta <= MAX_META; ++meta) {
			list.add(new ItemStack(item, 1, meta));
		}
	}
	
	@Override
	public void registerIcons(IIconRegister register) {
		for(int meta=0; meta <= MAX_META; ++meta) {
			icons[meta] = register.registerIcon(textureNames[meta]);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int meta = itemstack.getItemDamage();
		if(0 <= meta && meta <= MAX_META) {
			return super.getUnlocalizedName() + StringUtil.surfix(".", names[meta]);
		}
		else {
			return super.getUnlocalizedName();
		}
	}
}
