package main.java;

public class HeapTest {
	public static void main(String[] args) {
		var heap = new Heap();
		assert(heap.pop().isEmpty());

		heap.push(0);
		assert(heap.pop().get() == 0);
		assert(heap.pop().isEmpty());

		heap.push(0);
		heap.push(1);
		heap.push(1);
		heap.push(2);
		assert(heap.pop().get() == 0);
		assert(heap.pop().get() == 1);
		assert(heap.pop().get() == 1);
		assert(heap.pop().get() == 2);
		assert(heap.pop().isEmpty());


		heap.push(0);
		heap.push(4);
		heap.push(2);
		heap.push(3);
		heap.push(1);
		assert(heap.pop().get() == 0);
		assert(heap.pop().get() == 1);
		assert(heap.pop().get() == 2);
		heap.push(5);
		heap.push(-1);
		assert(heap.pop().get() == -1);
		assert(heap.pop().get() == 3);
		assert(heap.pop().get() == 4);
		assert(heap.pop().get() == 5);
		assert(heap.pop().isEmpty());
	}
}
