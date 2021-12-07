package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

    void part1() throws IOException {
        List<String[]> lines = Files.lines(Path.of("src/Day7/input.txt")).map(l -> l.split(",")).collect(Collectors.toList());
        List<Integer> nums = Arrays.stream(lines.get(0)).map(Integer::parseInt).collect(Collectors.toList());

        int avg = nums.stream().mapToInt(l -> l).sorted().toArray()[nums.size() / 2];

        int steps = nums.stream().mapToInt(n -> Math.abs(n - avg)).sum();

        System.out.println(steps);
    }

    void part2() throws IOException {
        List<String[]> lines = Files.lines(Path.of("src/Day7/input.txt")).map(l -> l.split(",")).collect(Collectors.toList());
        List<Integer> nums = Arrays.stream(lines.get(0)).map(Integer::parseInt).collect(Collectors.toList());

        double avg = nums.stream().mapToInt(l -> l).average().getAsDouble();
        int rounded = (int) Math.floor(avg);

        long steps = nums.stream().mapToLong(n -> (long) ((Math.pow(Math.abs(n - rounded), 2) + (Math.abs(n - rounded))) / 2)).sum();

        System.out.println(steps);
    }

    public static void main(String[] args) throws IOException {
        Day7 day7 = new Day7();
        day7.part1();
        day7.part2();
    }


}
