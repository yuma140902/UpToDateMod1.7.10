package yuma140902.uptodatemod.vrl;

/**
 * {@link VanillaResourceLoader}の処理中に起きたエラーを表す
 */
public class VRLException extends Exception {
	public VRLException(){
		super();
	}
	
	public VRLException(String msg){
		super(msg);
	}
	
	public VRLException(String msg, Throwable throwable){
		super(msg, throwable);
	}
	
	public VRLException(Throwable throwable){
		super(throwable);
	}
	
	public VRLException(String msg, Throwable throwable, boolean enableSuppression, boolean writableStackTrace){
		super(msg, throwable, enableSuppression, writableStackTrace);
	}
}
