package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day14 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/Day14/input.txt"));

        Map<String, String> rules = new HashMap<>();

        lines.stream().skip(2).forEach(l->{
            String[] entry = l.split(" -> ");
            rules.put(entry[0], entry[1]);
        });

        String polymer = lines.get(0);

        Map<String, Long> pairCount = new HashMap<>();

        for (int i = 0; i < polymer.length()-1; i++) {
            final Long currentValue = pairCount.getOrDefault(polymer.substring(i, i + 2), 0L);
            pairCount.put(polymer.substring(i, i + 2), currentValue + 1);
        }

        for(int iteration=0; iteration<40; iteration++) {
            Map<String, Long> newPairCount = new HashMap<>();
            for (String key : pairCount.keySet()) {
                newPairCount.merge(key.charAt(0) + rules.get(key), pairCount.get(key), Long::sum);
                newPairCount.merge(rules.get(key) + key.charAt(1), pairCount.get(key), Long::sum);
            }
            pairCount=newPairCount;
        }
        Map<Character, Long> charCount = new HashMap<>();
        for (String key : pairCount.keySet()) {
            charCount.merge(key.charAt(0), pairCount.get(key), Long::sum);
            charCount.merge(key.charAt(1), pairCount.get(key), Long::sum);
        }

        final List<Map.Entry<Character, Long>> sortedCharCount = new ArrayList<>(charCount.entrySet());
        sortedCharCount.sort(Map.Entry.comparingByValue());
        long min = sortedCharCount.get(0).getValue();
        long max = sortedCharCount.get(sortedCharCount.size() - 1).getValue();
        System.out.println((max - min)/2);
    }
}
