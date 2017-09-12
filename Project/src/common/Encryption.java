package common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Encryption {
	public static String encryption(String pass) {

		Charset charset = StandardCharsets.UTF_8;
		String algorithm = "MD5";

		byte[] bytes;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(pass.getBytes(charset));
			String result = DatatypeConverter.printHexBinary(bytes);
			return result;
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
			return null;
		}
	}
}