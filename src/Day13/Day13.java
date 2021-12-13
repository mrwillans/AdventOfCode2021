package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/Day13/input.txt"));
        int maxX = lines.stream().filter(l -> !l.startsWith("f")).filter(l -> !l.isEmpty()).mapToInt(l -> Integer.parseInt(l.split(",")[0])).max().getAsInt();
        int maxY = lines.stream().filter(l -> !l.startsWith("f")).filter(l -> !l.isEmpty()).mapToInt(l -> Integer.parseInt(l.split(",")[1])).max().getAsInt();
        int[][] grid = new int[maxY + 1][maxX + 1];

        for (String line : lines) {
            if (line.isBlank()) {
                break;
            } else {
                String[] nums = line.split(",");
                grid[Integer.parseInt(nums[1])][Integer.parseInt(nums[0])] = 1;
            }
        }
        List<String> instructions = lines.stream().filter(l -> l.startsWith("f")).collect(Collectors.toList());
        for (String instruction : instructions) {
            if (instruction.contains("x=")) {
                int line = Integer.parseInt(instruction.split("=")[1]);

                for (int i = 0; i < grid.length; i++) {
                    for (int j = line + 1; j < grid[i].length; j++) {
                        if (grid[i][j] == 1) {
                            grid[i][(j - ((j - line) * 2))] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
//                for (int i = 0; i < grid.length; i++) {
//                    for (int j = 0; j < grid[i].length; j++) {
//                        if (grid[i][j] == 1) {
//                            count++;
//                        }
//                    }
//                }
            } else if (instruction.contains("y=")) {
                int line = Integer.parseInt(instruction.split("=")[1]);

                for (int i = line + 1; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (grid[i][j] == 1) {
                            grid[(i - ((i - line) * 2))][j] = grid[i][j];
                            grid[i][j] = 0;
                        }
                    }
                }
                int[][] newGrid = new int[line+1][maxX + 1];
                for(int i=0; i<line+1; i++){
                    newGrid[i]=grid[i];
                }
                grid=newGrid;
            }
        }

        //System.out.println(Arrays.deepToString(grid));
        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
}
