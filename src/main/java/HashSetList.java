package main.java;

import java.util.ArrayList;

public class HashSetList {
	ArrayList<ArrayList<Integer>> buckets;
	int size;

	HashSetList() {
		buckets = new ArrayList<>();
	}

	void add(int item) {
		if (!contains(item)) {
			ensureSize();
			buckets.get(bucketIndex(item)).add(item);
			size++;
		}
	}

	int bucketIndex(int item) {
		return item % buckets.size();
	}

	void ensureSize() {
		if (buckets.isEmpty()) {
			for (int i = 0; i < 10; i++) {
				buckets.add(new ArrayList<>());
			}
			return;
		}

		if (size > buckets.size() / 2) {
			var oldBuckets = buckets;

			buckets = new ArrayList<>(oldBuckets.size() * 2);
			for (int i = 0; i < oldBuckets.size() * 2; i++) {
				buckets.add(new ArrayList<>());
			}

			size = 0;

			for (var bucket : oldBuckets) {
				for (var item : bucket) {
					add(item);
				}
			}
		}
	}

	boolean contains(int item) {
		if (buckets.size() == 0) {
			return false;
		}

		for (var existing : buckets.get(bucketIndex(item))) {
			if (existing == item) {
				return true;
			}
		}
		return false;
	}

	void remove(int item) {
		if (buckets.size() == 0) {
			return;
		}

		var bucket = buckets.get(bucketIndex(item));
		for (int i = 0; i < bucket.size(); i++) {
			if (bucket.get(i) == item) {
				bucket.remove(i);
				return;
			}
		}
	}
}
