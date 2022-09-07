package main.java;

public class Tree2Str {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static String tree2str(TreeNode root) {
		if (root.left == null && root.right == null) {
			return String.valueOf(root.val);
		}

		var builder = new StringBuilder(String.valueOf(root.val));

		builder.append("(");
		if (root.left != null) {
			builder.append(tree2str(root.left));
		}
		builder.append(")");

		if (root.right != null) {
			builder.append("(");
			builder.append(tree2str(root.right));
			builder.append(")");
		}

		return builder.toString();
	}

	record TestCase(TreeNode root, String expected) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new TreeNode(0),
				"0"
			),
			new TestCase(
				new TreeNode(
					1,
					new TreeNode(2, new TreeNode(4), null),
					new TreeNode(3)
				),
				"1(2(4))(3)"
			),
			new TestCase(
				new TreeNode(
					1,
					new TreeNode(2, null, new TreeNode(4)),
					new TreeNode(3)
				),
				"1(2()(4))(3)"
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = tree2str(tests[i].root);
			assert result.equals(tests[i].expected) : i;
		}
	}
}
