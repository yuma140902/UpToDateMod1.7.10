package yuma140902.yumaessentials.iea;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IEAFeatureResult {
	private static enum EnumResult{SUCCEEDED, SKIPPED, FAILED}
	
	private static final IEAFeatureResult succeeded = new IEAFeatureResult(EnumResult.SUCCEEDED);
	private static final IEAFeatureResult skipped = new IEAFeatureResult(EnumResult.SKIPPED);
	
	public static IEAFeatureResult succeeded(){return succeeded;}
	public static IEAFeatureResult skipped(){return skipped;}
	public static IEAFeatureResult failed(){return failed(null);}
	public static IEAFeatureResult failed(Throwable reason){
		IEAFeatureResult result = new IEAFeatureResult(EnumResult.FAILED);
		result.reason = reason;
		return result;
	}
	
	@Nonnull
	private final EnumResult result;
	@Nullable
	private Throwable reason = null;
	
	private IEAFeatureResult(EnumResult result){
		this.result = result;
	}
	
	public boolean isSuccess(){return this.result==EnumResult.SUCCEEDED;}
	public boolean isSkipped(){return this.result==EnumResult.SKIPPED;}
	public boolean isFailed(){return this.result==EnumResult.FAILED;}
	
	@Nullable
	public Throwable getReason(){return this.reason;}
}
