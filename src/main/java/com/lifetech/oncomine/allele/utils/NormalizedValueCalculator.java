package com.lifetech.oncomine.allele.utils;

public class NormalizedValueCalculator {

	public NormalizedValueCalculator() {
	}

	public NormalizedValues calculateNormalizedValues(String referenceAllele, String alternateAllele, Integer position) {
		NormalizedValues normalizedValues = new NormalizedValues(referenceAllele, alternateAllele, position);
		normalizedValues = doTrim(normalizedValues);
		return normalizedValues;
	}

	private NormalizedValues doTrim(NormalizedValues values) {
		trimCommonBasesFromEnd(values);
		trimCommonBasesFromFront(values);
		doFinalTrim(values);
		return values;
	}

	private void trimCommonBasesFromFront(NormalizedValues values) {
		if (stringsLengthsEqual(values)) {
			doEqualLengthTrimming(values);
		} else {
			doUnequalLengthTrimming(values);
		}
	}

	private void doUnequalLengthTrimming(NormalizedValues values) {
		while (stringsLongEnough(values) && firstCharactersEqual(values) && secondCharactersEqual(values)) {
			trimFirstCharacterOfReferenceAndAlternateAndIncrementPosition(values);
		}
	}

	private void doEqualLengthTrimming(NormalizedValues values) {
		while (stringsLongEnough(values) && firstCharactersEqual(values)) {
			trimFirstCharacterOfReferenceAndAlternateAndIncrementPosition(values);
		}
	}

	private void doFinalTrim(NormalizedValues values) {
		if (stringsLongEnough(values) && firstCharactersEqual(values)) {
			trimFirstCharacterOfReferenceAndAlternateAndIncrementPosition(values);
		}
	}

	private void trimCommonBasesFromEnd(NormalizedValues values) {
		while (stringsLongEnough(values) && lastCharactersEqual(values)) {
			values.setReferenceAllele(trimLastCharacter(values.getReferenceAllele()));
			values.setAlternateAllele(trimLastCharacter(values.getAlternateAllele()));
		}
	}

	private boolean secondCharactersEqual(NormalizedValues values) {
		return values.getReferenceAllele().charAt(1) == values.getAlternateAllele().charAt(1);
	}

	private boolean firstCharactersEqual(NormalizedValues values) {
		return values.getReferenceAllele().charAt(0) == values.getAlternateAllele().charAt(0);
	}

	private boolean stringsLengthsEqual(NormalizedValues values) {
		return values.getReferenceAllele().length() == values.getAlternateAllele().length();
	}

	private boolean lastCharactersEqual(NormalizedValues values) {
		return values.getReferenceAllele().charAt(values.getReferenceAllele().length() - 1) == values
				.getAlternateAllele().charAt(values.getAlternateAllele().length() - 1);
	}

	private boolean stringsLongEnough(NormalizedValues values) {
		return values.getReferenceAllele().length() != 1 && values.getAlternateAllele().length() != 1;
	}

	private void trimFirstCharacterOfReferenceAndAlternateAndIncrementPosition(NormalizedValues values) {
		values.setAlternateAllele(values.getAlternateAllele().substring(1, values.getAlternateAllele().length()));
		values.setReferenceAllele(values.getReferenceAllele().substring(1, values.getReferenceAllele().length()));
		values.setPosition(values.getPosition() + 1);
	}

	private String trimLastCharacter(String trimString) {
		return trimString.substring(0, trimString.length() - 1);
	}

}
