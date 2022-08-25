package main.java;

import java.util.HashMap;

public class RansomNote {
	public static boolean canConstruct(String ransomNote, String magazine) {
		// Letter to number remaining
		var map = new HashMap<Character, Integer>();

		for (var c : magazine.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}

		for (var c : ransomNote.toCharArray()) {
			if (!map.containsKey(c)) {
				return false;
			}

			map.put(c, map.get(c) - 1);
		}

		for (var value : map.values()) {
			if (value < 0) {
				return false;
			}
		}

		return true;
	}

	static class TestCase {
		String ransomNote;
		String magazine;
		boolean expected;

		public TestCase(String ransomNote, String magazine, boolean expected) {
			this.ransomNote = ransomNote;
			this.magazine = magazine;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				"",
				"",
				true
			),
			new TestCase(
				"a",
				"",
				false
			),
			new TestCase(
				"",
				"a",
				true
			),
			new TestCase(
				"a",
				"a",
				true
			),
			new TestCase(
				"aa",
				"aa",
				true
			),
			new TestCase(
				"ab",
				"aab",
				true
			),
		};

		for (var i = 0; i < tests.length; i++) {
			var result = canConstruct(tests[i].ransomNote, tests[i].magazine);
			assert(result == tests[i].expected) : i;
		}
	}
}
