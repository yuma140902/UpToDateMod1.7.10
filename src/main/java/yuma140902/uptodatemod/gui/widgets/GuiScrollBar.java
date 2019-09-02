package yuma140902.uptodatemod.gui.widgets;

import java.util.Iterator;
import javax.annotation.Nonnull;
import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import yuma140902.uptodatemod.gui.event.IEvent;
import yuma140902.uptodatemod.gui.event.IEventHandler;
import yuma140902.uptodatemod.gui.event.IEventProvider;
import yuma140902.uptodatemod.gui.util.GuiUtils;
import yuma140902.uptodatemod.gui.util.IResourceLocationPart;
import yuma140902.uptodatemod.gui.widgets.GuiScrollBar.ScrollChangeEvent;
import yuma140902.uptodatemod.util.registry.GenericRegistry;

public class GuiScrollBar extends GuiButtonExt implements IEventProvider<ScrollChangeEvent> {

	private static final int mouseHovering = 2;
	
	private IResourceLocationPart grip;
	private int gripYPosition;
	private int heightScrollArea;
	
	private boolean isScrolling = false;
	private int prevMouseX = -1;
	private int prevMouseY = -1;
	private int currentValue = 0;
	private int scrollMax;
	
	public GuiScrollBar(int id, int x, int y, int width, int height, int scrollMax, IResourceLocationPart gripRes) {
		super(id, x, y, width, height, "");
		this.scrollMax = scrollMax;
		this.grip = gripRes;
		this.gripYPosition = this.yPosition;
		this.heightScrollArea = this.height - grip.getHeight();
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(!this.visible) {
			return;
		}
		
		GuiUtils.drawContinuousTexturedBox(grip, this.xPosition, gripYPosition, grip.getWidth(), grip.getHeight(), this.zLevel);
		
		if(!isScrolling) {
			this.field_146123_n = mouseX >= this.xPosition && mouseY >= gripYPosition && mouseX < this.xPosition + grip.getWidth() && mouseY < gripYPosition + grip.getHeight();
		}
		else {
			// ドラッグ中はグリップの判定範囲を広くする
			final int margin = 5;
			this.field_146123_n = mouseX >= this.xPosition && mouseY >= gripYPosition-margin && mouseX < this.xPosition + grip.getWidth() && mouseY < gripYPosition + grip.getHeight() + margin;
		}
		int hoverstate = this.getHoverState(this.field_146123_n);
		if(hoverstate == mouseHovering && this.isScrolling)
			this.mouseDragged(mc, mouseX, mouseY);
	}
	
	@Override
	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		if(!this.isScrolling || this.prevMouseX == -1 || this.prevMouseY == -1) {
			this.prevMouseX = mouseX;
			this.prevMouseY = mouseY;
			return;
		}
		
		int diffY = mouseY - prevMouseY;
		gripYPosition += diffY;
		sanitizeGripYPosition();
		startScroll();
		
		
		this.prevMouseX = mouseX;
		this.prevMouseY = mouseY;
	}
	
	private void sanitizeGripYPosition() {
		final int min = this.yPosition;
		final int max = this.yPosition + heightScrollArea;
		if(gripYPosition < min) {
			gripYPosition = min;
		}
		if(gripYPosition > max) {
			gripYPosition = max;
		}
	}
	
	private void sanitizeCurrentValue() {
		if(currentValue < 0) {
			currentValue = 0;
		}
		if(currentValue > scrollMax) {
			currentValue = scrollMax;
		}
	}
	
	@Override
	public void func_146113_a(SoundHandler soundHandler) {
		//super.func_146113_a(soundHandler);  // 音は鳴らさない
		startScroll();
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		stopScroll();
	}
	
	public void startScroll() {
		this.isScrolling = true;
		this.currentValue = -1;
	}
	
	private void stopScroll() {
		this.isScrolling = false;
		this.prevMouseX = -1;
		this.prevMouseY = -1;
		
		fireEvent(ScrollChangeEvent.CreateNew(this));
	}
	
	public int getMaxValue() {
		return scrollMax;
	}
	
	public int getCurrentValue() {
		if(this.currentValue >= 0) return this.currentValue;
		
		int gripYPos = this.gripYPosition - this.yPosition;
		
		if(gripYPos == 0) return 0;
		else if(gripYPos == heightScrollArea) return scrollMax;
		
		int value = gripYPos * (scrollMax+1) / heightScrollArea;
		
		return this.currentValue = value;
	}
	
	public void scrollTo(int value) {
		this.currentValue = value;
		sanitizeCurrentValue();
		
		if(currentValue == 0) {
			this.gripYPosition = this.yPosition;
			return;
		}
		else if(currentValue >= scrollMax) {
			this.gripYPosition = this.yPosition + heightScrollArea;
			return;
		}
		
		this.gripYPosition = Math.round((float)currentValue * heightScrollArea / (scrollMax+1)) + this.yPosition + 1;
		sanitizeGripYPosition();
		
		fireEvent(ScrollChangeEvent.CreateNew(this));
	}
	
	
	
	// ================= IEventProvider<ScrollChangeEvent> ここから =================
	
	private GenericRegistry<IEventHandler<ScrollChangeEvent>> scrollChangeEventHandlers = new GenericRegistry<>();
	
	@Override
	public void subscribe(IEventHandler<ScrollChangeEvent> handler) {
		scrollChangeEventHandlers.register(handler);
	}

	@Override
	public void unregister(IEventHandler<ScrollChangeEvent> handler) {
		scrollChangeEventHandlers.remove(handler);
	}

	@Override
	public void fireEvent(ScrollChangeEvent event) {
		Iterator<IEventHandler<ScrollChangeEvent>> iterator = scrollChangeEventHandlers.iterator();
		while (iterator.hasNext()) {
			IEventHandler<ScrollChangeEvent> eventHandler = iterator.next();
			eventHandler.onFire(event);
		}
	}
	
	// ================= IEventProvider<ScrollChangeEvent> ここまで =================
	
	
	
	public static class ScrollChangeEvent implements IEvent {
		public int scrollValue;
		
		public ScrollChangeEvent(int scrollValue) {
			this.scrollValue = scrollValue;
		}
		
		@Nonnull
		public static ScrollChangeEvent CreateNew(@Nonnull GuiScrollBar scrollBar) {
			return new ScrollChangeEvent(scrollBar.getCurrentValue());
		}
	}
}
