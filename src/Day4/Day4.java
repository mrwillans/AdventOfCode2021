package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    int part(boolean part1) throws IOException {
        List<String> lines = Files.lines(Path.of("src/Day4/input.txt")).collect(Collectors.toList());
        LinkedList<String> bingoGame = Arrays.stream(lines.get(0).split(",")).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<HashMap<Integer, String>> listOfBingoCards = new LinkedList<>();
        LinkedList<HashMap<String, Boolean>> listOfBingoCardsMarked = new LinkedList<>();
        for (int i = 2; i < lines.size() - 4; i += 6) {
            HashMap<Integer, String> bingoCardVals = new HashMap<>();
            HashMap<String, Boolean> bingoCardMarked = new HashMap<>();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    List<String> bingoLine = Arrays.stream(lines.get(i + j).split(" ")).filter(l -> !l.equals("")).collect(Collectors.toList());
                    bingoCardVals.put(Integer.parseInt(bingoLine.get(k)), k + "," + j);
                    bingoCardMarked.put(k + "," + j, false);
                }
            }
            listOfBingoCards.add(bingoCardVals);
            listOfBingoCardsMarked.add(bingoCardMarked);
        }
        Set<Integer> winners = new HashSet<>();
        int initialNumOfCards = listOfBingoCards.size();
        for (String num : bingoGame) {
            for (int i = 0; i < listOfBingoCards.size(); i++) {
                HashMap<Integer, String> card = listOfBingoCards.get(i);
                HashMap<String, Boolean> cardMarks = listOfBingoCardsMarked.get(i);
                if (card.containsKey(Integer.parseInt(num))) {
                    cardMarks.put(card.get(Integer.parseInt(num)), true);
                }
                if (isValid(cardMarks)) {
                    if (part1) {
                        int unmarkedSum = 0;
                        for (int number : card.keySet()) {
                            if (!cardMarks.get(card.get(number))) {
                                unmarkedSum += number;
                            }
                        }
                        return Integer.parseInt(num) * unmarkedSum;
                    } else {
                        winners.add(i);
                        if (winners.size() == initialNumOfCards) {
                            int unmarkedSum = 0;
                            for (int number : card.keySet()) {
                                if (!cardMarks.get(card.get(number))) {
                                    unmarkedSum += number;
                                }
                            }
                            return Integer.parseInt(num) * unmarkedSum;
                        }
                    }
                }
            }
        }
        return -1;
    }

    boolean isValid(HashMap<String, Boolean> card) {
        for (int i = 0; i < 5; i++) {
            if (card.get(i + "," + 0) && card.get(i + "," + 1) && card.get(i + "," + 2) && card.get(i + "," + 3) && card.get(i + "," + 4)) {
                return true;
            } else if (card.get(0 + "," + i) && card.get(1 + "," + i) && card.get(2 + "," + i) && card.get(3 + "," + i) && card.get(4 + "," + i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Day4 day4 = new Day4();
        System.out.println(day4.part(true));
        System.out.println(day4.part(false));
    }
}
