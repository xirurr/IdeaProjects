import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        if (a>=1){
            if (a-((int)(a))==0){
                System.out.println("число"+a+" "+"целое");
            }
            else {
                System.out.println("число"+a+" "+" дробное");
            }
        }
    }
}
