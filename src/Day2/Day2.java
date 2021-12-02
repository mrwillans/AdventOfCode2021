package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Day2 {

    void part1() throws IOException {
        AtomicLong x= new AtomicLong();
        AtomicLong y= new AtomicLong();
        Files.lines(Path.of("src/Day2/input.txt")).forEach(l -> {
            String[] instructions = l.split(" ");
            switch(instructions[0]){
                case "forward":
                    x.addAndGet(Long.parseLong(instructions[1]));
                    break;
                case "down":
                    y.addAndGet(Long.parseLong(instructions[1]));
                    break;
                case "up":
                    y.addAndGet(Long.parseLong(instructions[1])*-1);
                    break;
            }
        });
        System.out.println(x.get()*y.get());
    }

    void part2() throws IOException {
        AtomicLong x= new AtomicLong();
        AtomicLong y= new AtomicLong();
        AtomicLong aim = new AtomicLong();
        Files.lines(Path.of("src/Day2/input.txt")).forEach(l -> {
            String[] instructions = l.split(" ");
            switch(instructions[0]){
                case "forward":
                    x.addAndGet(Long.parseLong(instructions[1]));
                    y.addAndGet(Long.parseLong(instructions[1])*aim.get());
                    break;
                case "down":
                    aim.addAndGet(Long.parseLong(instructions[1]));
                    break;
                case "up":
                    aim.addAndGet(Long.parseLong(instructions[1])*-1);
                    break;
            }
        });
        System.out.println(x.get()*y.get());
    }

    public static void main(String[] args) throws IOException {
        Day2 day2 = new Day2();
        day2.part1();
        day2.part2();

    }
}
