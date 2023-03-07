package yuma140902.yumalib.api.registry;

import java.util.ArrayList;
import java.util.List;
import yuma140902.yumalib.api.update.IUpdateChecker;

/**
 * {@link IUpdateChecker}のレジストリ
 */
public class UpdateCheckerRegistry {
	private UpdateCheckerRegistry() {}
	
	public static final UpdateCheckerRegistry INSTANCE = new UpdateCheckerRegistry();
	
	private List<IUpdateChecker> updateCheckers = new ArrayList<IUpdateChecker>();
	
	public List<IUpdateChecker> list(){
		return updateCheckers;
	}
	
	public void register(IUpdateChecker updateChecker) {
		updateCheckers.add(updateChecker);
	}
}
