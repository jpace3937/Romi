import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int firstNumber = input.nextInt();
        int secondNumber = input.nextInt();
        int product = multiply(firstNumber, secondNumber);
        System.out.println(product);
        input.close();
    }
    public static void welcome(){
        System.out.println("Welcome to calculator!");
    }
    public static void add(int num1, int num2){
        System.out.println(num1 + num2);
    }
    public static int multiply(int num1, int num2){
        return num1*num2;
    }
    public static int subtract(int num1, int num2){
        return num1 - num2;
    }
    public static int cube(int num){
        return (int) Math.pow(num, 3);
    }
}
