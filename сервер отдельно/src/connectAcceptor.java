import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

public  class connectAcceptor implements Runnable {
    DataInputStream DIS;
    PrintWriter PW;
    public static Socket clienDialog;
    ArrayList<String> chaty = new ArrayList<>();
    static ArrayList<PrintWriter> clienty = new ArrayList<>();
    public connectAcceptor(Socket sockA) {
        connectAcceptor.clienDialog = sockA;
    }




    public void run() {
        try {
             DIS = new DataInputStream(clienDialog.getInputStream());
             PW =new PrintWriter(clienDialog.getOutputStream());
            clienty.add(PW);
            while (!clienDialog.isClosed()) {
                String entry = DIS.readUTF();
                System.out.println("ПРОЧИТАНО" + entry);
                Calendar rightNow = Calendar.getInstance();
                String entry2 = rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE) + "    " + entry;
                chatlog cl = new chatlog();
                chaty = cl.setgetChaty(entry2);

                System.out.println(entry);

                for (PrintWriter clt:clienty){
                    int le = chaty.size();
                    clt.println(le);
                    System.out.println(clt);
                    clt.flush();
                    while (le > 0) {
                        String g = chaty.get(le - 1);
                        clt.println(g);
                        System.out.println(g);
                        clt.flush();
                        le--;
                    }
                }
            }
            }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

