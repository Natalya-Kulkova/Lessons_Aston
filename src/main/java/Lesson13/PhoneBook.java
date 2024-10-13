package Lesson13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> phoneBookMap;

    public PhoneBook() {
        phoneBookMap = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        phoneBookMap.putIfAbsent(lastName, new ArrayList<>());
        phoneBookMap.get(lastName).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phoneBookMap.getOrDefault(lastName, new ArrayList<>());
    }
}