package main.java;

public class CircularQueueTest {
	@SuppressWarnings({
		"AssertWithSideEffects",
		"OptionalGetWithoutIsPresent"
	})
	public static void main(String[] args) {
		{
			var queue = new CircularQueue(0);
			assert(!queue.push(0));
			assert(queue.pop().isEmpty());
		}
		{
			var queue = new CircularQueue(1);
			assert(queue.push(0));
			assert(!queue.push(1));
			assert(queue.pop().get() == 0);
			assert(queue.pop().isEmpty());
			assert(queue.push(1));
			assert(queue.pop().get() == 1);
		}
		{
			var queue = new CircularQueue(4);
			assert(queue.pop().isEmpty());

			assert(queue.push(0));
			assert(queue.push(1));
			assert(queue.push(2));
			assert(queue.push(3));
			assert(!queue.push(4));
			assert(queue.pop().get() == 0);
			assert(queue.pop().get() == 1);
			assert(queue.pop().get() == 2);
			assert(queue.pop().get() == 3);
			assert(queue.pop().isEmpty());
		}
		{
			var queue = new CircularQueue(4);
			assert(queue.push(0));
			assert(queue.pop().get() == 0);
			assert(queue.push(1));
			assert(queue.pop().get() == 1);
			assert(queue.push(2));
			assert(queue.push(3));
			assert(queue.push(4));
			assert(queue.pop().get() == 2);
			assert(queue.push(5));
			assert(queue.pop().get() == 3);
			assert(queue.pop().get() == 4);
			assert(queue.push(6));
			assert(queue.pop().get() == 5);
			assert(queue.pop().get() == 6);
		}
	}
}
