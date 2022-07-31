package main.java;

public class NumArray {
	static class SegmentTree {
		SegmentTree left;
		SegmentTree right;
		Integer number;

		SegmentTree(int[] numbers, int start, int end) {
			if (start == end) {
				number = numbers[start];
				return;
			}

			int mid = start + (end - start) / 2;
			left = new SegmentTree(numbers, start, mid);
			right = new SegmentTree(numbers, mid + 1, end);
		}

		int query(int start, int end, int i, int j) {
			if (start > j || end < i) {
				return 0;
			}

			if (i <= start && j >= end && number != null) {
				return number;
			}

			int mid = start + (end - start) / 2;
			if (i > mid) {
				return right.query(mid + 1, end, i, j);
			} else if (j <= mid) {
				return left.query(start, mid, i, j);
			}

			var leftQuery = left.query(start, mid, i, mid);
			var rightQuery = right.query(mid + 1, end, mid + 1, j);

			if (start == i && end == j) {
				number = leftQuery + rightQuery;
			}
			return leftQuery + rightQuery;
		}

		void update(int start, int end, int i, int value) {
			if (start > i || end < i) {
				return;
			}

			if (i <= start && end <= i) {
				number = value;
				return;
			}

			var mid = start + (end - start) / 2;
			left.update(start, mid, i, value);
			right.update(mid + 1, end, i, value);

			number = null;
		}
	}

	final SegmentTree segmentTree;
	final int size;

	public NumArray(int[] nums) {
		this.size = nums.length;
		this.segmentTree = new SegmentTree(nums, 0, this.size - 1);
	}

	public void update(int index, int val) {
		segmentTree.update(0, size - 1, index, val);
	}

	public int sumRange(int left, int right) {
		return segmentTree.query(0, size - 1, left, right);
	}

	public static void main(String[] args) {
		{
			var tree = new SegmentTree(new int[]{0, 1, 2}, 0, 2);
			assert(tree.number == null);
			assert(tree.left.number == null);
			assert(tree.left.left.number == 0);
			assert(tree.left.right.number == 1);
			assert(tree.right.number == 2);

			var result = tree.query(0, 2, 0, 2);
			assert(result == 3);
		}
		{
			var tree = new SegmentTree(new int[]{0, 1, 2, 3, 4, 5}, 0, 5);
			var result0 = tree.query(0, 5, 0, 5);
			assert(result0 == 15);

			tree.update(0, 5, 0, 6);
			var result1 = tree.query(0, 5, 0, 5);
			assert(result1 == 21);

			tree.update(0, 5, 1, 7);
			var result2 = tree.query(0, 5, 0, 5);
			assert(result2 == 27);
		}
		{
			var array = new NumArray(new int[]{0});
			array.update(0, 1);
			assert(array.sumRange(0, 0) == 1);
		}
		{
			var array = new NumArray(new int[]{1, 2});
			assert(array.sumRange(0, 1) == 3);
		}
		{
			var array = new NumArray(new int[]{9, -8});
			array.update(0, 3);
			var result0 = array.sumRange(1, 1);
			assert(result0 == -8);
			var result1 = array.sumRange(0, 1);
			assert(result1 == -5);
			array.update(1, -3);
			var result2 = array.sumRange(0, 1);
			assert(result2 == 0);
		}
		{
			var array = new NumArray(new int[]{7, 2, 7, 2, 0});
			array.update(4, 6);
			array.update(0, 2);
			array.update(0, 9);
			var result0 = array.sumRange(4, 4);
			assert(result0 == 6);
			array.update(3, 8);
			var result1 = array.sumRange(0, 4);
			assert(result1 == 32);
			array.update(4, 1);
			var result2 = array.sumRange(0, 3);
			assert(result2 == 26);
			var result3 = array.sumRange(0, 4);
			assert(result3 == 27);
		}
	}
}
