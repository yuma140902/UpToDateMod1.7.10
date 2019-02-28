package yuma140902.uptodatemod.asm;

import net.minecraft.util.IIcon;

public final class UpToDateCoreHooks {
	private UpToDateCoreHooks() {}
	
	public static IIcon getIconForBlockDirt(int side, int meta) {
		if (meta == 2) {
			if (side == 1) {
				return this.field_150008_b;
			}
			
			if (side != 0) {
				return this.field_150010_M;
			}
		}
	}
}
