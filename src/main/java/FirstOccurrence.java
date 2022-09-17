package main.java;

public class FirstOccurrence {
	static int hash(String s) {
		var hash = 0;
		for (var c : s.toCharArray()) {
			hash += c;
		}
		return hash;
	}

	public static int strStr(String string, String substring) {
		if (substring.length() == 0 || string.length() < substring.length()) {
			return -1;
		}

		final var substringHash = hash(substring);
		var currentHash = hash(string.substring(0, substring.length()));

		if (currentHash == substringHash && string.startsWith(substring)) {
			return 0;
		}

		// i is the left end index
		for (int i = 0; i < string.length() - substring.length(); i++) {
			currentHash += string.charAt(i + substring.length()) - string.charAt(i);

			if (currentHash == substringHash && string.startsWith(substring, i + 1)) {
				return i + 1;
			}
		}

		return -1;
	}

	record TestCase(String string, String substring, int expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				"",
				"",
				-1
			),
			new TestCase(
				"a",
				"",
				-1
			),
			new TestCase(
				"",
				"a",
				-1
			),
			new TestCase(
				"a",
				"a",
				0
			),
			new TestCase(
				"ab",
				"a",
				0
			),
			new TestCase(
				"ab",
				"b",
				1
			),
			new TestCase(
				"ab",
				"ab",
				0
			),
			new TestCase(
				"abc",
				"ab",
				0
			),
			new TestCase(
				"abc",
				"bc",
				1
			),
			new TestCase(
				"aabb",
				"ab",
				1
			),
			new TestCase(
				"sadbutsad",
				"sad",
				0
			),
			new TestCase(
				"leetcode",
				"leeto",
				-1
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = strStr(tests[i].string, tests[i].substring);
			assert result == tests[i].expected : i;
		}
	}
}
