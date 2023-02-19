package yuma140902.uptodatemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import yuma140902.yumalib.YumaLibCreativeTab;
import yuma140902.yumalib.api.IRegisterable;
import yuma140902.yumalib.api.client.model.IBlockWithYLBlockModel;
import yuma140902.yumalib.api.client.model.YLBlockModel;
import yuma140902.yumalib.api.client.model.YLBlockModelToCollisionBox;
import yuma140902.yumalib.api.registry.Contexts;

import java.util.List;

public class BlockTest extends Block implements IRegisterable, IBlockWithYLBlockModel
{
	public BlockTest() {
		super(Material.circuits);
	}
	
	IIcon testIcon;
	IIcon north, south, west, east, top, bottom;
	
	@Override public void registerBlockIcons(IIconRegister register) {
		super.registerBlockIcons(register);
		this.testIcon = register.registerIcon(this.getTextureName());
		this.north = register.registerIcon(this.getTextureName() + "_n");
		this.south = register.registerIcon(this.getTextureName() + "_s");
		this.west = register.registerIcon(this.getTextureName() + "_w");
		this.east = register.registerIcon(this.getTextureName() + "_e");
		this.top = register.registerIcon(this.getTextureName() + "_top");
		this.bottom = register.registerIcon(this.getTextureName() + "_bottom");
	}
	
	@Override public void register() {
		this.setBlockName(Contexts.current().nameProvider().domainedUnlocalized("test"));
		this.setBlockTextureName(Contexts.current().nameProvider().domainedTexture("test"));
		GameRegistry.registerBlock(this, "test");
		YumaLibCreativeTab.setToTab(this);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
		YLBlockModelToCollisionBox.addCollisionBoxesToList(world, x, y, z, mask, list, this.getYLBlockModelInWorld(world, x, y, z));
	}
	
	
	
	
	
	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
		//TODO
		return super.collisionRayTrace(world, x, y, z, start, end);
	}
	
	@Override public boolean isOpaqueCube() {
		return false;
	}
	
	@Override public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override public int getRenderType() {
		return YLBlockModel.renderId();
	}
	
	@Override public YLBlockModel getYLBlockModelInWorld(IBlockAccess world, int x, int y, int z) {
		YLBlockModel.Element element = new YLBlockModel.Element(
						new double[]{0, 0, 0},
						new double[]{16, 16, 8},
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"bottom",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"top",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"north",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"south",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"west",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"east",
										null,
										0
						)
		);
		
		YLBlockModel.Element element2 = new YLBlockModel.Element(
						new double[]{0, 0, 8},
						new double[]{16, 4, 16},
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"bottom",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"top",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"north",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"south",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"west",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"east",
										null,
										0
						)
		);
		
		YLBlockModel.Element element3 = new YLBlockModel.Element(
						new double[]{16, 0, 16},
						new double[]{16+128, 128, 16+128},
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"bottom",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"top",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{-256, 256, -256, 256},
										"north",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"south",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"west",
										null,
										0
						),
						new YLBlockModel.Face(
										new int[]{0, 16, 0, 16},
										"east",
										null,
										0
						)
		);
		
		return new YLBlockModel(new YLBlockModel.Element[]{element, element2, element3});
	}
	
	@Override public YLBlockModel getYLBlockModelInInventory(int metadata) {
		return null;
	}
	
	@Override public IIcon getFaceTexture(String textureName) {
		switch (textureName) {
			case "north":
				return north;
			case "south":
				return south;
			case "west":
				return west;
			case "east":
				return east;
			case "top":
				return top;
			case "bottom":
				return bottom;
			default:
				return testIcon;
		}
	}
}
