package main.java;

import java.util.ArrayList;
import java.util.Collections;

public class HashSet {
	static class Entry {
		int item;
		boolean deleted;

		Entry(int item) {
			this.item = item;
			this.deleted = false;
		}
	}

	ArrayList<Entry> array;
	int size;

	HashSet() {
		this.array = new ArrayList<>();
		this.size = 0;
	}

	void add(int item) {
		ensureCapacity();

		var index = getStartingIndex(item);
		while (true) {
			if (array.get(index) == null) {
				array.set(index, new Entry(item));
				size++;
				return;
			}

			if (!array.get(index).deleted && array.get(index).item == item) {
				return;
			}

			index = (index + 1) % array.size();
		}
	}

	int getStartingIndex(int hash) {
		return hash % array.size();
	}

	void ensureCapacity() {
		if ((size + 1) * 2 >= array.size()) {
			var oldArray = array;

			array = new ArrayList<>(Collections.nCopies((size + 1) * 3, null));
			size = 0;

			for (var item : oldArray) {
				if (item != null && !item.deleted) {
					add(item.item);
				}
			}
		}
	}

	boolean contains(int item) {
		if (array.size() == 0) {
			return false;
		}

		var index = getStartingIndex(item);
		for (int i = 0; i < array.size(); i++) {
			var entry = array.get(index);
			if (entry != null && !entry.deleted && entry.item == item) {
				return true;
			}

			index = (index + 1) % array.size();
		}

		return false;
	}

	void remove(int item) {
		if (array.size() == 0) {
			return;
		}

		var index = getStartingIndex(item);
		for (int i = 0; i < array.size(); i++) {
			var entry = array.get(index);
			if (entry != null && !entry.deleted && entry.item == item) {
				array.get(index).deleted = true;
				size--;
				return;
			}

			index = (index + 1) % array.size();
		}
	}
}
