package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Day8 {

    void part1() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day8/input.txt")).map(l -> l.split(" \\| ")[1]).collect(Collectors.toList());

        long count = lines.stream().map(l -> l.split(" ")).map(nums -> Arrays.stream(nums).filter(n -> n.length() == 2 || n.length() == 3 || n.length() == 4 || n.length() == 7).collect(Collectors.toList())).mapToLong(List::size).sum();
        System.out.println(count);
    }

    void part2() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day8/input.txt")).collect(Collectors.toList());
        AtomicLong sum = new AtomicLong();
        lines.forEach(l -> {
            String[] leftSide = l.split(" \\| ")[0].split(" ");
            String[] rightSide = l.split(" \\| ")[1].split(" ");

            HashMap<Integer, String> patternMap = new HashMap<>();
            Arrays.stream(leftSide).sorted(Comparator.comparingInt(String::length)).forEach(n -> {
                if (n.length() == 2) {
                    patternMap.put(1, n);
                }
                if (n.length() == 3) {
                    patternMap.put(7, n);
                }
                if (n.length() == 4) {
                    patternMap.put(4, n);
                }
                if (n.length() == 7) {
                    patternMap.put(8, n);
                }
                if (n.length() == 5) {
                    String one = patternMap.get(1);
                    String four = patternMap.get(4);
                    if (commonChars(n, one) == 2) {
                        patternMap.put(3, n);
                    } else if (commonChars(n, four) == 2) {
                        patternMap.put(2, n);
                    } else {
                        patternMap.put(5, n);
                    }
                }
                if (n.length() == 6) {
                    String one = patternMap.get(1);
                    String four = patternMap.get(4);
                    if (commonChars(n, one) == 1) {
                        patternMap.put(6, n);
                    } else if (commonChars(n, four) == 4) {
                        patternMap.put(9, n);
                    } else {
                        patternMap.put(0, n);
                    }
                }
            });
            StringBuilder number = new StringBuilder();
            Arrays.stream(rightSide).forEach(r -> {
                String sorted = Arrays.stream(r.split("")).sorted().collect(Collectors.joining());
                for (int i = 0; i < 10; i++) {
                    String fromMap = Arrays.stream(patternMap.get(i).split("")).sorted().collect(Collectors.joining());
                    if (sorted.equals(fromMap)) {
                        number.append(i);
                    }
                }
            });
            sum.addAndGet(Long.parseLong(String.valueOf(number)));
        });
        System.out.println(sum.get());
    }

    public int commonChars(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        for (Character c : s1.toCharArray()) {
            if (s2.indexOf(c) >= 0) {
                set.add(c);
            }
        }
        return set.size();
    }

    public static void main(String[] args) throws IOException {
        Day8 day8 = new Day8();
        day8.part1();
        day8.part2();
    }

}
