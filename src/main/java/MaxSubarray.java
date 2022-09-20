package main.java;

public class MaxSubarray {
	static public int findLength(int[] a, int[] b) {
		var table = new int[a.length + 1][b.length + 1];
		var max = 0;

		for (var aIndex = 0; aIndex <= a.length; aIndex++) {
			for (var bIndex = 0; bIndex <= b.length; bIndex++) {
				if (aIndex == 0 || bIndex == 0) {
					table[aIndex][bIndex] = 0;
				} else if (a[aIndex - 1] == b[bIndex - 1]) {
					table[aIndex][bIndex] = 1 + table[aIndex - 1][bIndex - 1];

					if (table[aIndex][bIndex] > max) {
						max = table[aIndex][bIndex];
					}
				}
			}
		}

		return max;
	}

	record TestCase(int[] nums1, int[] nums2, int expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new int[]{},
				new int[]{},
				0
			),
			new TestCase(
				new int[]{0},
				new int[]{},
				0
			),
			new TestCase(
				new int[]{0, 1},
				new int[]{},
				0
			),
			new TestCase(
				new int[]{0},
				new int[]{0},
				1
			),
			new TestCase(
				new int[]{0, 1},
				new int[]{0},
				1
			),
			new TestCase(
				new int[]{0, 1},
				new int[]{1},
				1
			),
			new TestCase(
				new int[]{0, 1},
				new int[]{0, 1},
				2
			),
			new TestCase(
				new int[]{0, 1, 2},
				new int[]{0, 1},
				2
			),
			new TestCase(
				new int[]{0, 1, 2},
				new int[]{1, 2},
				2
			),
			new TestCase(
				new int[]{0, 1, 2},
				new int[]{0, 2},
				1
			),
			new TestCase(
				new int[]{0, 1, 2, 3},
				new int[]{1, 2},
				2
			),
			new TestCase(
				new int[]{0, 1, 2, 3},
				new int[]{0, 2, 3},
				2
			),
			new TestCase(
				new int[]{0, 0, 0, 0, 1},
				new int[]{1, 0, 0, 0, 0},
				4
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result0 = findLength(tests[i].nums1, tests[i].nums2);
			assert result0 == tests[i].expected : i;
			var result1 = findLength(tests[i].nums2, tests[i].nums1);
			assert result1 == tests[i].expected : i;
		}
	}
}
