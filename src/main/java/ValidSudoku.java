package main.java;

import java.util.HashSet;

public class ValidSudoku {
	static boolean checkRowsAndColumns(char[][] board) {
		for (var row : board) {
			var set = new HashSet<Character>();

			for (var col = 0; col < 9; col++) {
				if (row[col] != '.') {
					if (set.contains(row[col])) {
						return false;
					}

					set.add(row[col]);
				}
			}
		}

		for (var col = 0; col < 9; col++) {
			var set = new HashSet<Character>();

			for (var row : board) {
				if (row[col] != '.') {
					if (set.contains(row[col])) {
						return false;
					}

					set.add(row[col]);
				}
			}
		}

		return true;
	}

	static boolean checkSubBoxes(char[][] board) {
		for (int boxRow = 0; boxRow < 3; boxRow++) {
			for (int boxCol = 0; boxCol < 3; boxCol++) {
				var set = new HashSet<Character>();

				for (int row = 0; row < 3; row++) {
					for (int col = 0; col < 3; col++) {
						var c = board[boxRow * 3 + row][boxCol * 3 + col];
						if (c != '.') {
							if (set.contains(c)) {
								return false;
							}

							set.add(c);
						}
					}
				}
			}
		}

		return true;
	}

	public static boolean isValidSudoku(char[][] board) {
		assert(board.length == 9);
		assert(board[0].length == 9);

		if (!checkRowsAndColumns(board)) {
			return false;
		}

		return checkSubBoxes(board);
	}

	record TestCase(
		char[][] board,
		boolean expected
	) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new char[][]{
					{'5','3','.','.','7','.','.','.','.'},
					{'6','.','.','1','9','5','.','.','.'},
					{'.','9','8','.','.','.','.','6','.'},
					{'8','.','.','.','6','.','.','.','3'},
					{'4','.','.','8','.','3','.','.','1'},
					{'7','.','.','.','2','.','.','.','6'},
					{'.','6','.','.','.','.','2','8','.'},
					{'.','.','.','4','1','9','.','.','5'},
					{'.','.','.','.','8','.','.','7','9'},
				},
				true
			),
			new TestCase(
				new char[][] {
					{'8','3','.','.','7','.','.','.','.'},
					{'6','.','.','1','9','5','.','.','.'},
					{'.','9','8','.','.','.','.','6','.'},
					{'8','.','.','.','6','.','.','.','3'},
					{'4','.','.','8','.','3','.','.','1'},
					{'7','.','.','.','2','.','.','.','6'},
					{'.','6','.','.','.','.','2','8','.'},
					{'.','.','.','4','1','9','.','.','5'},
					{'.','.','.','.','8','.','.','7','9'},
				},
				false
			),
		};

		for (int i = 0; i < tests.length; i++) {
			var result = isValidSudoku(tests[i].board);
			assert(result == tests[i].expected) : i;
		}
	}
}
