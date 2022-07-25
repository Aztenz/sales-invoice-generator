import java.util.Scanner;
import model.Shape;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Shape name:");
        String n = sc.next();
        System.out.println("Please enter Shape color:");
        String c = sc.next();
        Shape s = new Shape(n, c);
        s.display();
    }
}
