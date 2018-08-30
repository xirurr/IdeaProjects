import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MS {
    ArrayList<String> massive = new ArrayList<>();
    public static void main(String[] args) {
        new MS().start();
    }
   void  start(){
        try{
           FileWriter FW = new FileWriter("test.txt");
           BufferedWriter BW = new BufferedWriter(FW);
           Scanner in = new Scanner(System.in);
           BW.write(in.next());
           BW.flush();
           FileReader FR = new FileReader("test.txt");
           BufferedReader BR = new BufferedReader(FR);
           while (BR.ready()){
               String smbdl;
               char c;
               c = (char)BR.read();
               smbdl = String.valueOf(c);
               if (smbdl.equals(".")){
                   break;
               }
               massive.add(smbdl);
           }
           System.out.println(massive);



       }catch (Exception ex){
            ex.printStackTrace();
        }
   }
}
