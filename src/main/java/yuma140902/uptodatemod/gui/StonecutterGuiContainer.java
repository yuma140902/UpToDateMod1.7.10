package yuma140902.uptodatemod.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.MyBlocks;
import yuma140902.uptodatemod.MyItems;
import yuma140902.uptodatemod.gui.util.IResourceLocationPart;
import yuma140902.uptodatemod.gui.util.ResourceLocationPart;
import yuma140902.uptodatemod.gui.widgets.GuiScrollBar;
import yuma140902.uptodatemod.gui.widgets.GuiScrollBar.ScrollChangeEvent;
import yuma140902.uptodatemod.gui.widgets.WidgetFlowContainer;
import yuma140902.uptodatemod.tileentity.TileEntityStonecutter;

public class StonecutterGuiContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation(ModUpToDateMod.MOD_TEXTURE_DOMAIN, "textures/gui/stonecutter.png");
	private static final IResourceLocationPart SCROLLBAR_RES = new ResourceLocationPart(TEXTURE, 176, 0, 12, 15);
	private static final IResourceLocationPart BG_NORMAL = new ResourceLocationPart(TEXTURE, 0, 166, 16, 18);
	private static final IResourceLocationPart BG_SELECTED = new ResourceLocationPart(TEXTURE, 0, 184, 16, 18);
	private static final IResourceLocationPart BG_HOVER = new ResourceLocationPart(TEXTURE, 0, 202, 16, 18);
	private static final int idScrollBar = 0;
	private static final int idFlowContainer = 1;
	private GuiScrollBar scrollBar;
	private WidgetFlowContainer recipeList;
	
	private TileEntityStonecutter tileentity;
	
	public StonecutterGuiContainer(EntityPlayer player, TileEntityStonecutter tileentity) {
		super(new StonecutterContainer(player, tileentity));
		this.tileentity = tileentity;
		this.ySize = 166;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		
		recipeList = new WidgetFlowContainer(52, 15, 47, 54, BG_NORMAL, BG_HOVER, BG_SELECTED).setNumRow(4);
		recipeList.subscribe(event -> System.out.println(event.selectedIndex));
		recipeList.addItem(new ItemStack(Blocks.bookshelf));
		recipeList.addItem(new ItemStack(MyBlocks.barrel));
		recipeList.addItem(new ItemStack(Blocks.acacia_stairs));
		recipeList.addItem(new ItemStack(Items.diamond_pickaxe));
		recipeList.addItem(new ItemStack(MyItems.armorStand));
		recipeList.addItem(new ItemStack(Blocks.rail));
		
		this.scrollBar = new GuiScrollBar(idScrollBar, x+119, y+15, 12, 54, 5, SCROLLBAR_RES);
		recipeList.setScrollBar(scrollBar, 5);
		scrollBar.scrollTo(tileentity.getGuiScroll());
		scrollBar.subscribe((ScrollChangeEvent event) -> {
			tileentity.setGuiScroll(event.scrollValue);
		});
		
		this.buttonList.clear();
		this.buttonList.add(this.scrollBar);
		this.buttonList.add(this.recipeList.asGui(idFlowContainer, x, y));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(StatCollector.translateToLocal(tileentity.getInventoryName()), 8, 4, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 94 + 1, 4210752);
		recipeList.drawWidget(mc, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(TEXTURE);
		int tmpX = (this.width - this.xSize) / 2;
    int tmpY = (this.height - this.ySize) / 2;
    this.drawTexturedModalRect(tmpX, tmpY, 0, 0, this.xSize, this.ySize);
	}
	
	/*GUIが開いている時にゲームの処理を止めるかどうか。*/
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
