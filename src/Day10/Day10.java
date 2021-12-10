package Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Day10 {

    AtomicLong sum = new AtomicLong();
    AtomicLong sum2 = new AtomicLong();
    List<AtomicLong> sumList = new ArrayList<>();

    void checkBracket(String line, Stack<String> stack, boolean part2) {
        for (String c : line.split("")) {
            if (c.equals("(") || c.equals("[") || c.equals("{") || c.equals("<")) {
                stack.push(c);
            } else if (c.equals(")")) {
                if (!stack.peek().equals("(")) {
                    sum.addAndGet(3);
                    return;
                } else {
                    stack.pop();
                }
            } else if (c.equals("]")) {
                if (!stack.peek().equals("[")) {
                    sum.addAndGet(57);
                    return;
                } else {
                    stack.pop();
                }
            } else if (c.equals("}")) {
                if (!stack.peek().equals("{")) {
                    sum.addAndGet(1197);
                    return;
                } else {
                    stack.pop();
                }
            } else if (c.equals(">")) {
                if (!stack.peek().equals("<")) {
                    sum.addAndGet(25137);
                    return;
                } else {
                    stack.pop();
                }
            }
        }

        if (part2) {
            while (!stack.isEmpty()) {
                String bracket = stack.pop();
                switch (bracket) {
                    case "(":
                        sum2.set((sum2.get() * 5) + 1);
                        break;
                    case "[":
                        sum2.set((sum2.get() * 5) + 2);
                        break;
                    case "{":
                        sum2.set((sum2.get() * 5) + 3);
                        break;
                    case "<":
                        sum2.set((sum2.get() * 5) + 4);
                        break;
                }
            }
            sumList.add(sum2);
        }
    }

    void part1() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day10/input.txt")).collect(Collectors.toList());

        lines.forEach(l -> {
            Stack<String> stack = new Stack<>();
            checkBracket(l, stack, false);
        });
        System.out.println(sum);
    }

    void part2() throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day10/input.txt")).collect(Collectors.toList());

        lines.forEach(l -> {
            sum2 = new AtomicLong();
            Stack<String> stack = new Stack<>();
            checkBracket(l, stack, true);
        });
        sumList.sort(Comparator.comparing(AtomicLong::get));
        System.out.println(sumList.get(sumList.size() / 2));
    }

    public static void main(String[] args) throws IOException {
        Day10 day10 = new Day10();
        day10.part1();
        day10.part2();
    }
}
