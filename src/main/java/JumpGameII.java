package main.java;

import java.util.Arrays;

public class JumpGameII {
	public static int jump(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		var steps = new int[nums.length];
		Arrays.fill(steps, 1, nums.length, Integer.MAX_VALUE);

		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i + nums[i] >= j && steps[i] + 1 < steps[j]) {
					steps[j] = steps[i] + 1;
				}
			}
		}

		return steps[nums.length - 1];
	}

	record TestCase(int[] nums, int expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new int[] {},
				0
			),
			new TestCase(
				new int[] {0},
				0
			),
			new TestCase(
				new int[] {1},
				0
			),
			new TestCase(
				new int[] {1, 2},
				1
			),
			new TestCase(
				new int[] {2, 3, 1, 1, 4},
				2
			),
			new TestCase(
				new int[] {2, 1, 3, 1, 4},
				2
			),
			new TestCase(
				new int[] {2, 3, 0, 1, 4},
				2
			),
			new TestCase(
				new int[] {1, 1, 1, 1},
				3
			),
			new TestCase(
				new int[] {1, 1, 1, 1, 1},
				4
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = jump(tests[i].nums);
			assert result == tests[i].expected : i;
		}
	}
}
