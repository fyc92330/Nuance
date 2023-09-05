package org.chun.somnus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CryptoType {
	BASE64_AES_ECB_PKCS5PADDING("AES", "AES/ECB/PKCS5Padding", false, 0, false),
	BASE64_AES_ECB_NOPADDING("AES", "AES/ECB/NoPadding", false, 0, false),
	BASE64_AES_GCM_PKCS5PADDING("AES", "AES/GCM/PKCS5Padding", true, 128, true),
	BASE64_AES_GCM_NOPADDING("AES", "AES/GCM/NoPadding", true, 128, true),
	;

	private final String algorithm;
	private final String transformation;
	private final boolean ivRequired;
	private final int gcmSpecLen;
	private final boolean aadRequired;
}
