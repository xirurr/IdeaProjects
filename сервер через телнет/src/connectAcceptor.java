import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public  class connectAcceptor implements Runnable {

    public connectAcceptor(Socket sockA) {
        connectAcceptor.clienDialog = sockA;
    }

    BufferedReader input;
    PrintWriter PW;
    public static Socket clienDialog;
    ArrayList<String> chaty = new ArrayList<>();
    static ArrayList<PrintWriter> clienty = new ArrayList<>();
    String login;
    clientbase cb = new clientbase();
    DataOutputStream DOS;


    public void run() {
        try {
            DOS =new DataOutputStream(clienDialog.getOutputStream());
            PW = new PrintWriter(clienDialog.getOutputStream(), true);
            InputStreamReader baseinput = new InputStreamReader(clienDialog.getInputStream());
            input = new BufferedReader(baseinput);
            clienty.add(PW);
            boolean loginstatus = loginprocess();


           if (!loginstatus) {
                PW.println("авторизация неудачна");
                clienDialog.close();
            }
            else{
                Broadcast("",login+" is online");
            }
            while (!clienDialog.isClosed()) {
                if (input.ready()) {
                    String var = input.readLine();
                    if (var.equalsIgnoreCase("quit")){
                        cb.deauth(login);
                        clienDialog.close();
                    }
                    Broadcast(login,var);
                }
                    if(baseinput.read()==-1) {
                //  Broadcast("",login+"is OFFLINE");
                    cb.deauth(login);
                    clienDialog.close();
                    }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String hashIT(String var) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(var.getBytes());
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String test = bigInt.toString(16);
        System.out.println(test);
        return test;
    }

    void Broadcast(String login, String text) {
      d:  for (PrintWriter clt : clienty) {
            if (clt==PW){
                continue d;
          }
            String var = login+":"+text;
            clt.println(var);
        }
    }

    boolean loginprocess() throws Exception {
        boolean loginstatus = false;
        PW.print("login:");
        PW.flush();
        login = input.readLine();
        if (login.indexOf("��\u001F�� ��\u0018��'��\u0001��\u0003��") != -1) {
            System.out.println("авторизация");
            login = login.replace("���� ����'������", "");
            PW.print("\033[aAC DO ECHO:");
            PW.flush();

            String password = input.readLine();
            password = hashIT(password);
            if (cb.checkauth(password, login)) {
                System.out.println(login+"Вошел");
                loginstatus = true;
            }
        }
        return loginstatus;
    }
}


