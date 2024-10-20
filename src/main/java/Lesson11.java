public class Lesson11 {
    /*Создать классы Собака и Кот с наследованием от класса Животное.
Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия. Результатом выполнения действия будет печать в консоль. (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.';
У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.)
Добавить подсчет созданных котов, собак и животных.
Расширить задачу, добавив для котов возможность кушать из миски, выполнив следующие пункты:
- Сделать так, чтобы в миске с едой не могло получится отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
- Каждому коту нужно добавить поле сытость (когда создаём кота, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
- Считаем, что если коту мало еды в миске, то он ее просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
- Создать массив котов и миску с едой, попросить всех котов покушать из этой миски и потом вывести информацию о сытости котов в консоль.
- Добавить метод, с помощью которого можно было бы добавит еду в миску.*/
    /*Применяя интерфейсы написать программу расчёта периметра и площади геометрических фигур: круг, прямоугольник, треугольник.
Задать для каждой фигуры цвет заливки и цвет границы.
Результат полученных характеристик [Периметр, площадь, цвет фона, цвет границ] по каждой фигуре вывести в консоль.
Попробуйте реализовать базовые методы, такие как расчёт периметра фигур, в качестве дефолтных методов в интерфейсе.*/
    public static void main(String[] args) {
        // Часть 1: Создание животных
        // Создание миски с едой
        Bowl bowl = new Bowl(20); // Миска с 20 единицами еды

        // Создание массива котов
        Cat[] cats = new Cat[3];
        cats[0] = new Cat("Мурка", bowl);
        cats[1] = new Cat("Барсик", bowl);
        cats[2] = new Cat("Снежок", bowl);

        // Коты пытаются покушать
        for (Cat cat : cats) {
            cat.eat();
        }

        // Вывод информации о сытости котов
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сытость: " + (cat.isFull() ? "Сыт" : "Голоден"));
        }

        // Создание собак
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Шарик");

        // Собака пробегает и плывет
        dog1.run(150);
        dog1.swim(5);
        dog2.run(600); // Пробежит только 500
        dog2.swim(15); // Проплывет только 10

        // Вывод количества созданных животных
        System.out.println("Создано животных: " + Animal.getAnimalCount());
        System.out.println("Создано котов: " + Cat.getCatCount());
        System.out.println("Создано собак: " + Dog.getDogCount());

        // Часть 2: Создание фигур
        // Создание фигур
        Shape circle = new Circle(5, "Красный", "Черный");
        Shape rectangle = new Rectangle(4, 6, "Синий", "Зеленый");
        Shape triangle = new Triangle(3, 4, 5, "Желтый", "Фиолетовый");

        // Вывод характеристик фигур
        printShapeInfo(circle);
        printShapeInfo(rectangle);
        printShapeInfo(triangle);
    }

    // Метод для вывода информации о фигуре
    public static void printShapeInfo(Shape shape) {
        System.out.println("Фигура: " + shape.getClass().getSimpleName());
        System.out.println("Периметр: " + shape.getPerimeter());
        System.out.println("Площадь: " + shape.getArea());
        System.out.println("Цвет заливки: " + shape.getFillColor());
        System.out.println("Цвет границы: " + shape.getBorderColor());
        System.out.println();
    }
}

//Задание 1
// Интерфейс Shape
interface Shape {
    double getArea(); // Метод для расчета площади
    double getPerimeter(); // Метод для расчета периметра

    // Дефолтный метод для получения цвета заливки
    default String getFillColor() {
        return "Нет цвета заливки";
    }

    // Дефолтный метод для получения цвета границы
    default String getBorderColor() {
        return "Нет цвета границы";
    }
}

// Класс Animal
class Animal {
    private static int animalCount = 0;

    public Animal() {
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public void run(int distance) {
        System.out.println("Животное пробежало " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println("Животное проплыло " + distance + " м.");
    }
}

// Класс Dog
class Dog extends Animal {
    private static int dogCount = 0;
    private String name;

    public Dog(String name) {
        super();
        this.name = name;
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать больше 500 м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть больше 10 м.");
        }
    }
}

// Класс Cat
class Cat extends Animal {
    private static int catCount = 0;
    private String name;
    private boolean isFull;
    private Bowl bowl;

    public Cat(String name, Bowl bowl) {
        super();
        this.name = name;
        this.bowl = bowl;
        this.isFull = false;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public String getName() {
        return name;
    }

    public boolean isFull() {
        return isFull;
    }

    public void eat() {
        if (bowl.getFoodAmount() > 0) {
            isFull = bowl.eat(10); // Кот ест 10 единиц еды
            if (isFull) {
                System.out.println(name + " покушал и теперь сыт.");
            } else {
                System.out.println(name + " не хватает еды в миске.");
            }
        } else {
            System.out.println(name + " не может покушать, в миске нет еды.");
        }
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать больше 200 м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }
}

// Класс Bowl (миска с едой)
class Bowl {
    private int foodAmount;

    public Bowl(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public boolean eat(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
            return true; // Кот сыт
        } else {
            return false; // Кот не сыт
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Добавлено " + amount + " еды в миску. Теперь в миске " + foodAmount + " еды.");
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды.");
        }
    }
}

//Задание 2
// Класс Circle
class Circle implements Shape {
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

// Класс Rectangle
class Rectangle implements Shape {
    private double width;
    private double height;
    private String fillColor;
    private String borderColor;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}

// Класс Triangle
class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    private String fillColor;
    private String borderColor;

    public Triangle(double sideA, double sideB, double sideC, String fillColor, String borderColor) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2; // Полупериметр
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC)); // Формула Герона
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}