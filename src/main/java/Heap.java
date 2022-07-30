package main.java;

import java.util.ArrayList;
import java.util.Optional;

public class Heap {
	ArrayList<Integer> array;

	Heap() {
		this.array = new ArrayList<>();
	}

	void push(int item) {
		array.add(0);

		var index = array.size() - 1;
		var parentIndex = (index - 1) / 2;

		while (index > 0 && array.get(parentIndex) > item) {
			array.set(index, array.get(parentIndex));

			index = parentIndex;
			parentIndex = (index - 1) / 2;
		}

		array.set(index, item);
	}

	Optional<Integer> pop() {
		if (array.size() == 0) {
			return Optional.empty();
		}

		var result = array.get(0);

		array.set(0, array.get(array.size() - 1));

		int index = 0;
		int leftChildIndex = 1;
		while (leftChildIndex < array.size()) {
			var smallerIndex = leftChildIndex;
			var rightChildIndex = 2 * index + 2;

			if (
				rightChildIndex < array.size()
					&& array.get(rightChildIndex) < array.get(leftChildIndex)
			) {
				smallerIndex = rightChildIndex;
			}

			if (array.get(index) < array.get(smallerIndex)) {
				break;
			} else {
				var currentItem = array.get(index);
				array.set(index, array.get(smallerIndex));
				array.set(smallerIndex, currentItem);
			}

			index = smallerIndex;
			leftChildIndex = 2 * index + 1;
		}
		array.remove(array.size() - 1);

		return Optional.of(result);
	}
}
