package main.java;

import java.util.ArrayDeque;
import java.util.Objects;

public class RemoveNthFromEnd {
	static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
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

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		var right = head;

		for (int i = 0; i < n; i++) {
			right = right.next;
		}

		if (right == null) {
			return head.next;
		}

		var left = head;

		while (right.next != null) {
			right = right.next;
			left = left.next;
		}

		if (left.next != null) {
			left.next = left.next.next;
		}

		return head;
	}

	static class TestCase {
		ListNode head;
		int n;
		ListNode expected;

		public TestCase(ListNode head, int n, ListNode expected) {
			this.head = head;
			this.n = n;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new ListNode(0),
				1,
				null
			),
			new TestCase(
				new ListNode(0, new ListNode(1)),
				1,
				new ListNode(0)
			),
			new TestCase(
				new ListNode(0, new ListNode(1)),
				2,
				new ListNode(1)
			),
			new TestCase(
				new ListNode(0, new ListNode(1, new ListNode(2))),
				1,
				new ListNode(0, new ListNode(1))
			),
			new TestCase(
				new ListNode(0, new ListNode(1, new ListNode(2))),
				2,
				new ListNode(0, new ListNode(2))
			),
			new TestCase(
				new ListNode(0,
					new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))))
				),
				2,
				new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(4))))
			),
		};

		for (var i = 0; i < tests.length; i++) {
			var result = removeNthFromEnd(tests[i].head, tests[i].n);
			assert(Objects.equals(result, tests[i].expected)) : i;
		}
	}
}
