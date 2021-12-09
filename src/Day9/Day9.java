package Day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {

    public static long count = 0;

    void part1(int[][] grid) {
        long sum = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                int num = grid[y][x];
                int yPlus = Integer.MAX_VALUE, yMinus = Integer.MAX_VALUE, xPlus = Integer.MAX_VALUE, xMinus = Integer.MAX_VALUE;
                if (y + 1 < grid.length) {
                    yPlus = grid[y + 1][x];
                }
                if (y - 1 >= 0) {
                    yMinus = grid[y - 1][x];
                }
                if (x + 1 < grid[y].length) {
                    xPlus = grid[y][x + 1];
                }
                if (x - 1 >= 0) {
                    xMinus = grid[y][x - 1];
                }
                if (num < xMinus && num < xPlus && num < yMinus && num < yPlus) {
                    sum += num + 1;
                }
            }
        }
        System.out.println(sum);
    }

    void part2(int[][] grid) {

        List<Long> basinSizes = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                count = 0;
                markGrid(grid, y, x);
                if (count != 0) {
                    basinSizes.add(count);
                }
            }
        }
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        System.out.println(basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2));
    }

    private void markGrid(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == -1 || grid[i][j] == 9) return;
        grid[i][j] = -1;
        count++;
        markGrid(grid, i + 1, j);
        markGrid(grid, i - 1, j);
        markGrid(grid, i, j + 1);
        markGrid(grid, i, j - 1);
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day9/input.txt")).collect(Collectors.toList());

        int[][] grid = new int[lines.size()][lines.get(0).split("").length];

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split("");
            for (int j = 0; j < line.length; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }
        Day9 day9 = new Day9();
        day9.part1(grid);
        day9.part2(grid);

    }

}

