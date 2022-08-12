package main.java;

import java.util.HashMap;
import java.util.Objects;

public class RegularExpression {
	static class CacheEntry {
		String s;
		String pattern;

		public CacheEntry(String s, String pattern) {
			this.s = s;
			this.pattern = pattern;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			CacheEntry that = (CacheEntry) o;
			return s.equals(that.s) && pattern.equals(that.pattern);
		}

		@Override
		public int hashCode() {
			return Objects.hash(s, pattern);
		}
	}

	public static boolean isMatch(String s, String pattern) {
		return isMatch(s, pattern, new HashMap<>());
	}

	static boolean isMatch(String s, String pattern, HashMap<CacheEntry, Boolean> cache) {
		if (s.length() == 0) {
			return pattern.length() == 0 || (pattern.length() == 2 && pattern.charAt(1) == '*');
		}

		if (pattern.length() == 0) {
			return false;
		}

		var cacheEntry = new CacheEntry(s, pattern);
		if (cache.containsKey(cacheEntry)) {
			return cache.get(cacheEntry);
		}

		var result = false;
		if (pattern.length() > 1 && pattern.charAt(1) == '*') {
			result = isMatch(s, pattern.substring(2), cache)
				|| (
					(pattern.charAt(0) == '.' || s.charAt(0) == pattern.charAt(0))
					&& isMatch(s.substring(1), pattern, cache)
				);
		} else {
			result = (pattern.charAt(0) == '.' || pattern.charAt(0) == s.charAt(0))
				&& isMatch(s.substring(1), pattern.substring(1), cache);
		}

		cache.put(cacheEntry, result);
		return result;
	}

	static class TestCase {
		String s;
		String pattern;
		boolean expected;

		public TestCase(String s, String pattern, boolean expected) {
			this.s = s;
			this.pattern = pattern;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase("", "", true),
			new TestCase("a", "", false),
			new TestCase("", "a", false),
			new TestCase("a", "a", true),
			new TestCase("a", "b", false),
			new TestCase("ab", "a", false),
			new TestCase("a", "ab", false),
			new TestCase("a", ".", true),
			new TestCase("ab", ".", false),
			new TestCase("", ".", false),
			new TestCase("ab", "..", true),
			new TestCase("a", "a*", true),
			new TestCase("aa", "a*", true),
			new TestCase("aa", ".*", true),
			new TestCase("ab", ".*", true),
			new TestCase("ab", "a*b", true),
			new TestCase("aab", "a*b", true),
			new TestCase("aab", "a*b*", true),
			new TestCase("abb", "a*b*", true),
			new TestCase("aabb", "a*b*", true),
			new TestCase("aacbb", "a*b*", false),
			new TestCase("a", "b*a", true),
			new TestCase("a", "ab*", true),
			new TestCase("ab", ".*c", false),
			new TestCase("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*c", false),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = isMatch(tests[i].s, tests[i].pattern);
			assert(result == tests[i].expected) : i;
		}
	}
}
