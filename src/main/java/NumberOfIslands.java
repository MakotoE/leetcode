package main.java;

public class NumberOfIslands {
	static final char WATER = '0';
	static final char LAND = '1';

	record Coordinate(int row, int col) {}

	public static int numIslands(char[][] grid) {
		if (grid.length == 0) {
			return 0;
		}

		var visited = new boolean[grid.length][grid[0].length];
		var currentCoordinate = new Coordinate(0, 0);
		var count = 0;

		while (currentCoordinate.col < grid[0].length) {
			if (
				!visited[currentCoordinate.row][currentCoordinate.col]
				&& grid[currentCoordinate.row][currentCoordinate.col] == LAND
			) {
				count++;
			}

			markVisited(grid, currentCoordinate, visited);

			if (currentCoordinate.row + 1 == grid.length) {
				currentCoordinate = new Coordinate(0, currentCoordinate.col + 1);
			} else {
				currentCoordinate = new Coordinate(currentCoordinate.row + 1, currentCoordinate.col);
			}
		}

		return count;
	}

	static void markVisited(char[][] grid, Coordinate coordinate, boolean[][] visited) {
		if (visited[coordinate.row][coordinate.col]) {
			return;
		}

		visited[coordinate.row][coordinate.col] = true;

		if (grid[coordinate.row][coordinate.col] == WATER) {
			return;
		}

		if (coordinate.row - 1 >= 0) {
			markVisited(grid, new Coordinate(coordinate.row - 1, coordinate.col), visited);
		}

		if (coordinate.row + 1 < grid.length) {
			markVisited(grid, new Coordinate(coordinate.row + 1, coordinate.col), visited);
		}

		if (coordinate.col - 1 >= 0) {
			markVisited(grid, new Coordinate(coordinate.row, coordinate.col - 1), visited);
		}

		if (coordinate.col + 1 < grid[0].length) {
			markVisited(grid, new Coordinate(coordinate.row, coordinate.col + 1), visited);
		}
	}

	record TestCase (
		char[][] grid,
		int expected
	) {}

	public static void main(String[] args) {
		var tests = new TestCase[] {
			new TestCase(
				new char[][]{},
				0
			),
			new TestCase(
				new char[][]{
					{WATER}
				},
				0
			),
			new TestCase(
				new char[][]{
					{LAND}
				},
				1
			),
			new TestCase(
				new char[][]{
					{'1','1','1','1','0'},
					{'1','1','0','1','0'},
					{'1','1','0','0','0'},
					{'0','0','0','0','0'}
				},
				1
			),
			new TestCase(
				new char[][]{
					{'1','1','0','0','0'},
					{'1','1','0','0','0'},
					{'0','0','1','0','0'},
					{'0','0','0','1','1'}
				},
				3
			),
		};

		for (var i = 0; i < tests.length; i++) {
			var result = numIslands(tests[i].grid);
			assert(result == tests[i].expected) : i;
		}
	}
}
