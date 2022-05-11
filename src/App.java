public class App {
    public static void main(String[] args) throws Exception {
        int dayOfWeek = 7;
        if(dayOfWeek < 2){
            System.out.println("Sunday");
        }
        else if(dayOfWeek < 3){
            System.out.println("Monday");
        }
        else if(dayOfWeek < 4){
            System.out.println("Tuesday");
        }
        else if(dayOfWeek < 5){
            System.out.println("Wednesday");
        }
        else if(dayOfWeek < 6){
            System.out.println("Thursday");
        }
        else if(dayOfWeek < 7){
            System.out.println("Friday");
        }
        else{
            System.out.println("Saturday");
        }
    }
}
