package yuma140902.yumalib_ee.api.update;

import java.util.ArrayList;
import java.util.List;

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
