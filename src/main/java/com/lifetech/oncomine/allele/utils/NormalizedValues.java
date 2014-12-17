package com.lifetech.oncomine.allele.utils;

public class NormalizedValues {
	private String alternateAllele;
	private String referenceAllele;
	private Integer position;

	public NormalizedValues(String referenceAllele, String alternateAllele, Integer position) {
		this.alternateAllele = alternateAllele;
		this.referenceAllele = referenceAllele;
		this.position = position;
	}

	public String getAlternateAllele() {
		return this.alternateAllele;
	}

	public void setAlternateAllele(String newAlternate) {
		this.alternateAllele = newAlternate;
	}

	public String getReferenceAllele() {
		return this.referenceAllele;
	}

	public void setReferenceAllele(String newReference) {
		this.referenceAllele = newReference;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer newPosition) {
		this.position = newPosition;
	}
}
