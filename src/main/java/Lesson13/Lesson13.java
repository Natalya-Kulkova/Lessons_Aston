package Lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {
    public static void main(String[] args) {
        // Часть 1: Подсчет уникальных слов
        String[] words = {
                "apple", "banana", "orange", "apple", "banana", "kiwi", "orange", "grape", "kiwi", "apple",
                "peach", "banana", "grape", "kiwi", "melon", "peach", "apple", "banana", "grape", "kiwi"
        };
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWords(words);
        wordCounter.displayUniqueWords();
// Часть 2: Телефонный справочник
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "123-456");
        phoneBook.add("Иванов", "789-012");
        phoneBook.add("Петров", "345-678");
        phoneBook.add("Сидоров", "901-234");
        phoneBook.add("Сидоров", "567-890");
        System.out.println("Телефоны Иванова: " + phoneBook.get("Иванов"));
        System.out.println("Телефоны Сидорова: " + phoneBook.get("Сидоров"));
        System.out.println("Телефоны Петрова: " + phoneBook.get("Петров"));
    }
}