import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GenealogyResearch research = new GenealogyResearchImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            research.displayMenu();
            System.out.print("Введите номер пункта меню: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера после ввода числа

            switch (choice) {
                case 1:
                    research.createSampleTree();
                    break;
                case 2:
                    research.addChild();
                    break;
                case 3:
                    research.saveTreeToFile();
                    break;
                case 4:
                    research.loadTreeFromFile();
                    break;
                case 5:
                    research.getChildren();
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите пункт меню от 0 до 5.");
            }
        }
    }
}