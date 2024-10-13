package Lesson13;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    private Map<String, Integer> wordCountMap;

    public WordCounter() {
        wordCountMap = new HashMap<>();
    }

    public void countWords(String[] words) {
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }
    }

    public void displayUniqueWords() {
        System.out.println("Уникальные слова и их количество:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
