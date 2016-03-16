package com.fissionworks.ddtutils.data.creation;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomStringBuilderTest {

	@Test
	public void build_withLengthSet_shouldCreateAlphanumericStringOfGivenLength() {
		final int expectedLength = 42;
		final String actualString = new RandomStringBuilder().length(expectedLength).build();
		Assert.assertTrue(StringUtils.isAlphanumeric(actualString));
		Assert.assertEquals(actualString.length(), expectedLength);
	}

	@Test
	public void build_withMinLengthZeroAndNonZeroMaxLength_shouldGenerateAlphanumericStringInRange() {
		// Generate and test multiple strings to increase probability that all length possibilities created/tested
		final int minLength = 0;
		final int maxLength = 4;
		for (int i = 0; i < 20; i++) {
			final String actualString = new RandomStringBuilder().length(minLength, maxLength).build();
			Assert.assertTrue(StringUtils.isAlphanumeric(actualString) || StringUtils.isEmpty(actualString));
			Assert.assertTrue(actualString.length() >= minLength && actualString.length() <= maxLength,
					"Generated string was outside the the minLength/maxLength range");
		}
	}

	@Test
	public void build_withNoParametersSet_shouldCreateDefaultLengthAlphanumericString() {
		final String actualString = new RandomStringBuilder().build();
		Assert.assertTrue(StringUtils.isAlphanumeric(actualString));
		Assert.assertEquals(actualString.length(), RandomStringBuilder.DEAFULT_LENGTH);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void length_withMinAndMaxLengthZero_shouldThrowException() {
		new RandomStringBuilder().length(0, 0);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void length_withMinLengthGreaterThanMaxLength_shouldThrowException() {
		new RandomStringBuilder().length(5, 4);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void length_withNegativeLength_shouldThrowException() {
		new RandomStringBuilder().length(-1);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void length_withNegativeMinLength_shouldThrowException() {
		new RandomStringBuilder().length(-1, 1);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void length_withZeroLength_shouldThrowException() {
		new RandomStringBuilder().length(0);
	}
}