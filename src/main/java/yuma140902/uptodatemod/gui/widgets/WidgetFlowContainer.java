package yuma140902.uptodatemod.gui.widgets;

import java.util.Iterator;
import javax.annotation.Nonnull;
import cpw.mods.fml.client.config.GuiButtonExt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.item.ItemStack;
import yuma140902.uptodatemod.gui.event.IEvent;
import yuma140902.uptodatemod.gui.event.IEventHandler;
import yuma140902.uptodatemod.gui.event.IEventProvider;
import yuma140902.uptodatemod.gui.util.GuiUtils;
import yuma140902.uptodatemod.gui.util.IResourceLocationPart;
import yuma140902.uptodatemod.gui.widgets.WidgetFlowContainer.SelectionChangeEvent;
import yuma140902.uptodatemod.util.registry.GenericRegistry;

public class WidgetFlowContainer implements IFixedWidget, IEventProvider<SelectionChangeEvent> {
	
	private static final int ITEM_WIDTH = 16;
	private static final int ITEM_HEIGHT = 18;
	
	private final GenericRegistry<ItemInfo> itemContainer = new GenericRegistry<>();

	@Getter private int posX, posY;
	private int contentWidth, contentHeight;
	private int totalWidth;
	private GuiScrollBar scrollBar;
	private int numRow = 1;
	
	private final IResourceLocationPart bgNormal;
	private final IResourceLocationPart bgHover;
	private final IResourceLocationPart bgSelected;
	
	private final EventProvider eventProvider = new EventProvider();
	
	public WidgetFlowContainer(int posX, int posY, int width, int height, IResourceLocationPart bgNormal, IResourceLocationPart bgHover, IResourceLocationPart bgSelected) {
		this.posX = posX;
		this.posY = posY;
		this.contentWidth = width;
		this.contentHeight = height;
		this.totalWidth = width;
		this.bgNormal = bgNormal;
		this.bgHover = bgHover;
		this.bgSelected = bgSelected;
	}
	
	public WidgetFlowContainer setNumRow(int numRow) {
		this.numRow = numRow;
		return this;
	}
	
	public WidgetFlowContainer setScrollBar(GuiScrollBar scrollBar, int margin) {
		this.scrollBar = scrollBar;
		this.totalWidth = this.contentWidth + margin + scrollBar.width;
		return this;
	}
	
	@Override
	public void drawWidget(Minecraft mc, int mouseX, int mouseY) {
		Iterator<ItemInfo> items = allItem();
		int row = 0;
		int column = 0;
		GuiUtils.startDrawingItem();
		while (items.hasNext()) {
			ItemInfo item = items.next();
			int x = posX + row * ITEM_WIDTH;
			int y = posY + column * ITEM_HEIGHT;
			GuiUtils.drawSingleItem(x, y+1, item.getItemstack(), mc);
			if(item.isSelected()) {
				GuiUtils.drawContinuousTexturedBox(bgHover, x, y, bgSelected.getWidth(), bgSelected.getHeight(), 0);
			}
			else if(item.isHover()) {
				GuiUtils.drawContinuousTexturedBox(bgHover, x, y, bgHover.getWidth(), bgHover.getHeight(), 0);
			}
			else {
				GuiUtils.drawContinuousTexturedBox(bgNormal, x, y, bgNormal.getWidth(), bgNormal.getHeight(), 0);
			}
			
			++row;
			if(row == numRow) {
				row = 0;
				++column;
			}
		}
		GuiUtils.stopDrawingItem();
	}
	
	
	
	// ================= IEventProvider<SelectionChangeEvent> ここから =================
	
	@Override
	public void subscribe(IEventHandler<SelectionChangeEvent> handler) {
		eventProvider.subscribe(handler);
	}

	@Override
	public void unregister(IEventHandler<SelectionChangeEvent> handler) {
		eventProvider.unregister(handler);
	}

	@Override
	public void fireEvent(SelectionChangeEvent event) {
		eventProvider.fireEvent(event);
	}
	
	private static class EventProvider implements IEventProvider<SelectionChangeEvent> {
		
		private GenericRegistry<IEventHandler<SelectionChangeEvent>> eventHandlers = new GenericRegistry<>();
		
		@Override
		public void subscribe(IEventHandler<SelectionChangeEvent> handler) {
			eventHandlers.register(handler);
		}

		@Override
		public void unregister(IEventHandler<SelectionChangeEvent> handler) {
			eventHandlers.remove(handler);
		}

		@Override
		public void fireEvent(SelectionChangeEvent event) {
			Iterator<IEventHandler<SelectionChangeEvent>> iterator = eventHandlers.iterator();
			while (iterator.hasNext()) {
				IEventHandler<SelectionChangeEvent> eventHandler = iterator.next();
				eventHandler.onFire(event);
			}
		}
		
	}
	
	// ================= IEventProvider<SelectionChangeEvent> ここまで =================
	
	
	
	/**
	 * @param id
   * @param x GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
	 * @param y GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
	 */
	public GuiFlowContainer asGui(int id, int x, int y) {
		return new GuiFlowContainer(id, this, x, y);
	}

	@Override
	public int getWidth() {
		return totalWidth;
	}

	@Override
	public int getHeight() {
		return contentHeight;
	}
	
	
	
	public Iterator<ItemInfo> allItem(){
		return itemContainer.iterator();
	}
	
	public void addItem(@Nonnull ItemStack item) {
		itemContainer.register(new ItemInfo(item));
	}
	
	public void removeAllItem() {
		itemContainer.removeAll();
	}
	
	
	
	
	@RequiredArgsConstructor
	private static class ItemInfo {
		@Getter
		private final @Nonnull ItemStack itemstack;
		
		@Getter
		@Setter
		private boolean isHover = false;
		
		@Getter
		@Setter
		private boolean isSelected = false;
		
	}
	
	
	
	
	
	public static class GuiFlowContainer extends GuiButtonExt {
		private final WidgetFlowContainer container;
		
		private boolean clicked = false;
		
		/**
		 * 
		 * @param id
		 * @param container
		 * @param x GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
		 * @param y GuiScreenのX座標 (WidgetはGuiScreenの左上が原点だが、Guiはウィンドウの左上が原点なので、GuiScreenの左上の座標が必要)
		 */
		public GuiFlowContainer(int id, @Nonnull WidgetFlowContainer container, int x, int y) {
			super(id, x+container.posX, y+container.posY, container.totalWidth, container.contentHeight, "");
			this.container = container;
		}
		
		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY) {
			Iterator<ItemInfo> items = container.allItem();
			int row = 0;
			int column = 0;
			int index = 0;
			while (items.hasNext()) {
				ItemInfo item = items.next();
				int xBegin = xPosition + row * ITEM_WIDTH;
				int yBegin = yPosition + column * ITEM_HEIGHT;
				int xEnd = xBegin + ITEM_WIDTH;
				int yEnd = yBegin + ITEM_HEIGHT;
				if(xBegin <= mouseX && mouseX <= xEnd && yBegin <= mouseY && mouseY <= yEnd) {
					item.setHover(true);
					if(clicked) {
						container.allItem().forEachRemaining(otherItem -> otherItem.setSelected(false));
						item.setSelected(true);
						clicked = false;
						container.fireEvent(new SelectionChangeEvent(index));
					}
				}
				else {
					item.setHover(false);
				}
				
				
				++row;
				if(row == container.numRow) {
					row = 0;
					++column;
				}
				++index;
			}
		}
		
		@Override
		public void func_146113_a(SoundHandler soundHandler) {
			super.func_146113_a(soundHandler);
			clicked = true;
		}
		
		@Override
		protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_) {
		}
	}
	
	
	
	
	@AllArgsConstructor
	public static class SelectionChangeEvent implements IEvent{
		public int selectedIndex;
		
	}

}
