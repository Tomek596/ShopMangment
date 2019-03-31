import api.ProductFacade;
import api.UserRegisterLoginFacade;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import entity.enums.Color;
import entity.enums.Material;
import entity.enums.SkinType;
import entity.parser.ColorParser;
import entity.parser.MaterialParser;
import entity.parser.SkinParser;
import facade.ProductFacadeImpl;
import facade.UserRegisterLoginFacadeImpl;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static void startMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Zaloguj się");
        System.out.println("2 - Zarejestruj się");
        System.out.println("0 - Wyjdź");
    }

    private static void loggedMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Dodaj nowy produkt");
        System.out.println("2 - Usuń produkt");
        System.out.println("3 - Wyświetl dostepne produkty");
        System.out.println("0 - Wyloguj się");
    }

    private static void productTypeMenu() {
        System.out.println("1 - Dodaj buty");
        System.out.println("2 - Dodaj ubrania");
        System.out.println("3 - Inne");
    }

    private static Product createOtherProduct() {
        String productName;
        Color color;
        Float price, weight;
        Integer count;

        System.out.println("ProductName: ");
        productName = scanner.next();

        System.out.println("Price: ");
        price = scanner.nextFloat();

        System.out.println("Weight: ");
        weight = scanner.nextFloat();

        System.out.println("Choose one of colors: RED, BLUE, GREEN, WHITE, BLACK, YELLOW");
        color = ColorParser.parseStrToColor(scanner.next());

        System.out.println("Count: ");
        count = scanner.nextInt();

        return new Product(1L, productName, price, weight, color, count);
    }

    private static Product createBootsProduct() {
        String productName;
        Color color;
        Float price, weight;
        Integer count, size;
        SkinType skinType;

        System.out.println("ProductName: ");
        productName = scanner.next();

        System.out.println("Price: ");
        price = scanner.nextFloat();

        System.out.println("Weight: ");
        weight = scanner.nextFloat();

        System.out.println("Choose one of colors: RED, BLUE, GREEN, WHITE, BLACK, YELLOW");
        color = ColorParser.parseStrToColor(scanner.next());

        System.out.println("Count: ");
        count = scanner.nextInt();

        System.out.println("Size: ");
        size = scanner.nextInt();

        System.out.println("Choose skin type: NATURAL, ARTIFICIAL");
        skinType = SkinParser.parseStrToSkinType(scanner.next());

        return new Boots(1L, productName, price, weight, color, count, size, skinType);
    }

    private static Product createClothProduct() {
        String productName, size;
        Material material;
        Color color;
        float price, weight;
        int count;
        System.out.println("ProductName: ");
        productName = scanner.next();
        System.out.println("Price: ");
        price = scanner.nextFloat();
        System.out.println("Weight: ");
        weight = scanner.nextFloat();
        System.out.println("Choose one of colors: RED, BLUE, GREEN, WHITE, BLACK, YELLOW");
        color = ColorParser.parseStrToColor(scanner.next());
        System.out.println("Count: ");
        count = scanner.nextInt();
        System.out.println("Size: ");
        size = scanner.next();
        System.out.println("Choose material: LEATHER, FUR, COTTON, WOOL, POLYESTERS.");
        material = MaterialParser.parseStrToMaterial(scanner.next());
        return new Cloth(1L, productName, price, weight, color, count, size, material);
    }

    public static void main(String[] args) {
        UserRegisterLoginFacade userFacade = UserRegisterLoginFacadeImpl.getInstance();
        ProductFacade productFacade = ProductFacadeImpl.getInstance();
        boolean appOn = true;
        boolean loggedOn = false;
        int read;
        while (appOn) {
            startMenu();
            read = scanner.nextInt();

            switch (read) {
                case 1:
                    System.out.println("Podaj login:");
                    String loginLog = scanner.next();
                    System.out.println("Podaj hasło:");
                    String passwordLog = scanner.next();
                    if (userFacade.loginUser(loginLog, passwordLog)) {
                        loggedOn = true;
                        System.out.println("Zalogowałeś się!");
                    } else {
                        System.out.println("Niepoprawne dane!");
                    }
                    break;
                case 2:
                    System.out.println("Podaj login:");
                    String loginReg = scanner.next();
                    System.out.println("Podaj hasło:");
                    String passwordReg = scanner.next();
                    User user = new User(1L, loginReg, passwordReg);
                    userFacade.registerUser(user);
                    break;
                case 0:
                    appOn = false;
                    break;
            }
            while (loggedOn) {
                loggedMenu();
                read = scanner.nextInt();
                switch (read) {
                    case 1:
                        productTypeMenu();
                        read = scanner.nextInt();
                        Product product = null;
                        switch (read) {
                            case 1:
                                product = createBootsProduct();
                                break;
                            case 2:
                                product = createClothProduct();
                                break;
                            case 3:
                                product = createOtherProduct();
                                break;
                        }
                        System.out.println(productFacade.createProduct(product));
                        break;
                    case 2:
                        System.out.println("Dostepne produkty: " + productFacade.getAllProducts());
                        System.out.println("Podaj nazwę produktu do usunięcia: ");
                        String productName = scanner.next();
                        System.out.println(productFacade.removeProduct(productName));
                        break;
                    case 3:
                        System.out.println("Dostępne produkty to: " + productFacade.getAllProducts());
                        break;
                    case 0:
                        loggedOn = false;
                        break;
                }
            }
        }
    }
}