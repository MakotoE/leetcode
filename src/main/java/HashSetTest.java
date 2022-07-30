package main.java;

public class HashSetTest {
	public static void main(String[] args) {
		{
			var set = new HashSet();
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
			set.add(8);
			set.add(1);

			set.remove(0);
			set.add(0);
			set.remove(0);
			assert(!set.contains(0));
		}
	}
}
