package main.java;

import java.util.HashSet;

public class LengthOfLongestSubstring {
	public static int lengthOfLongestSubstring(String s) {
		var characterSet = new HashSet<Character>();

		var start = 0;
		var end = 0;
		var maxLength = 0;

		while (end < s.length()) {
			var c = s.charAt(end);

			if (characterSet.contains(c)) {
				while (characterSet.contains(c)) {
					characterSet.remove(s.charAt(start));
					start++;
				}
			}

			characterSet.add(c);
			end++;

			if (characterSet.size() > maxLength) {
				maxLength = characterSet.size();
			}
		}

		return maxLength;
	}

	static class TestCase {
		String s;
		int expected;

		public TestCase(String s, int expected) {
			this.s = s;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase("", 0),
			new TestCase("a", 1),
			new TestCase("aa", 1),
			new TestCase("ab", 2),
			new TestCase("aab", 2),
			new TestCase("abb", 2),
			new TestCase("abbc", 2),
			new TestCase("ababc", 3),
			new TestCase("abcabcbb", 3),
			new TestCase("dvdf", 3),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = lengthOfLongestSubstring(tests[i].s);
			assert(result == tests[i].expected) : i;
		}
	}
}
