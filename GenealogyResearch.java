import java.util.List;
import java.util.Scanner;

public interface GenealogyResearch {
    void displayMenu();
    void createSampleTree();
    void addChild();
    void saveTreeToFile();
    void loadTreeFromFile();
    void getChildren();
}
