import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<String> wordlist = new ArrayList<>();
    BufferedReader BR;
    InputStreamReader ISR;
    String firstword;
    String lastword;

    public static void main(String[] args) {
        Main mn = new Main();
        mn.start();
        mn.scanning();


    }

    void start() {
        Readfile();
      //  Scanner in = new Scanner(System.in);
       /* firstword = in.next();
        lastword = in.next();*/
       firstword = "аборт";
       lastword = "забор";
       System.out.println(firstword+"  "+lastword);

    }

    void Readfile(){
        {
            try {
                ISR = new InputStreamReader(new FileInputStream("words.txt"), "windows-1251");
                BR = new BufferedReader(ISR);
                while (BR.ready()){
                    String var = BR.readLine();
                    wordlist.add(var);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    void scanning(){
      //  if (lastword.length()>firstword.length()){

            System.out.println(firstword.charAt(0));

      //  }


    }
}
