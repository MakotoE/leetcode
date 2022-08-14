package main.java;

import java.util.*;
import java.util.HashSet;

public class ThreeSum {
	public static List<List<Integer>> threeSum(int[] numbers) {
		if (numbers.length < 3) {
			return List.of();
		}

		Arrays.sort(numbers);

		var set = new HashSet<List<Integer>>();

		for (int i = 0; i < numbers.length; i++) {
			int j = i + 1;
			int k = numbers.length - 1;

			while (j < k) {
				var sum = numbers[j] + numbers[k];
				if (sum == -numbers[i]) {
					set.add(List.of(numbers[i], numbers[j], numbers[k]));
					j++;
					k--;
				} else if (sum > -numbers[i]) {
					k--;
				} else {
					j++;
				}
			}
		}

		return new ArrayList<>(set);
	}

	static class TestCases {
		int[] numbers;
		List<List<Integer>> expected;

		public TestCases(int[] numbers, List<List<Integer>> expected) {
			this.numbers = numbers;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCases[] {
			new TestCases(
				new int[]{},
				List.of()
			),
			new TestCases(
				new int[]{0},
				List.of()
			),
			new TestCases(
				new int[]{0, 0, 0},
				List.of(
					List.of(0, 0, 0)
				)
			),
			new TestCases(
				new int[]{0, 0, 1},
				List.of()
			),
			new TestCases(
				new int[]{0, 1, -1},
				List.of(
					List.of(-1, 0, 1)
				)
			),
			new TestCases(
				new int[]{2, 1, -1, -3, 1, -2},
				List.of(
					List.of(-3, 1, 2),
					List.of(-2, 1, 1)
				)
			),
			new TestCases(
				new int[]{-1, 0, 1, 2, -1, -4},
				List.of(
					List.of(-1, -1, 2),
					List.of(-1, 0, 1)
				)
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = threeSum(tests[i].numbers);
			assert(result.equals(tests[i].expected)) : i;
		}
	}
}
