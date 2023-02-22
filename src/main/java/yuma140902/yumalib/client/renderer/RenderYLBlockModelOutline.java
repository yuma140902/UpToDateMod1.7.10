package yuma140902.yumalib.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.opengl.GL11;
import yuma140902.yumalib.api.client.model.IBlockWithYLBlockModel;
import yuma140902.yumalib.api.client.model.YLBlockModel;
import yuma140902.yumalib.api.client.model.YLBlockModelToCollisionBox;

public class RenderYLBlockModelOutline {
	public static boolean renderOutlineInWorld(RenderGlobal renderer, EntityPlayer player, MovingObjectPosition target, float partialTicks){
		if(target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return false;
		Block block = player.worldObj.getBlock(target.blockX, target.blockY, target.blockZ);
		if(!(block instanceof IBlockWithYLBlockModel)) return false;
		
		
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(false);
		
		double delta = 0.002D;
		double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
		double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
		double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
		
		YLBlockModel model = ((IBlockWithYLBlockModel)block).getYLBlockModelInWorld(player.worldObj, target.blockX, target.blockY, target.blockZ);
		for(final YLBlockModel.Element element : model.getElements()){
			AxisAlignedBB aabb = YLBlockModelToCollisionBox.newAABBFromElement(target.blockX, target.blockY, target.blockZ, element)
							.expand(delta, delta, delta)
							.getOffsetBoundingBox(-d0, -d1, -d2);
			RenderGlobal.drawOutlinedBoundingBox(aabb, -1);
		}
		
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		
		return true;
	}
}
