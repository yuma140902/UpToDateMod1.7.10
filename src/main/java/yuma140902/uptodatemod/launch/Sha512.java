package yuma140902.uptodatemod.launch;

import java.io.BufferedReader;
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
    String algorithm = "SHA-512";

    //ハッシュ生成処理
    byte[] bytes;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(str.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
    String result = DatatypeConverter.printHexBinary(bytes);
    return result;
	}
	
	public static String calcSha512(InputStream stream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = bufferedReader.readLine()) != null) {
			sb.append(line).append('\n');
		}
		
		return calcSha512(sb.toString());
	}
}
