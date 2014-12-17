package com.lifetech.oncomine.allele.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalizedValuesTest {

	private NormalizedValues values;
	private String alternateAllele;
	private String referenceAllele;
	private Integer position;

	@Before
	public void setUp() {
		alternateAllele = "alternate";
		referenceAllele = "reference";
		position = new Integer(123);
		values = new NormalizedValues(referenceAllele, alternateAllele, position);
	}

	@After
	public void tearDown() {
		values = null;
		alternateAllele = null;
		referenceAllele = null;
		position = null;
	}

	@Test
	public void getAlternateAllele() throws Exception {
		assertEquals(alternateAllele, values.getAlternateAllele());
		values.setAlternateAllele("ABC");
		assertEquals("ABC", values.getAlternateAllele());
	}

	@Test
	public void getReferenceAllele() throws Exception {
		assertEquals(referenceAllele, values.getReferenceAllele());
		values.setReferenceAllele("CCC");
		assertEquals("CCC", values.getReferenceAllele());
	}

	@Test
	public void getPosition() throws Exception {
		assertEquals(position, values.getPosition());
		Integer newPosition = new Integer(4);
		values.setPosition(newPosition);
		assertEquals(newPosition, values.getPosition());
	}

}
