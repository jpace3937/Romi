import java.util.Scanner;

public class App {
    public static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        one();
        two();
        three();
        four();
        five();
        six();
        input.close();
    }
    public static void one(){
        for(int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }
    public static void two(){
        int i = -10;
        while(i < 21){
            System.out.println(i);
            i++;
        }
    }
    public static void three(){
        double total = 0;
        double max = input.nextDouble();
        for(int i = 1; i <= max; i++){
            total += (i*i);
        }
        System.out.println(total);
    }
    public static void four(){
        double total = 0;
        double max = input.nextDouble();
        for(int i = 1; i <= max; i++){
            total += (i);
        }
        System.out.println(total*total);
    }
    public static void five(){
        double total = 0;
        double total1 = 0;
        double max = input.nextDouble();
        for(int i = 1; i <= max; i++){
            total += (i);
        }
        for(int i = 1; i <= max; i++){
            total1 += (i*i);
        }
        System.out.println((total*total) - total1);
    }
    public static void six(){
        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
                System.out.print(i*j + " ");
            }
            System.out.println();
        }
    }
}
