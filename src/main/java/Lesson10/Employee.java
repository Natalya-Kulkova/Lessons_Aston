package Lesson10;
import java.math.BigDecimal;

public class Employee {
    private String firstName; // Имя
    private String lastName;  // Фамилия
    private BigDecimal salary; // Зарплата
    private String email;     // Email
    private String phone;     // Телефон
    private int age;          // Возраст

    // Конструктор класса
    public Employee(String firstName, String lastName, BigDecimal salary, String email, String phone, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    // Геттеры и сеттеры
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    // Метод для вывода информации об объекте
    public void displayInfo() {
        System.out.println("Имя: " + firstName + " " + lastName);
        System.out.println("Зарплата: " + salary);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("Возраст: " + age);
        System.out.println();
    }
}
