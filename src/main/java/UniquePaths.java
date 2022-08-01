package main.java;

import java.util.HashMap;
import java.util.Objects;

public class UniquePaths {
	public static int uniquePaths(int width, int height) {
		var cache = new HashMap<CacheEntry, Integer>();
		return getPathCount(cache, width, height, 1, 1);
	}

	static class CacheEntry {
		int xDifference;
		int yDifference;

		public CacheEntry(int xDifference, int yDifference) {
			this.xDifference = xDifference;
			this.yDifference = yDifference;
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
			return xDifference == that.xDifference && yDifference == that.yDifference
				|| xDifference == that.yDifference && yDifference == that.xDifference;
		}

		@Override
		public int hashCode() {
			return Objects.hash(xDifference + yDifference);
		}
	}

	static int getPathCount(HashMap<CacheEntry, Integer> cache, int width, int height, int x, int y) {
		int result = 0;

		if (y < height) {
			var entry = new CacheEntry(width - x, height - (y + 1));
			if (cache.containsKey(entry)) {
				result += cache.get(entry);
			} else {
				var count = getPathCount(cache, width, height, x, y + 1);
				cache.put(entry, count);
				result += count;
			}
		}

		if (x < width) {
			var entry = new CacheEntry(width - (x + 1), height - y);
			if (cache.containsKey(entry)) {
				result += cache.get(entry);
			} else {
				var count = getPathCount(cache, width, height, x + 1, y);
				cache.put(entry, count);
				result += count;
			}
		}

		if (x == width && y == height) {
			return 1;
		}

		return result;
	}

	static class TestCase {
		int height;
		int width;
		int expected;

		public TestCase(int height, int width, int expected) {
			this.height = height;
			this.width = width;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[]{
			new TestCase(0, 0, 0),
			new TestCase(1, 1, 1),
			new TestCase(2, 2, 2),
			new TestCase(3, 3, 6),
			new TestCase(3, 2, 3),
			new TestCase(2, 3, 3),
			new TestCase(4, 3, 10),
			new TestCase(3, 4, 10),
			new TestCase(23, 12, 193536720),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = uniquePaths(tests[i].width, tests[i].height);
			assert(result == tests[i].expected) : i;
		}
	}
}
