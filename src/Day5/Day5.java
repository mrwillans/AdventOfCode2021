package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day5 {

    void part1() throws IOException {
        Stream<String> lines = Files.lines(Path.of("src/Day5/input.txt"));

        int[][] grid = new int[1000][1000];

        lines.forEach(l -> {
            String[] coord = l.split(" -> ");
            String[] beginning = coord[0].split(",");
            String[] end = coord[1].split(",");
            if (beginning[0].equals(end[0]) || beginning[1].equals(end[1])) {
                int x1 = Math.min(Integer.parseInt(beginning[0]), Integer.parseInt(end[0]));
                int x2 = Math.max(Integer.parseInt(beginning[0]), Integer.parseInt(end[0]));
                int y1 = Math.min(Integer.parseInt(beginning[1]), Integer.parseInt(end[1]));
                int y2 = Math.max(Integer.parseInt(beginning[1]), Integer.parseInt(end[1]));
                for (int y = y1; y <= y2; y++) {
                    for (int x = x1; x <= x2; x++) {
                        int currentVal = grid[y][x];
                        currentVal++;
                        grid[y][x] = currentVal;
                    }
                }
            }
        });
        long counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    void part2() throws IOException {
        Stream<String> lines = Files.lines(Path.of("src/Day5/input.txt"));

        int[][] grid = new int[1000][1000];

        lines.forEach(l -> {
            String[] coord = l.split(" -> ");
            String[] beginning = coord[0].split(",");
            String[] end = coord[1].split(",");
            if (beginning[0].equals(end[0]) || beginning[1].equals(end[1])) {
                int x1 = Math.min(Integer.parseInt(beginning[0]), Integer.parseInt(end[0]));
                int x2 = Math.max(Integer.parseInt(beginning[0]), Integer.parseInt(end[0]));
                int y1 = Math.min(Integer.parseInt(beginning[1]), Integer.parseInt(end[1]));
                int y2 = Math.max(Integer.parseInt(beginning[1]), Integer.parseInt(end[1]));
                for (int y = y1; y <= y2; y++) {
                    for (int x = x1; x <= x2; x++) {
                        int currentVal = grid[y][x];
                        currentVal++;
                        grid[y][x] = currentVal;
                    }
                }
            } else{
                int x1 = Integer.parseInt(beginning[0]);
                int x2 = Integer.parseInt(end[0]);
                int y1 = Integer.parseInt(beginning[1]);
                int y2 = Integer.parseInt(end[1]);
                if(x1<x2 && y1<y2){
                    for (int y = y1, x=x1; y <= y2 && x<=x2; y++, x++) {
                            int currentVal = grid[y][x];
                            currentVal++;
                            grid[y][x] = currentVal;
                    }
                }else if(x1>x2 && y1<y2){
                    for (int y = y1, x=x1; y <= y2 && x<=x1; y++, x--) {
                            int currentVal = grid[y][x];
                            currentVal++;
                            grid[y][x] = currentVal;
                    }
                }else if(x1<x2 && y1>y2){
                    for (int y = y1, x=x1; y >= y2 && x<=x2; y--, x++) {
                            int currentVal = grid[y][x];
                            currentVal++;
                            grid[y][x] = currentVal;
                    }
                }else if(x1>x2 && y1>y2){
                    for (int y = y1, x=x1; y >= y2 && x>=x2; y--, x--) {
                            int currentVal = grid[y][x];
                            currentVal++;
                            grid[y][x] = currentVal;
                    }
                }
            }
        });
        long counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }



    public static void main(String[] args) throws IOException {
        Day5 day5 = new Day5();
        day5.part1();
        day5.part2();
    }
}
