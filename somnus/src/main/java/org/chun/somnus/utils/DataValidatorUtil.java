package org.chun.somnus.utils;

import java.util.Optional;
import java.util.function.IntSupplier;

public class DataValidatorUtil {

	public static boolean singleValue(IntSupplier intSupplier, RuntimeException runtimeException) {
		return Optional.of(intSupplier.getAsInt() == 1)
			.orElseThrow(() -> runtimeException);
	}

	public static boolean countValues(IntSupplier intSupplier, int count, RuntimeException runtimeException){
		return Optional.of(intSupplier.getAsInt() == count)
			.orElseThrow(() -> runtimeException);
	}



}
