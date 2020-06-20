package yuma140902.uptodatemod.launch;

/**
 * {@link VanillaResourceLoader}の処理中に起きたエラーを表す
 */
public class VanillaResourceLoadingException extends Exception {
	public VanillaResourceLoadingException(){
		super();
	}
	
	public VanillaResourceLoadingException(String msg){
		super(msg);
	}
	
	public VanillaResourceLoadingException(String msg, Throwable throwable){
		super(msg, throwable);
	}
	
	public VanillaResourceLoadingException(Throwable throwable){
		super(throwable);
	}
	
	public VanillaResourceLoadingException(String msg, Throwable throwable, boolean enableSuppression, boolean writableStackTrace){
		super(msg, throwable, enableSuppression, writableStackTrace);
	}
}
