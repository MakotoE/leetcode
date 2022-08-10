package main.java;

import java.util.Arrays;
import java.util.Objects;

public class SortedArrayToBST {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			TreeNode treeNode = (TreeNode) o;
			return val == treeNode.val
				&& Objects.equals(left, treeNode.left)
				&& Objects.equals(right, treeNode.right);
		}
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		/*
		Pick middle item, and make that root node
		repeat for each half
		 */

		if (nums.length == 0) {
			return null;
		}

		var middle = nums.length / 2;
		var node = new TreeNode(nums[middle]);
		node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, middle));
		node.right = sortedArrayToBST(Arrays.copyOfRange(nums, middle + 1, nums.length));
		return node;
	}

	static class TestCase {
		int[] nums;
		TreeNode expected;

		public TestCase(int[] nums, TreeNode expected) {
			this.nums = nums;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new int[]{},
				null
			),
			new TestCase(
				new int[]{1},
				new TreeNode(1)
			),
			new TestCase(
				new int[]{0, 1},
				new TreeNode(1, new TreeNode(0), null)
			),
			new TestCase(
				new int[]{0, 1, 2},
				new TreeNode(1, new TreeNode(0), new TreeNode(2))
			),
			new TestCase(
				new int[]{0, 1, 2, 3},
				new TreeNode(2,
					new TreeNode(1, new TreeNode(0), null),
					new TreeNode(3)
				)
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = sortedArrayToBST(tests[i].nums);
			assert(Objects.equals(result, tests[i].expected)) : i;
		}
	}
}
