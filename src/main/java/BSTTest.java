package main.java;

import java.util.List;

public class BSTTest {
	public static void main(String[] args) {
		add();
		remove();
		contains();
	}

	static void add() {
		var tree = new BST();
		assert(tree.allItems().isEmpty());
		tree.add(0);
		assert(tree.allItems().equals(List.of(0)));
		tree.add(1);
		assert(tree.allItems().equals(List.of(0, 1)));
		tree.add(3);
		assert(tree.allItems().equals(List.of(0, 1, 3)));
		tree.add(-1);
		assert(tree.allItems().equals(List.of(-1, 0, 1, 3)));
		tree.add(2);
		assert(tree.allItems().equals(List.of(-1, 0, 1, 2, 3)));
	}

	static void remove() {
		var tree = new BST();
		tree.add(3);
		tree.add(1);
		tree.add(0);
		tree.add(2);
		tree.add(4);
		tree.add(5);
		assert(tree.allItems().equals(List.of(0, 1, 2, 3, 4, 5)));
		tree.remove(5);
		assert(tree.allItems().equals(List.of(0, 1, 2, 3, 4)));
		tree.remove(3);
		assert(tree.allItems().equals(List.of(0, 1, 2, 4)));
		tree.remove(1);
		assert(tree.allItems().equals(List.of(0, 2, 4)));
		tree.remove(0);
		assert(tree.allItems().equals(List.of(2, 4)));
		tree.remove(4);
		assert(tree.allItems().equals(List.of(2)));
		tree.remove(2);
		assert(tree.allItems().isEmpty());
	}

	static void contains() {
		var tree = new BST();
		tree.add(3);
		tree.add(1);
		tree.add(0);
		tree.add(2);
		tree.add(4);
		tree.add(5);

		assert(tree.contains(3));
		assert(tree.contains(1));
		assert(tree.contains(0));
		assert(tree.contains(2));
		assert(tree.contains(4));
		assert(tree.contains(5));
	}
}
