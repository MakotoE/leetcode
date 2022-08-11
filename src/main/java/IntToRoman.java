package main.java;

public class IntToRoman {
	static String convertDigit(int n, String one, String five, String ten) {
		switch (n) {
		case 4 -> {
			return String.format("%s%s", one, five);
		}
		case 9 -> {
			return String.format("%s%s", one, ten);
		}
		}

		if (n <= 3) {
			return one.repeat(n);
		}

		return String.format("%s%s", five, one.repeat(n - 5));
	}

	public static String intToRoman(int number) {
		/*
		Convert each digit
		 */
		var thousands = number / 1000;
		var hundreds = (number - thousands * 1000) / 100;
		var tens = (number - thousands * 1000 - hundreds * 100) / 10;
		var ones = number - thousands * 1000 - hundreds * 100 - tens * 10;
		return String.format(
			"%s%s%s%s",
			convertDigit(thousands, "M", "", ""),
			convertDigit(hundreds, "C", "D", "M"),
			convertDigit(tens, "X", "L", "C"),
			convertDigit(ones, "I", "V", "X")
		);
	}

	static class TestCase {
		int number;
		String expected;

		public TestCase(int number, String expected) {
			this.number = number;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				0,
				""
			),
			new TestCase(
				1,
				"I"
			),
			new TestCase(
				3,
				"III"
			),
			new TestCase(
				4,
				"IV"
			),
			new TestCase(
				5,
				"V"
			),
			new TestCase(
				6,
				"VI"
			),
			new TestCase(
				8,
				"VIII"
			),
			new TestCase(
				9,
				"IX"
			),
			new TestCase(
				10,
				"X"
			),
			new TestCase(
				50,
				"L"
			),
			new TestCase(
				100,
				"C"
			),
			new TestCase(
				500,
				"D"
			),
			new TestCase(
				1000,
				"M"
			),
			new TestCase(
				58,
				"LVIII"
			),
			new TestCase(
				1994,
				"MCMXCIV"
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = intToRoman(tests[i].number);
			assert(result.equals(tests[i].expected)) : i;
		}
	}
}
