import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        System.out.println("На скольких человек необходимо разделить счёт?");
        Scanner scanner = new Scanner(System.in);
        int clientCount;
        while (true) {
            if (scanner.hasNextInt()) {
                clientCount = scanner.nextInt();
                if (clientCount > 1) {
                    scanner.nextLine();
                    Calculator calculator = new Calculator();
                    calculator.start(scanner, clientCount);
                    break;
                } else System.out.println("Введите корректное значение");
            } else {
                System.out.println("Введите корректное значение");
                scanner.next();
            }
        }

    }
}

class Calculator {
    ArrayList<Product> productList = new ArrayList<>();
    Double sum = 0.00;

    public void start(Scanner scanner, int clientCount) {
        System.out.println("Введите названия товаров и их стоимость в формате '[Товар] [Стоимость]'\n" +
                "Стоимость товара необходимо ввести в формате [рубли,копейки]\n\n" +
                "После добавление всех товаров введите команду 'Завершить'");
        while (true) {
            if (scanner.hasNextLine()) {
                String inputString = scanner.next();
                if (inputString.equalsIgnoreCase("завершить")) {
                    System.out.println("\nДобавление товаров завершено\n");
                    break;
                } else if (scanner.hasNextDouble()) {
                    double inputDouble = scanner.nextDouble();
                    productList.add(new Product(inputString, inputDouble));
                    System.out.println("Товар успешно добавлен, при необходимости введите следующий товар или завершите работу калькулятора\n");
                    sum += inputDouble;
                } else {
                    System.out.println("Некорректный ввод, повторите еще раз");
                    scanner.next();
                }
            }
        }
        System.out.println("Добавленные товары:");
        for (Product product : productList) {
            System.out.println(product.name);
        }
        String rublesType;
        if (Math.floor(sum / clientCount) == 1.0) {
            rublesType = "рубль";
        } else if (Math.floor(sum / clientCount) >= 5.0) {
            rublesType = "рублей";
        } else rublesType = "рубля";
        System.out.println("\nКаждый должен заплатить: " + String.format("%.2f", sum / clientCount) + " " + rublesType);
    }
}

class Product {
    String name;
    Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
