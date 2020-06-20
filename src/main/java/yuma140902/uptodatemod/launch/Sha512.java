package yuma140902.uptodatemod.launch;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Sha512 {
	public static String calcSha512(String str) {
		Charset charset = StandardCharsets.UTF_8;
		return calcSha512(str.getBytes(charset));
	}
	
	public static String calcSha512(byte[] bytes){
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("SHA-512").digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return DatatypeConverter.printHexBinary(hash);
	}
	
	public static String calcSha512(InputStream inputStream) throws IOException {
		return calcSha512(readAll(inputStream));
	}
	
	private static byte[] readAll(InputStream inputStream) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		while(true){
			int len = inputStream.read(buffer);
			if(len < 0){
				break;
			}
			bout.write(buffer, 0, len);
		}
		return bout.toByteArray();
	}
}
