import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int userInput = input.nextInt();
        System.out.println("User Input: " + userInput);
        input.close();
    }
}
