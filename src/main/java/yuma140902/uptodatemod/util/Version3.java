package yuma140902.uptodatemod.util;

public class Version3 {
	public int a = 0;
	public int b = 0;
	public int c = 0;
	
	public Version3(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public static Version3 fromString(String str) {
		if(str == null || str.isEmpty()) return new Version3(0, 0, 0);
		
		int a = 0, b = 0, c = 0;
		String[] tmp = str.split("[.]");
		if(tmp.length < 3) return new Version3(0, 0, 0);
		try {
			a = Integer.parseInt(tmp[0]);
			b = Integer.parseInt(tmp[1]);
			c = Integer.parseInt(tmp[2]);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return new Version3(0, 0, 0);
		}
		
		return new Version3(a, b, c);
	}
	
	public boolean isLaterThan(Version3 version3) {
		if(this.a > version3.a) return true;
		else if(this.a < version3.a) return false;
		
		if(this.b > version3.b) return true;
		else if(this.b < version3.b) return false;
		
		if(this.c > version3.c) return true;
		else if(this.c < version3.c) return false;
		
		return false;
	}
	
	public static boolean isLaterThan(String verA, String verB) {
		Version3 versionA = fromString(verA);
		Version3 versionB = fromString(verB);
		return versionA.isLaterThan(versionB);
	}
	
	public String toString() {
		return a + "." + b + "." + c;
	}
}
