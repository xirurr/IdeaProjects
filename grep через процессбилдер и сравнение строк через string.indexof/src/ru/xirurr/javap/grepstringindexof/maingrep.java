package ru.xirurr.javap.grepstringindexof;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

public class maingrep {
    public static void main(String[] args) {
        String search = args [0];
        String command = args[1];
        String par1 = args[2];
        String par2 ="";
        String par3 ="";
        if (args.length>3)
        {
            par2 = args[3];
        }
        if (args.length>4)
        {
            par3 = args[4];
        }
        maingrep m1 = new maingrep();
        try {
            m1.pb(search, command, par1, par2, par3);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void grepit(String gde, String chto) throws Exception{
        if (gde.equals("")){
            return;
        }
       if (gde.toLowerCase().indexOf(chto.toLowerCase())!=-1){
           System.out.println(gde);
       }
    }

    void pb(String search,String command,String par1,String par2,String par3) throws Exception{
        ProcessBuilder pb = new ProcessBuilder(command,par1,par2,par3);
        Map<String, String> env = pb.environment();
        env.put("VAR1", "myValue");
        env.remove("OTHERVAR");
        env.put("VAR2", env.get("VAR1") + "suffix");
        File workingFolder = new File("C:\\Windows\\System32");
        pb.directory(workingFolder);
        Process proc = pb.start();
        Charset inputCharset = Charset.forName("IBM-866");
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream(),inputCharset));
        while(!stdInput.ready()){
            Thread.sleep(1);
        }
        while (stdInput.ready()){
            String s = stdInput.readLine();
            grepit(s,search);
        }


    }
}
