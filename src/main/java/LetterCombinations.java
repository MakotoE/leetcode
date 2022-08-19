package main.java;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
	static List<Character> getLetters(char digit) {
		switch (digit) {
		case '2' -> {
			return List.of('a', 'b', 'c');
		}
		case '3' -> {
			return List.of('d', 'e', 'f');
		}
		case '4' -> {
			return List.of('g', 'h', 'i');
		}
		case '5' -> {
			return List.of('j', 'k', 'l');
		}
		case '6' -> {
			return List.of('m', 'n', 'o');
		}
		case '7' -> {
			return List.of('p', 'q', 'r', 's');
		}
		case '8' -> {
			return List.of('t', 'u', 'v');
		}
		case '9' -> {
			return List.of('w', 'x', 'y', 'z');
		}
		default -> throw new RuntimeException("unexpected");
		}
	}
	public static List<String> letterCombinations(String digits) {
		if (digits.isEmpty()) {
			return List.of();
		}

		var strings = letterCombinations(digits.substring(1));

		var result = new ArrayList<String>();
		for (var c : getLetters(digits.charAt(0))) {
			for (var s : strings) {
				result.add(c + s);
			}

			if (strings.isEmpty()) {
				result.add(String.valueOf(c));
			}
		}

		return result;
	}

	static class TestCase {
		String digits;
		List<String> expected;

		public TestCase(String digits, List<String> expected) {
			this.digits = digits;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				"",
				List.of()
			),
			new TestCase(
				"2",
				List.of(
					"a",
					"b",
					"c"
				)
			),
			new TestCase(
				"23",
				List.of(
					"ad",
					"ae",
					"af",
					"bd",
					"be",
					"bf",
					"cd",
					"ce",
					"cf"
				)
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = letterCombinations(tests[i].digits);
			assert(result.equals(tests[i].expected)) : i;
		}
	}
}
