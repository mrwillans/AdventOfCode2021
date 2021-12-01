package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    long part1(List<Integer> lines) {
        long count = 0;
        for (int i = 0; i < lines.size() - 1; i++) {
            if (lines.get(i + 1) > lines.get(i)) {
                count++;
            }
        }
        return count;
    }

    long part2(List<Integer> lines) {
        long count = 0;
        for (int i = 0; i < lines.size() - 3; i++) {
            int slidingWindow1 = lines.get(i) + lines.get(i + 1) + lines.get(i + 2);
            int slidingWindow2 = lines.get(i + 1) + lines.get(i + 2) + lines.get(i + 3);
            if (slidingWindow2 > slidingWindow1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        List<Integer> lines = Files.lines(Path.of("src/Day1/input.txt")).map(Integer::parseInt).collect(Collectors.toList());
        Day1 day1 = new Day1();
        System.out.println(day1.part1(lines));
        System.out.println(day1.part2(lines));
    }
}
