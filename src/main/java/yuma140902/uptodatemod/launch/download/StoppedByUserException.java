package yuma140902.uptodatemod.launch.download;

public class StoppedByUserException extends Exception {
	public StoppedByUserException() {
		super();
	}
	
	public StoppedByUserException(String message) {
		super(message);
	}
	
	public StoppedByUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public StoppedByUserException(Throwable cause) {
		super(cause);
	}
}
