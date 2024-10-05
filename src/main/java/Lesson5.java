public class Lesson5 {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println("isSumBetween10And20(5, 7) = " + isSumBetween10And20(5, 7));
        System.out.println("isSumBetween10And20(10, 15) = " + isSumBetween10And20(10, 15));
        System.out.println("isSumBetween10And20(5, 5) = " + isSumBetween10And20(5, 5));
        checkPositiveOrNegative(6);
        checkPositiveOrNegative(-3);
        checkPositiveOrNegative(0);
        System.out.println("isNegative(-5) = " + isNegative(-5));
        System.out.println("isNegative(10) = " + isNegative(10));
        System.out.println("isNegative(0) = " + isNegative(0));
        printStringNTimes("Привет", 3);
        printStringNTimes("Java", 5);
        System.out.println("2000 - високосный год: " + isLeapYear(2000));
        System.out.println("2100 - високосный год: " + isLeapYear(2100));
        System.out.println("2024 - високосный год: " + isLeapYear(2024));
        System.out.println("2023 - високосный год: " + isLeapYear(2023));
        invertArray();
        createAndFillArray();
        processArray();
        fillDiagonal();
        int[] newArray = createArray(5, 7);
        System.out.println("Созданный массив:");
        for (int num : newArray) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    //Создайте метод printThreeWords(), который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    /*Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
    и инициализируйте их любыми значениями, которыми захотите.
    Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0,
    то вывести в консоль сообщение "Сумма положительная", в противном случае - "Сумма отрицательная".*/
    public static void checkSumSign() {
        int a = 5;
        int b = -3;

        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    /*Создайте метод printColor(),
        в теле которого задайте int переменную value и инициализируйте её любым значением.
        Если value меньше 0 (0 включительно), то в консоль метод должен вывести сообщение "Красный",
        если лежит в пределах от 0 (0 исключительно) до 100 (100 включительно), то "Желтый",
        если больше 100 (100 исключительно) - "Зелёный".*/
    public static void printColor() {
        int value = 56;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зелёный");
        }
    }

    /*Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
        и инициализируйте их любыми значениями, которыми захотите.
        Если a больше или равно b, то необходимо вывести в консоль сообщение "a >= b",
        в противном случае "a < b"*/
    public static void compareNumbers() {
        int a = 10;
        int b = 5;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    /*Напишите метод, принимающий на вход два целых числа и проверяющий,
    что их сумма лежит в пределах от 10 до 20 (включительно), если да - вернуть true, в противном случае - false.*/
    public static boolean isSumBetween10And20(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    /*Напишите метод, которому в качестве параметра передается целое число,
    метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    Замечание: ноль считаем положительным числом*/
    public static void checkPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println("Число " + number + " положительное");
        } else {
            System.out.println("Число " + number + " отрицательное");
        }
    }

    /*Напишите метод, которому в качестве параметра передается целое число.
     Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
      Замечание: ноль считаем положительным числом.*/
    public static boolean isNegative(int number) {
        return number < 0;
    }

    /*Напишите метод, которому в качестве аргументов передается строка и число,
     метод должен отпечатать в консоль указанную строку, указанное количество раз;*/
    public static void printStringNTimes(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(str);
        }
    }

    /*Напишите метод, который определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й - високосный.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /*Задать целочисленный массив, состоящий из элементов 0 и 1.
     Например: [1, 1, 0, 0, 1, 0, 1, 1, 0, 0].
     С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    static void invertArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("Исходный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        }

        System.out.println("Инвертированный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /*Задать пустой целочисленный массив длинной 100.
    С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;*/
    public static void createAndFillArray() {
        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        System.out.println("Массив, заполненный числами от 1 до 100:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }

    //Задать массив [1, 5, 3, 2, 11, 4, 8, 9, 1] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void processArray() {
        int[] arr = {1, 5, 3, 2, 11, 4, 8, 9, 1};

        System.out.println("Исходный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        System.out.println("Обработанный массив:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /*Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    и с помощью цикла(-ов) заполнить его диагональные элементы единицами
    (можно только одну из диагоналей, если обе сложно).
    Определить элементы одной из диагоналей можно по следующему принципу:
    индексы таких элементов равны, то есть [0][0], [1][1], [2][2], ..., [n][n];
     */
    public static void fillDiagonal() {
        int size = 5;
        int[][] arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            arr[i][i] = 1;
            arr[i][size - 1 - i] = 1;
        }

        System.out.println("Массив с заполненными диагоналями:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*Написать метод, принимающий на вход два аргумента: len и initialValue,
    и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue.
     */
    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
        }
        return arr;
    }
}
