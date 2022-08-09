package main.java;

import java.util.ArrayList;

public class ZigzagConversion {
	enum Direction {
		Up,
		Down,
	}

	public static String convert(String s, int numRows) {
		if (numRows == 0) {
			return "";
		}

		if (numRows == 1) {
			return s;
		}

		var rows = new ArrayList<StringBuilder>(numRows);

		for (int i = 0; i < numRows; i++) {
			rows.add(new StringBuilder());
		}

		var currentRow = 0;
		var currentDirection = Direction.Down;
		for (int i = 0; i < s.length(); i++) {
			rows.get(currentRow).append(s.charAt(i));

			if (currentRow == 0) {
				currentDirection = Direction.Down;
			} else if (currentRow == numRows - 1) {
				currentDirection = Direction.Up;
			}

			if (currentDirection == Direction.Up) {
				currentRow--;
			} else {
				currentRow++;
			}
		}

		var result = new StringBuilder();
		for (var row : rows) {
			result.append(row);
		}
		return result.toString();
	}

	static class TestCase {
		String s;
		int numRows;
		String expected;

		public TestCase(String s, int numRows, String expected) {
			this.s = s;
			this.numRows = numRows;
			this.expected = expected;
		}
	}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase("", 0, ""),
			new TestCase("0", 1, "0"),
			new TestCase("0123456789", 0, ""),
			new TestCase("0123456789", 1, "0123456789"),
			new TestCase("0123456789", 2, "0246813579"),
			new TestCase("0123456789", 3, "0481357926"),
			new TestCase("0123456789", 4, "0615724839"),
			new TestCase("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),
			new TestCase("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = convert(tests[i].s, tests[i].numRows);
			assert(result.equals(tests[i].expected)) : i;
		}
	}
}
