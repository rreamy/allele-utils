package com.lifetech.oncomine.allele.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NormalizedValueCalculatorTest {

	private NormalizedValueCalculator calculator;

	@Before
	public void setUp() {
		calculator = new NormalizedValueCalculator();
	}

	@After
	public void tearDown() {
		calculator = null;
	}

	@Test
	public void calculatesNormalizedValues_TrimsTheRightSide_trimsMultiple() {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("AAA", "CTAA", new Integer(123));

		assertEquals("CT", normalizedValues.getAlternateAllele());
		assertEquals("A", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(123), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_TrimsTheRightSide_trimsSingle() {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("AAA", "CCTA", new Integer(123));

		assertEquals("CCT", normalizedValues.getAlternateAllele());
		assertEquals("AA", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(123), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_trimsTheLeftSide_trimsSingle() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("AAA", "ATGG", new Integer(123));

		assertEquals("TGG", normalizedValues.getAlternateAllele());
		assertEquals("AA", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(124), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_trimsTheLeftSide_trimsMultiple() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("AAA", "AATG", new Integer(123));

		assertEquals("TG", normalizedValues.getAlternateAllele());
		assertEquals("A", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(125), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_noTrimWhenNoCommonBases() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("CC", "AAA", new Integer(123));

		assertEquals("AAA", normalizedValues.getAlternateAllele());
		assertEquals("CC", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(123), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_noTrimWhenRefLengthOne() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("C", "CCC", new Integer(123));
		assertEquals("CCC", normalizedValues.getAlternateAllele());
		assertEquals("C", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(123), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_whenRefAndAltEqualLength() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("CCG", "CCT", new Integer(123));

		assertEquals("T", normalizedValues.getAlternateAllele());
		assertEquals("G", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(125), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_whenNotSameLengthAndFirstAndSecondCharactersEqual() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("CCGA", "CG", new Integer(123));

		assertEquals("G", normalizedValues.getAlternateAllele());
		assertEquals("CGA", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(124), normalizedValues.getPosition());
	}

	@Test
	public void calculatesNormalizedValues_doesFinalTrimIfStillLongEnoughAndInitialBaseMatches() throws Exception {
		NormalizedValues normalizedValues = calculator.calculateNormalizedValues("CCGA", "CCG", new Integer(123));
		assertEquals("G", normalizedValues.getAlternateAllele());
		assertEquals("GA", normalizedValues.getReferenceAllele());
		assertEquals(new Integer(125), normalizedValues.getPosition());
	}

}
