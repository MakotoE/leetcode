package main.java;

public class ContainerWithMostWater {
	public static int maxArea(int[] heights) {
		int leftIndex = 0;
		int rightIndex = heights.length - 1;
		int maxArea = 0;

		while (leftIndex < rightIndex) {
			var width = rightIndex - leftIndex;
			var height = Math.min(heights[leftIndex], heights[rightIndex]);
			var area = width * height;
			if (area > maxArea) {
				maxArea = area;
			}

			if (heights[leftIndex] > heights[rightIndex]) {
				rightIndex--;
			} else {
				leftIndex++;
			}
		}

		return maxArea;
	}

	static class TestCase {
		int[] heights;
		int expected;

		public TestCase(int[] heights, int expected) {
			this.heights = heights;
			this.expected = expected;
		}
	}

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
				new int[] {0, 0},
				0
			),
			new TestCase(
				new int[] {0, 1},
				0
			),
			new TestCase(
				new int[] {1, 1},
				1
			),
			new TestCase(
				new int[] {2, 2},
				2
			),
			new TestCase(
				new int[] {2, 1, 2},
				4
			),
			new TestCase(
				new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7},
				49
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = maxArea(tests[i].heights);
			assert(result == tests[i].expected) : i;
		}
	}
}
