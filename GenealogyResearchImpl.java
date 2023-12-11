import java.util.List;
import java.util.Scanner;

public class GenealogyResearchImpl implements GenealogyResearch {
    private FamilyTree tree;
    private FileHandler fileHandler;
    private Scanner scanner;

    public GenealogyResearchImpl() {
        fileHandler = new FileHandler();
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Создать пример генеалогического древа");
        System.out.println("2. Добавить ребенка");
        System.out.println("3. Сохранить древо в файл");
        System.out.println("4. Загрузить древо из файла");
        System.out.println("5. Получить всех детей выбранного человека");
        System.out.println("0. Выйти");
    }

    @Override
    public void createSampleTree() {
        Person person1 = new Person("John");
        Person person2 = new Person("Mary");
        Person person3 = new Person("Bob");
        Person person4 = new Person("Alice");

        person1.addChild(person2);
        person1.addChild(person3);
        person2.addChild(person4);

        tree = new FamilyTree(person1);
        System.out.println("Пример генеалогического древа создан.");
    }

    @Override
    public void addChild() {
        if (tree == null) {
            System.out.println("Генеалогическое древо не создано. Создайте древо с помощью пункта меню 1.");
            return;
        }

        System.out.print("Введите имя родителя: ");
        String parentName = scanner.nextLine();

        System.out.print("Введите имя ребенка: ");
        String childName = scanner.nextLine();

        Person parent = findPersonByName(tree.getRoot(), parentName);
        if (parent != null) {
            Person child = new Person(childName);
            parent.addChild(child);
            System.out.println("Ребенок добавлен.");
        } else {
            System.out.println("Родитель ненайден.");
        }
    }

    @Override
    public void saveTreeToFile() {
        if (tree == null) {
            System.out.println("Генеалогическое древо не создано. Создайте древо с помощью пункта меню 1.");
            return;
        }

        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine();

        fileHandler.writeTreeToFile(tree, filename);
    }

    @Override
    public void loadTreeFromFile() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();

        tree = fileHandler.readTreeFromFile(filename);
    }

    @Override
    public void getChildren() {
        if (tree == null) {
            System.out.println("Генеалогическое древо не создано. Создайте древо с помощью пункта меню 1.");
            return;
        }

        System.out.print("Введите имя родителя: ");
        String parentName = scanner.nextLine();

        Person parent = findPersonByName(tree.getRoot(), parentName);
        if (parent != null) {
            List<Person> children = parent.getChildren();
            if (!children.isEmpty()) {
                System.out.println("Дети выбранного человека:");
                for (Person child : children) {
                    System.out.println(child.getName());
                }
            } else {
                System.out.println("У выбранного человека нет детей.");
            }
        } else {
            System.out.println("Человек не найден.");
        }
    }

    private Person findPersonByName(Person person, String name) {
        if (person.getName().equals(name)) {
            return person;
        }

        List<Person> children = person.getChildren();
        for (Person child : children) {
            Person foundPerson = findPersonByName(child, name);
            if (foundPerson != null) {
                return foundPerson;
            }
        }

        return null;
    }
}