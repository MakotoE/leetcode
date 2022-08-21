package main.java;

import java.util.Objects;

public class SwapPairs {
	static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			ListNode listNode = (ListNode) o;
			return val == listNode.val && Objects.equals(next, listNode.next);
		}
	}

	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		var newHead = head.next;
		ListNode parent = null;
		var curr = newHead;
		var next = head;
		while (curr != null) {
			var end = curr.next;

			if (parent != null) {
				parent.next = curr;
			}

			curr.next = next;
			curr.next.next = end;
			parent = next;
			next = end;
			if (next == null) {
				break;
			} else {
				curr = next.next;
			}
		}
		return newHead;
	}

	static class TestCase {
		ListNode head;
		ListNode expected;

		public TestCase(ListNode head, ListNode expected) {
			this.head = head;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				null,
				null
			),
			new TestCase(
				new ListNode(0),
				new ListNode(0)
			),
			new TestCase(
				new ListNode(0, new ListNode(1)),
				new ListNode(1, new ListNode(0))
			),
			new TestCase(
				new ListNode(0, new ListNode(1, new ListNode(2))),
				new ListNode(1, new ListNode(0, new ListNode(2)))
			),
			new TestCase(
				new ListNode(
					0,
					new ListNode(1, new ListNode(2, new ListNode(3)))
				),
				new ListNode(
					1,
					new ListNode(0, new ListNode(3, new ListNode(2)))
				)
			),
			new TestCase(
				new ListNode(
					0,
					new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(
						4,
						new ListNode(5))))
					)
				),
				new ListNode(
					1,
					new ListNode(0, new ListNode(3, new ListNode(2, new ListNode(
						5,
						new ListNode(4))))
					)
				)
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = swapPairs(tests[i].head);
			assert(Objects.equals(result, tests[i].expected)) : i;
		}
	}
}
