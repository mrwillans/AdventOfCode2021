package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day6/input.txt")).collect(Collectors.toList());
        List<Integer> fish = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));

        long[] fishCount = new long[9];

        fish.forEach(f->fishCount[f]++);

        for(int i=0; i<256; i++){
            long toBeReborn = fishCount[0];
            for(int j=1; j<9; j++){
                fishCount[j-1] = fishCount[j];
            }
            fishCount[6]+=toBeReborn;
            fishCount[8]=toBeReborn;
        }

        System.out.println(Arrays.stream(fishCount).sum());
    }
}
