import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public void writeTreeToFile(FamilyTree tree, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(tree);
            System.out.println("Данные записаны в файл " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
        }
    }

    public FamilyTree readTreeFromFile(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            FamilyTree tree = (FamilyTree) objectIn.readObject();
            System.out.println("Данные прочитаны из файла " + filename);
            return tree;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при чтении данных из файла: " + e.getMessage());
        }

        return null;
    }
}