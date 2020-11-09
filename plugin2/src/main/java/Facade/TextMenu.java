package Facade;

import Builder.Product;
import Factory.Factory;
import Factory.Report;
import Factory.Request;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// creates and runs a text menu as a Facade.Facade for a Factory.Factory
public class TextMenu implements Facade {

    Factory factory;
    ArrayList<String> patternNames;
    Scanner scanner = new Scanner(System.in);
    Request request = new Request();
    Product product;

    public TextMenu(Factory factory) {
        this.factory = factory;
        Report r = factory.createReport();
        patternNames = r.getContent();
    }

    @Override
    // runs the Facade.TextMenu
    public void run() {
        Configs.logger.trace("Inside Facade.TextMenu.run()");

        displayMenu();
        request = getRequest();

        // log the request creation
        Configs.logger.debug("TextMenu created a request:");
        Configs.logger.debug(" Pattern: " + request.pattern);
        Configs.logger.debug(" name1: " + request.name1);
        Configs.logger.debug(" name2: " + request.name2);
        for (ArrayList<String> sArr : request.classNames) {
            Configs.logger.debug("  className: " + sArr.get(0));
            for (String s : sArr)
                Configs.logger.debug("   " + s);
        }

        // order the requested code from Factory.Factory
        product = factory.createProduct(request);

        // Display confirmation
        if (product==null) {
            System.out.println("Something went wrong. No code created");
            return;
        }
        System.out.println("\nGeneration successful!");
        System.out.println("Created files:");
        ArrayList<File> files = product.getFiles();
        for (File f : files)
            System.out.println(f.getAbsolutePath());
    }

    // displays the list of available design patterns
    private void displayMenu() {
        Configs.logger.trace("Inside Facade.TextMenu.displayMenu()");
        System.out.println("\nPatterns:\n");
        int i = 1;
        for (String s : patternNames) {
            System.out.print(i++);
            System.out.println(" " + s);
        }
    }

    // builds a request for a pattern based on user input
    private Request getRequest() {
        Configs.logger.trace("Inside textMenu.getRequest()");

        Request r = new Request();

        System.out.println("\nChoose a pattern: ");
        r.pattern = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the main interface name: ");
        r.name1 = scanner.nextLine();
        System.out.println("Enter the second interface name (if needed): ");
        r.name2 = scanner.nextLine();

        System.out.printf("Enter the number of classes implementing %s: \n", r.name1);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<n; i++) {
            ArrayList<String> temp = new ArrayList<>();
            System.out.printf(" Enter a name for class #%d implementing %s:\n", i+1, r.name1);
            temp.add(scanner.nextLine());

            System.out.printf(" Enter the number of classes implementing %s for %s:\n", r.name2, temp.get(0));
            int m = scanner.nextInt();
            scanner.nextLine();
            for (int j=0; j<m; j++) {
                System.out.printf("  Enter a name for class #%d implementing %s for %s:\n", j+1, r.name2, temp.get(0));
                temp.add(scanner.nextLine());
            }
            r.classNames.add(temp);
        }
        System.out.println();
        scanner.close();
        return r;
    }
}
