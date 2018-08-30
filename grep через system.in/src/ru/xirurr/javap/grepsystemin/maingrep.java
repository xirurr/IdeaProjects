package ru.xirurr.javap.grepsystemin;

import java.nio.charset.Charset;
import java.util.Scanner;

public class maingrep {
    public static void main(String[] args) {
        maingrep mgp = new maingrep();
        if (args.length==0){
            System.out.println("введи строку поиска, кретин");
            return;
        }
        mgp.start(args[0]);

    }

    void start(String chto){
        Scanner in;
        System.out.println(this.getOS().toLowerCase());
        if (this.getOS().toLowerCase().indexOf("win")!=-1) {
            System.out.println("винда");
             in = new Scanner(System.in, "IBM-866");
        }
        else {
            System.out.println("не винда");
             in = new Scanner(System.in);
        }
        while (in.hasNext()){
            String gde = in.nextLine();
           // System.out.println(gde);
            grepit(gde,chto);
        }
    }
    void grepit(String gde, String chto) {
        if (gde.equals("")){
            return;
        }
        if (gde.toLowerCase().indexOf(chto.toLowerCase())!=-1){
            System.out.println(gde);
        }
    }

    String getOS(){
        String s = System.getProperty("os.name");
        return s;
    }


}
