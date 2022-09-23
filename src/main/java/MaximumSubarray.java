package main.java;

public class MaximumSubarray {
	public static int maxSubArray(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		var currentSum = nums[0];
		var maxSum =  nums[0];

		for (var i = 1; i < nums.length; i++) {
			currentSum = Integer.max(currentSum + nums[i], nums[i]);
			maxSum = Integer.max(currentSum, maxSum);
		}

		return maxSum;
	}

	record TestCase(int[] nums, int expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new int[]{},
				0
			),
			new TestCase(
				new int[]{1},
				1
			),
			new TestCase(
				new int[]{0, 1},
				1
			),
			new TestCase(
				new int[]{5, 4, -1, 7, 8},
				23
			),
			new TestCase(
				new int[]{-1},
				-1
			),
			new TestCase(
				new int[]{-2, 1},
				1
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = maxSubArray(tests[i].nums);
			assert result == tests[i].expected : i;
		}
	}
}
