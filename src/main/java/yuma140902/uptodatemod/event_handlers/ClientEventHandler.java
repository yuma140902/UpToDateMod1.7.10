package yuma140902.uptodatemod.event_handlers;

public class ClientEventHandler {
private ClientEventHandler() {}
	
	public static final ClientEventHandler INSTANCE = new ClientEventHandler();
	
	///////////////////////
	/*
	@SubscribeEvent
	public void onTooltipShown(ItemTooltipEvent event) {
		ItemStack itemstack = event.itemStack;
		int[] oreIDs = OreDictionary.getOreIDs(itemstack);
		
		for(int i = 0; i < oreIDs.length; ++i) {
			event.toolTip.add(OreDictionary.getOreName(oreIDs[i]));
		}
	}
	*/
}
