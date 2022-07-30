import java.util.ArrayList;
import java.util.List;

public class Spiral {
	/*
	0 1
	3 2

	6 7 8
	5 0 1
	4 3 2

	20 21 22 23 24
	19  6  7  8  9
	18  5  0  1 10
	17  4  3  2 11
	16 15 14 13 12
	 */

	enum Direction {
		Right, Down, Left, Up,
	}

	public static ArrayList<ArrayList<Integer>> getMatrix(int n) {
		var width = (int)(Math.sqrt(n)) + 1;

		var result = new ArrayList<ArrayList<Integer>>();
		for (int row = 0; row < width; row++) {
			result.add(new ArrayList<>());
			for (int col = 0; col < width; col++) {
				result.get(row).add(null);
			}
		}

		int currentNumber = 0;
		int row = (width - 1) / 2;
		int col = row;
		var currentDirection = Direction.Right;
		int currentSideIndex = 0;
		int sideLength = 1;

		while (currentNumber <= n) {
			result.get(row).set(col, currentNumber);

			switch (currentDirection) {
			case Right -> {
				if (currentSideIndex == sideLength) {
					currentDirection = Direction.Down;
					currentSideIndex = 1;
					row++;
				} else {
					currentSideIndex++;
					col++;
				}
			}
			case Down -> {
				if (currentSideIndex == sideLength) {
					currentDirection = Direction.Left;
					currentSideIndex = 1;
					sideLength++;
					col--;
				} else {
					currentSideIndex++;
					row++;
				}
			}
			case Left -> {
				if (currentSideIndex == sideLength) {
					currentDirection = Direction.Up;
					currentSideIndex = 1;
					row--;
				} else {
					currentSideIndex++;
					col--;
				}
			}
			case Up -> {
				if (currentSideIndex == sideLength) {
					currentDirection = Direction.Right;
					currentSideIndex = 1;
					sideLength++;
					col++;
				} else {
					currentSideIndex++;
					row--;
				}
			}
			}

			currentNumber++;
		}

		return result;
	}

	static String spiralString(int n) {
		var matrix = getMatrix(n);

		var result = new StringBuilder();

		for (var row : matrix) {
			for (var number : row) {
				if (number == null) {
					result.append(' ');
				} else {
					result.append(number);
				}
				result.append(' ');
			}
			result.deleteCharAt(result.length() - 1);
			result.append('\n');
		}

		return result.toString();
	}

	public static void main(String[] args) {
		{
			var result = getMatrix(0);
			assert(result.size() == 1);
			assert(result.get(0).size() == 1);
			assert(result.get(0).get(0) == 0);
		}
		{
			var result = getMatrix(1);
			assert(result.size() == 2);
			assert(result.get(0).size() == 2);
			assert(result.get(0).get(0) == 0);
			assert(result.get(0).get(1) == 1);
			assert(result.get(1).get(1) == null);
			assert(result.get(1).get(0) == null);
		}
		{
			var result = getMatrix(8);
			assert(result.size() == 3);
			assert(result.get(0).size() == 3);

			var expected = List.of(
				List.of(6, 7, 8),
				List.of(5, 0, 1),
				List.of(4, 3, 2)
			);

			for (int row = 0; row < expected.size(); row++) {
				for (int col = 0; col < expected.get(0).size(); col++) {
					assert(result.get(row).get(col).equals(expected.get(row).get(col)));
				}
			}
		}
		{
			var result = getMatrix(9);
			assert(result.size() == 4);
			assert(result.get(0).size() == 4);
		}
		{
			var result = getMatrix(24);
			assert(result.size() == 5);
			assert(result.get(0).size() == 5);

			var expected = List.of(
				List.of(20, 21, 22, 23, 24),
				List.of(19, 6, 7, 8, 9),
				List.of(18, 5, 0, 1, 10),
				List.of(17, 4, 3, 2, 11),
				List.of(16, 15, 14, 13, 12)
			);

			for (int row = 0; row < expected.size(); row++) {
				for (int col = 0; col < expected.get(0).size(); col++) {
					assert(result.get(row).get(col).equals(expected.get(row).get(col)));
				}
			}
		}
		{
			var result = spiralString(0);
			assert(result.equals("0\n"));
		}
		{
			var result = spiralString(1);
			assert(result.equals("0 1\n   \n"));
		}
		{
			var result = spiralString(3);
			assert(result.equals("0 1\n3 2\n"));
		}
		{
			var result = spiralString(4);
			assert(result.equals("     \n  0 1\n4 3 2\n"));
		}
		{
			var result = spiralString(8);
			assert(result.equals("6 7 8\n5 0 1\n4 3 2\n"));
		}
	}
}
