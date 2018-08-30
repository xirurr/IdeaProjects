import java.util.ArrayList;

public class MS {
    public static void main(String[] args) {
        new MS().start();
    }

    void start() {
        ArrayList<Integer> prost = new ArrayList<>();
        int finish;
        int start;
        int current;
        start = 2;
        finish = 1000000;
        for (current = start; current <= finish; current++) {
            if (current%2==0){
                if (current!=2)
                {
                    continue;
                }
            }
            else if (current%3==0){
                if (current!=3)
                {
                    continue;
                }
            }
            else if (current % 5 == 0) {
                if (current!=5)
                {
                    continue;
                }
            }
            else if (current % 5 == 0) {
                if (current!=5)
                {
                    continue;
                }
            }

            prost.add(current);
        }
    }

}
