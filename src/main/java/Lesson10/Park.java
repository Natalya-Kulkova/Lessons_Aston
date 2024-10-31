package Lesson10;

public class Park {
    // Вложенный класс Attraction
    private class Attraction {
        private String name; // Название аттракциона
        private String workingHours; // Время работы
        private double price; // Стоимость

        // Конструктор вложенного класса
        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        // Метод для вывода информации об аттракционе
        public void displayInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " руб.");
            System.out.println();
        }
    }

    private Attraction[] attractions = new Attraction[10]; // Массив для хранения аттракционов
    private int count = 0; // Счетчик аттракционов

    // Метод для добавления аттракциона
    public void addAttraction(String name, String workingHours, double price) {
        if (count < attractions.length) {
            attractions[count++] = new Attraction(name, workingHours, price);
        } else {
            System.out.println("Достигнуто максимальное количество аттракционов.");
        }
    }

    // Метод для вывода информации об аттракционах
    public void displayAttractions() {
        System.out.println("Аттракционы в парке:");
        for (int i = 0; i < count; i++) {
            attractions[i].displayInfo();
        }
    }
}
