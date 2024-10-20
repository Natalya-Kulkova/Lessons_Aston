package Lesson10;

public class Lesson10 {
    public static void main(String[] args) {
        // 1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
        // Конструктор класса должен заполнять эти поля при создании объекта.
        // Внутри класса "Сотрудник" написать метод, который выводит информацию об объекте в консоль.

        // Создание массива из 5 сотрудников
        Employee[] employees = new Employee[5];

        // Заполнение массива данными сотрудников
        employees[0] = new Employee("Иванов Иван Иванович", "Менеджер", "ivanov@example.com", "+375291234567", 1500.00, 30);
        employees[1] = new Employee("Петров Петр Петрович", "Разработчик", "petrov@example.com", "+375291234568", 2000.00, 28);
        employees[2] = new Employee("Сидоров Сидор Сидорович", "Дизайнер", "sidorov@example.com", "+375291234569", 1800.00, 35);
        employees[3] = new Employee("Кузнецов Алексей Алексеевич", "Аналитик", "kuznetsov@example.com", "+375291234570", 1700.00, 32);
        employees[4] = new Employee("Смирнова Анна Сергеевна", "HR", "smirnova@example.com", "+375291234571", 1600.00, 29);

        // Вывод информации о каждом сотруднике
        for (Employee employee : employees) {
            employee.displayInfo();
        }

        // 3. Создать класс Park с внутренним классом, с помощью объектов которого можно хранить информацию об аттракционах,
        // времени их работы и стоимости.

        // Создание объекта класса Park
        Park park = new Park();
        park.addAttraction("Американские горки", "10:00 - 22:00", 500);
        park.addAttraction("Колесо обозрения", "10:00 - 20:00", 300);
        park.addAttraction("Детская площадка", "09:00 - 19:00", 200);

        // Вывод информации об аттракционах
        park.displayAttractions();
    }

    // Вложенный класс Employee
    public static class Employee {
        // Поля класса
        private String fullName; // ФИО
        private String position;  // Должность
        private String email;     // Email
        private String phone;     // Телефон
        private double salary;    // Зарплата
        private int age;          // Возраст

        // Конструктор класса
        public Employee(String fullName, String position, String email, String phone, double salary, int age) {
            this.fullName = fullName;
            this.position = position;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
            this.age = age;
        }

        // Метод для вывода информации об объекте
        public void displayInfo() {
            System.out.println("ФИО: " + fullName);
            System.out.println("Должность: " + position);
            System.out.println("Email: " + email);
            System.out.println("Телефон: " + phone);
            System.out.println("Зарплата: " + salary);
            System.out.println("Возраст: " + age);
            System.out.println();
        }

        // Вложенный класс для представления адреса сотрудника
        public class Address {
            private String city; // Город
            private String street; // Улица
            private String zipCode; // Почтовый индекс

            // Конструктор вложенного класса
            public Address(String city, String street, String zipCode) {
                this.city = city;
                this.street = street;
                this.zipCode = zipCode;
            }

            // Метод для вывода информации об адресе
            public void displayAddress() {
                System.out.println("Город: " + city);
                System.out.println("Улица: " + street);
                System.out.println("Почтовый индекс: " + zipCode);
            }
        }
    }
}
