import java.util.ArrayList;
import java.util.Comparator;

public class MS {
    String[] names = { "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex", "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda", "Aaron", "Kate" };
    int[] times = { 341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299, 343, 317, 265 };
    ArrayList<marafonec> spisok = new ArrayList<>();
    public static void main(String[] args) {
        new MS().start();
    }
    void start(){
        int i=0;
        for (String g:names){
            marafonec var = new marafonec();
            var.setname(g);
            var.settime(times[i]);
            spisok.add(var);
            i++;
        }
        for (marafonec g:spisok){
            System.out.println(g.getname()+" "+g.gettine());
        }
        sorting();
        System.out.println("__________________");

            System.out.println(spisok.get(1).getname()+" "+spisok.get(1).gettine());

    }
    void sorting(){
        spisok.sort(Comparator.comparing(marafonec::gettine));
    }

}

class marafonec{
    String name;
    int time;

    void setname (String varname){
        name = varname;
    }
    void settime (int vartime){
        time = vartime;
    }

    String getname(){
        return  name;
    }
    int gettine (){
        return time;
    }
}
