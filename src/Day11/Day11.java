package Day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {
    long flashes = 0;

    void part1() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day11/input2.txt")).collect(Collectors.toList());

        int[][] grid = new int[lines.size()][lines.get(0).split("").length];

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i).split("");
            for (int j = 0; j < line.length; j++) {
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int iter = 0; iter < 100; iter++) {
                for (int y = 0; y < grid.length; y++) {
                    for (int x = 0; x < grid[y].length; x++) {
                        int num = grid[y][x];
                        if (num == 10) {
                            flash(grid, y, x);
                        } else {
                            grid[y][x] = num + 1;
                        }
                    }
                }
        }
        System.out.println(flashes);
    }

    void flash(int[][] grid, int y, int x) {
        flashes++;
        grid[y][x] = 0;
        for (int dy = (y == 0 ? y : y - 1); dy < (y == (grid.length - 1) ? y + 1 : y + 2); dy++) {
            for (int dx = (x == 0 ? x : x - 1); dx < (x == (grid[y].length - 1) ? x + 1 : x + 2); dx++) {
                if (dx == x && dy == y) {
                    continue;
                }
                int num = grid[dy][dx];
                if (num == 10) {
                    //flash(grid, dy, dx);
                    continue;
                } else {
                    grid[dy][dx] = num + 1;
                }
            }
        }
    }

    boolean hasNines(int[][] grid) {
        for (int[] ints : grid) {
            for (int x = 0; x < grid[0].length; x++) {
                if (ints[x] == 9) return true;
            }
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        Day11 day11 = new Day11();
        day11.part1();
    }
}
