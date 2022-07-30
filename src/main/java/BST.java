package main.java;

import java.util.ArrayList;
import java.util.List;

public class BST {
	BinaryNode root;

	BST() {
		this.root = null;
	}

	List<Integer> allItems() {
		if (root == null) {
			return new ArrayList<>();
		}

		return nodeItems(root);
	}

	static ArrayList<Integer> nodeItems(BinaryNode node) {
		var result = new ArrayList<Integer>();
		if (node.left != null) {
			result.addAll(nodeItems(node.left));
		}
		result.add(node.item);
		if (node.right != null) {
			result.addAll(nodeItems(node.right));
		}

		return result;
	}

	void add(int item) {
		if (root == null) {
			root = new BinaryNode(item, null, null);
			return;
		}

		var curr = root;
		while (true) {
			if (item < curr.item) {
				if (curr.left == null) {
					curr.left = new BinaryNode(item, null, null);
					return;
				} else {
					curr = curr.left;
				}
			} else {
				if (curr.right == null) {
					curr.right = new BinaryNode(item, null, null);
					return;
				} else {
					curr = curr.right;
				}
			}
		}
	}

	void remove(int item) {
		if (root != null) {
			root = removeFromNode(root, item);
		}
	}

	static BinaryNode removeFromNode(BinaryNode node, int item) {
		if (item < node.item) {
			node.left = removeFromNode(node.left, item);
		} else if (item > node.item) {
			node.right = removeFromNode(node.right, item);
		} else {
			// Remove this node

			// 1 or 0 children
			if (node.left == null) {
				return node.right;
			} else if (node.right == null) {
				return node.left;
			}

			// 2 children
			node.item = minimumItem(node.right);
			node.right = removeFromNode(node.right, node.item);
		}

		return node;
	}

	static int minimumItem(BinaryNode node) {
		var curr = node;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr.item;
	}

	boolean contains(int item) {
		var curr = root;
		while (curr != null) {
			if (curr.item == item) {
				return true;
			}

			if (item < curr.item) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

		return false;
	}
}
