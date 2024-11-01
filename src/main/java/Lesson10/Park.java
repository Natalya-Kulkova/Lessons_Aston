package Lesson10;

public class Park {
    private String name; // Название парка

    // Внутренний класс Attraction
    public class Attraction {
        private String name; // Название аттракциона
        private String workingHours; // Время работы
        private double price; // Стоимость

        // Конструктор
        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        // Метод для вывода информации об аттракционе
        public void displayInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price);
            System.out.println();
        }
    }

    // Конструктор парка
    public Park(String name) {
        this.name = name;
    }

    // Метод для создания и вывода информации об аттракционах
    public void createAttractions() {
        Attraction[] attractions = new Attraction[3];
        attractions[0] = new Attraction("Американские горки", "10:00 - 22:00", 500);
        attractions[1] = new Attraction("Колесо обозрения", "09:00 - 21:00", 300);
        attractions[2] = new Attraction("Поездка на поезде", "10:00 - 20:00", 200);

        System.out.println("Аттракционы в парке " + name + ":");
        for (Attraction attraction : attractions) {
            attraction.displayInfo();
        }
    }
}