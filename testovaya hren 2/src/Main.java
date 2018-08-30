import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
     //   main.start();
     //   main.net();
        main.pb();
     //   main.chatest();

    }
    void start(){
        try {
            System.out.println(execCmd("das"));
        } catch (Exception e){
            e.printStackTrace();
        }
    /*    try

        {
            Process proc = Runtime.getRuntime().exec("ping ya.ru");
            proc.waitFor();
            proc.destroy();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
    }
    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec("ping ya.ru").getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    void net(){
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"cmd", "ping ya.ru -t"};
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

// read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    void pb(){
        try {
            ProcessBuilder pb = new ProcessBuilder("ping", "ya.ru", "","");
            Map<String, String> env = pb.environment();
            env.put("VAR1", "myValue");
            env.remove("OTHERVAR");
            env.put("VAR2", env.get("VAR1") + "suffix");
            File workingFolder = new File("/");
            pb.directory(workingFolder);
            Process proc = pb.start();
            Charset inputCharset = Charset.forName("IBM-866");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream(),inputCharset));
            String s = null;
            while ((s = stdInput.readLine()) != null)
            {
                System.out.println(s);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    void chatest(){
        System.out.println(Charset.availableCharsets());
    }


}
