import java.io.File;
import java.util.Scanner;

public class CMD {
    File fl;
    public static void main(String[] args) {
        new CMD().start();
    }
    void start(){
        fl = cd("");
        Scanner in = new Scanner(System.in);
        String vark = in.next();
        while (true) {
            if (vark.equals("pwd")) {
                System.out.println(fl.getAbsolutePath());
                vark = in.next();
            } else if (vark.equals("dir")) {
                dir();
                vark = in.next();
            } else if (vark.equals("cd")) {
                if (in.hasNext()) {
                    vark = in.next();
                }
                cd(vark);
                vark = in.next();
            }
        }
    }
    String pwd(){
        if (fl!=null) {
            String var = fl.getAbsolutePath();
            return var;
        }
        return null;
    }
    void dir(){
        String[] s = fl.list();
        if (s!=null) {
            for (String g : s) {
                System.out.println(g);
            }
        }
        else
        {System.out.println("папка пуста");}
    }

    File cd(String path){
        if (pwd()==null){
            fl = new File(path);
            return fl;
        }
        else {
            char[] c;
            c = path.toCharArray();
            if (c[0] =='/'){
                fl = new File(path);
                System.out.println(fl.getAbsolutePath());
                return fl;
            }
            else {
                String pwd = fl.getAbsolutePath();
                fl = new File(pwd + "/" + path);
                System.out.println(fl.getAbsolutePath());
                return fl;
            }

        }
    }
}

