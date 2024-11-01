package Lesson10;

public class Lesson10 {
    public static void main(String[] args) {
        // Создание массива сотрудников
        Employee[] employees = new Employee[5];

        // Заполнение массива данными
        employees[0] = new Employee("Ivanov Ivan", "Engineer", "ivanov@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Petrov Petr", "Manager", "petrov@mailbox.com", "892312313", 40000, 35);
        employees[2] = new Employee("Sidorov Sidor", "Developer", "sidorov@mailbox.com", "892312314", 50000, 28);
        employees[3] = new Employee("Kuznetsov Kuzya", "Designer", "kuznetsov@mailbox.com", "892312315", 35000, 32);
        employees[4] = new Employee("Smirnov Semyon", "Analyst", "smirnov@mailbox.com", "892312316", 45000, 29);

        // Вывод информации о каждом сотруднике
        for (Employee employee : employees) {
            employee.displayInfo();
        }

        // Создание парка и вывод информации об аттракционах
        Park park = new Park("Сказочный парк");
        park.createAttractions();
    }
}