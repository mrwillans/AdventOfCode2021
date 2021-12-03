package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    void part1() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day3/input.txt")).collect(Collectors.toList());
        Map<Integer, Long> ones = new HashMap<>();
        Map<Integer, Long> zeros = new HashMap<>();

        count(lines, ones, zeros);

        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < ones.size(); i++) {
            String num = ones.get(i) > zeros.get(i) ? "1" : "0";
            binary.append(num);
        }
        long gamma = Long.parseLong(String.valueOf(binary), 2);
        long ep = Long.parseLong(String.valueOf(binary).replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1"), 2);

        System.out.println(gamma * ep);
    }

    void part2() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day3/input.txt")).collect(Collectors.toList());
        long oxy = 0L;
        long carbon = 0L;
        for (int i = 0; i < 12; i++) {
            Map<Integer, Long> ones = new HashMap<>();
            Map<Integer, Long> zeros = new HashMap<>();
            if(lines.size() ==1){
                oxy = Long.parseLong(lines.get(0),2);
                break;
            }
            count(lines, ones, zeros);
            String winner = ones.get(i) >= zeros.get(i) ? "1" : "0";
            int finalI = i;
            lines = lines.stream().filter(l -> String.valueOf(l.toCharArray()[finalI]).equals(winner)).collect(Collectors.toList());
        }
        lines = Files.lines(Path.of("src/Day3/input.txt")).collect(Collectors.toList());
        for (int i = 0; i < 12; i++) {
            Map<Integer, Long> ones = new HashMap<>();
            Map<Integer, Long> zeros = new HashMap<>();
            if(lines.size() ==1){
                carbon = Long.parseLong(lines.get(0),2);
                break;
            }
            count(lines, ones, zeros);
            String winner = ones.get(i) >= zeros.get(i) ? "0" : "1";
            int finalI = i;
            lines = lines.stream().filter(l -> String.valueOf(l.toCharArray()[finalI]).equals(winner)).collect(Collectors.toList());
        }
        System.out.println(oxy*carbon);
    }

    void count(List<String> lines, Map<Integer, Long> ones, Map<Integer, Long> zeros) {
        lines.forEach(l -> {
            for (int i = 0; i < l.length(); i++) {
                char c = l.toCharArray()[i];
                if (Character.getNumericValue(c) == 1) {
                    ones.put(i, (ones.getOrDefault(i, 0L) + 1));
                } else if (Character.getNumericValue(c) == 0) {
                    zeros.put(i, (zeros.getOrDefault(i, 0L) + 1));
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Day3 day3 = new Day3();
        day3.part1();
        day3.part2();
    }
}
//youre pretty