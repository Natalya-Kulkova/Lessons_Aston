package Lesson10;

import java.math.BigDecimal;

public class Lesson10 {
    public static void main(String[] args) {
        // Создание миски с едой
        Bowl bowl = new Bowl(20); // Миска с 20 единицами еды

        // Создание массива сотрудников
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иван", "Иванов", new BigDecimal("1500.00"), "ivanov@example.com", "+375291234567", 30);
        employees[1] = new Employee("Петр", "Петров", new BigDecimal("2000.00"), "petrov@example.com", "+375291234568", 28);
        employees[2] = new Employee("Сидор", "Сидоров", new BigDecimal("1800.00"), "sidorov@example.com", "+375291234569", 35);
        employees[3] = new Employee("Алексей", "Кузнецов", new BigDecimal("1700.00"), "kuznetsov@example.com", "+375291234570", 32);
        employees[4] = new Employee("Анна", "Смирнова", new BigDecimal("1600.00"), "smirnova@example.com", "+375291234571", 29);

        // Вывод информации о каждом сотруднике
        for (Employee employee : employees) {
            employee.displayInfo();
        }

        // Создание объекта класса Park
        Park park = new Park();
        park.addAttraction("Американские горки", "10:00 - 22:00", 500);
        park.addAttraction("Колесо обозрения", "10:00 - 20:00", 300);
        park.addAttraction("Детская площадка", "09:00 - 19:00", 200);

        // Вывод информации об аттракционах
        park.displayAttractions();
    }
}