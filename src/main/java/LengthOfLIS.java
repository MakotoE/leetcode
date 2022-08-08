package main.java;

import java.util.ArrayList;
import java.util.Collections;

public class LengthOfLIS {
	public static int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		var longestSequences = new ArrayList<>(Collections.nCopies(nums.length, 1));
		int max = 0;
		for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
			for (int leftIndex = 0; leftIndex < rightIndex; leftIndex++) {
				if (nums[leftIndex] < nums[rightIndex]) {
					if (longestSequences.get(leftIndex) + 1 > longestSequences.get(rightIndex)) {
						longestSequences.set(rightIndex, longestSequences.get(leftIndex) + 1);

						if (longestSequences.get(rightIndex) > max) {
							max = longestSequences.get(rightIndex);
						}
					}
				}
			}
		}

		if (max == 0) {
			return 1;
		}

		return max;
	}

	static class TestCase {
		int[] nums;
		int expected;

		public TestCase(int[] nums, int expected) {
			this.nums = nums;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(new int[]{}, 0),
			new TestCase(new int[]{0, 3, 1, 6, 2, 2, 7}, 4),
			new TestCase(new int[]{0, 1, 0, 3, 2, 3}, 4),
			new TestCase(new int[]{7, 7, 7}, 1),
			new TestCase(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}, 6),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = lengthOfLIS(tests[i].nums);
			assert(result == tests[i].expected) : i;
		}
	}
}
