package org.chun.somnus.utils;

import lombok.AllArgsConstructor;
import org.chun.somnus.enums.CryptoType;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtil {

	public static CryptoUtilType type(CryptoType cryptoType){
		return new CryptoUtilType(cryptoType, cryptoType);
	}

	@AllArgsConstructor
	public static class CryptoUtilType {

		private final CryptoType encryptType;
		private final CryptoType decryptType;

		public String encrypt(byte[] plainText, byte[] key) throws Exception {
			return this.encrypt(plainText, key, null, null);
		}

		public String encrypt(byte[] plainText, byte[] key, byte[] iv, byte[] aad) throws Exception {
			SecretKeySpec keySpec = new SecretKeySpec(key, encryptType.getAlgorithm());
			Cipher cipher = Cipher.getInstance(encryptType.getTransformation());

			if (encryptType.isIvRequired()) {
				byte[] ivBytes = iv == null ? genSecureByte(encryptType.getGcmSpecLen()) : iv;
				GCMParameterSpec ivSpec = new GCMParameterSpec(encryptType.getGcmSpecLen(), ivBytes);
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			}

			if (encryptType.isAadRequired()) {
				cipher.updateAAD(aad);
			}

			return new String(Base64.getEncoder().encode(cipher.doFinal(plainText)), StandardCharsets.UTF_8);
		}

		public String decrypt(byte[] plainText, byte[] key) throws Exception {
			return this.decrypt(plainText, key, null, null);
		}

		public String decrypt(byte[] plainText, byte[] key, byte[] iv, byte[] aad) throws Exception {
			SecretKeySpec keySpec = new SecretKeySpec(key, decryptType.getAlgorithm());
			Cipher cipher = Cipher.getInstance(decryptType.getTransformation());

			if (decryptType.isIvRequired()) {
				byte[] ivBytes = iv == null ? genSecureByte(decryptType.getGcmSpecLen()) : iv;
				GCMParameterSpec ivSpec = new GCMParameterSpec(decryptType.getGcmSpecLen(), ivBytes);
				cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, keySpec);
			}

			if (decryptType.isAadRequired()) {
				cipher.updateAAD(aad);
			}

			return new String(cipher.doFinal(Base64.getDecoder().decode(plainText)), StandardCharsets.UTF_8);
		}
	}

	protected static byte[] genSecureByte(int len){
		byte[] bytes = new byte[len];
		new SecureRandom().nextBytes(bytes);
		return bytes;
	}
}
