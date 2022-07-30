package main.java;

public class HashSetListTest {
	public static void main(String[] args) {
		var set = new HashSetList();
		assert(!set.contains(0));
		set.remove(0);

		set.add(0);
		assert(set.contains(0));
		set.remove(0);
		assert(!set.contains(0));

		set.add(0);
		set.add(0);
		assert(set.contains(0));
		set.remove(0);
		assert(!set.contains(0));

		set.add(0);
		set.add(1);
		set.add(10);
		assert(set.contains(0));
		assert(set.contains(1));
		assert(set.contains(10));
		set.remove(0);
		assert(set.contains(10));
		set.remove(10);
		assert(!set.contains(10));

		for (int i = 0; i < 20; i++) {
			set.add(i);
			assert(set.contains(i));
		}

		for (int i = 0; i < 20; i++) {
			assert(set.contains(i));
		}

		for (int i = 0; i < 20; i++) {
			set.remove(i);
		}

		for (int i = 0; i < 20; i++) {
			assert(!set.contains(i));
		}
	}
}
