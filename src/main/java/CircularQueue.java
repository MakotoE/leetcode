package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class CircularQueue {
	final ArrayList<Integer> items;
	int start;
	int size;
	final int capacity;

	CircularQueue(int capacity) {
		this.items = new ArrayList<>(Collections.nCopies(capacity, 0));
		this.start = 0;
		this.size = 0;
		this.capacity = capacity;
	}

	// Returns true if item was pushed.
	boolean push(int item) {
		if (this.size == this.capacity) {
			return false;
		}

		items.set((this.start + this.size) % this.capacity, item);
		this.size++;
		return true;
	}

	Optional<Integer> pop() {
		if (this.size == 0) {
			return Optional.empty();
		}

		var result = items.get(this.start);
		this.start = (this.start + 1) % this.capacity;
		this.size--;
		return Optional.of(result);
	}
}
