package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LongestPalindrome {
	static HashMap<Character, ArrayList<Integer>> duplicateLetters(String s) {
		var duplicateLetters = new HashMap<Character, ArrayList<Integer>>();

		for (int i = 0; i < s.length(); i++) {
			var c = s.charAt(i);
			if (!duplicateLetters.containsKey(c)) {
				duplicateLetters.put(c, new ArrayList<>());
			}
			duplicateLetters.get(c).add(i);
		}

		for (var key : new HashSet<>(duplicateLetters.keySet())) {
			if (duplicateLetters.get(key).size() <= 1) {
				duplicateLetters.remove(key);
			}
		}

		return duplicateLetters;
	}

	static boolean isPalindrome(String s) {
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	public static String longestPalindrome(String s) {
		/*
		Find letters and their indices that appear twice
		Use the indices to check if it's a palindrome
		 */

		var letterToIndices = duplicateLetters(s);

		var longestPalindrome = "";

		for (var value : letterToIndices.values()) {
			for (int startIndex = 0; startIndex < value.size(); startIndex++) {
				for (int endIndex = value.size() - 1; endIndex >= startIndex; endIndex--) {
					var substring = s.substring(value.get(startIndex), value.get(endIndex) + 1);
					var isPalindrome = isPalindrome(substring);
					if (isPalindrome && substring.length() > longestPalindrome.length()) {
						longestPalindrome = substring;

						if (longestPalindrome.length() == s.length()) {
							return longestPalindrome;
						}
					}
				}
			}
		}

		// s did not have any palindrome
		if (longestPalindrome.isEmpty() && !s.isEmpty()) {
			longestPalindrome = s.substring(0, 1);
		}

		return longestPalindrome;
	}

	static class TestCase {
		String s;
		String expected;

		public TestCase(String s, String expected) {
			this.s = s;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		{
			var result0 = duplicateLetters("");
			assert(result0.size() == 0);
			var result1 = duplicateLetters("aa");
			assert(result1.size() == 1);
		}
		{
			var result0 = isPalindrome("");
			assert(result0);
			var result1 = isPalindrome("a");
			assert(result1);
			var result2 = isPalindrome("ab");
			assert(!result2);
			var result3 = isPalindrome("aba");
			assert(result3);
			var result4 = isPalindrome("ababa");
			assert(result4);
		}
		{
			var tests = new TestCase[] {
				new TestCase("", ""),
				new TestCase("a", "a"),
				new TestCase("ab", "a"),
				new TestCase("aba", "aba"),
				new TestCase("abab", "aba"),
				new TestCase("cabab", "aba"),
				new TestCase("babad", "aba"),
				new TestCase("cbbd", "bb"),
				new TestCase("abacbaab", "baab"),
			};

			for (int i = 0; i < tests.length; i++) {
				var result = longestPalindrome(tests[i].s);
				assert(result.equals(tests[i].expected)) : i;
			}
		}
	}
}
