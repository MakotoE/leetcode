package main.java;

import java.util.Arrays;

public class NextPermutation {
	/*
	123
	132
	213
	231
	312
	321

	1234
	1243
	1324
	1342
	1423
	1432
	2134
	2143
	2314
	2341
	3124
	3142
	3214
	3241
	3412
	3421
	 */
	static int swapIndex(int[] nums, int startingAtIndex) {
		var lowestIndex = startingAtIndex + 1;
		for (int i = startingAtIndex + 1; i < nums.length; i++) {
			if (nums[i] > nums[startingAtIndex] && nums[i] < nums[lowestIndex]) {
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}

	static int lowestIndex(int[] nums, int startingAtIndex) {
		var lowestIndex = startingAtIndex;
		for (int i = startingAtIndex + 1; i < nums.length; i++) {
			if (nums[i] < nums[lowestIndex]) {
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}

	static void swap(int[] nums, int indexA, int indexB) {
		var tmp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = tmp;
	}

	public static void nextPermutation(int[] nums) {
		if (nums.length <= 1) {
			return;
		}

		var highestNumber = Math.max(nums[nums.length - 2], nums[nums.length - 1]);
		var indexToCheck = nums.length - 2;

		while (true) {
			if (nums[indexToCheck] < highestNumber) {
				var swapIndex = swapIndex(nums, indexToCheck);
				swap(nums, indexToCheck, swapIndex);

				for (int i = indexToCheck + 1; i < nums.length - 1; i++) {
					var lowestIndex = lowestIndex(nums, i);
					swap(nums, i, lowestIndex);
				}
				break;
			}

			if (indexToCheck == 0) {
				Arrays.sort(nums);
				return;
			}

			indexToCheck--;

			if (nums[indexToCheck] > highestNumber) {
				highestNumber = nums[indexToCheck];
			}
		}
	}

	record TestCase(int[] nums, int[] expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new int[] {},
				new int[] {}
			),
			new TestCase(
				new int[] {0},
				new int[] {0}
			),
			new TestCase(
				new int[] {0, 1},
				new int[] {1, 0}
			),
			new TestCase(
				new int[] {1, 0},
				new int[] {0, 1}
			),
			new TestCase(
				new int[] {0, 1, 2},
				new int[] {0, 2, 1}
			),
			new TestCase(
				new int[] {0, 2, 1},
				new int[] {1, 0, 2}
			),
			new TestCase(
				new int[] {1, 0, 2},
				new int[] {1, 2, 0}
			),
			new TestCase(
				new int[] {1, 2, 0},
				new int[] {2, 0, 1}
			),
			new TestCase(
				new int[] {2, 0, 1},
				new int[] {2, 1, 0}
			),
			new TestCase(
				new int[] {2, 1, 0},
				new int[] {0, 1, 2}
			),
			new TestCase(
				new int[] {0, 1, 2, 3},
				new int[] {0, 1, 3, 2}
			),
			new TestCase(
				new int[] {0, 1, 3, 2},
				new int[] {0, 2, 1, 3}
			),
			new TestCase(
				new int[] {0, 3, 2, 1},
				new int[] {1, 0, 2, 3}
			),
			new TestCase(
				new int[] {3, 2, 1, 0},
				new int[] {0, 1, 2, 3}
			),
		};

		for (int i = 0; i < tests.length; i++) {
			nextPermutation(tests[i].nums);
			assert(Arrays.equals(tests[i].nums, tests[i].expected)) : i;
		}
	}
}
