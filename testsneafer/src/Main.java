import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Main  {
    Socket chatSocket=null;;
    BufferedReader BR =null;;
    InputStreamReader ISR =null;
    DataOutputStream DOS=null;
    PrintWriter PW = null;
    int[] var = {65533,65533,31,65533,65533,32,65533,65533,24,65533,65533,39,65533,65533,1,65533,65533,3,65533,65533,3};


    public static void main(String[] args){
        Main mb = new Main();
        mb.chatconnect();
    }


    void chatconnect() {
        try {
            if (chatSocket ==null) {
                System.out.println("попытка подключиться");
                chatSocket = new Socket("192.168.88.1", 22);
                DOS = new DataOutputStream(chatSocket.getOutputStream());
                ISR = new InputStreamReader(chatSocket.getInputStream());
                BR = new BufferedReader(ISR);
                PW = new PrintWriter(chatSocket.getOutputStream(), true);

                chatlistener();
                send();


            }
            else{
                DOS = new DataOutputStream(chatSocket.getOutputStream());
                ISR = new InputStreamReader(chatSocket.getInputStream());
                BR = new BufferedReader(ISR);
                chatlistener();
                send();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    void chatlistener(){
        System.out.println("попытка запустить второй");
        Thread chatControl = new Thread(new incomreader());
        System.out.println("вроде создан");
        chatControl.start();
        System.out.println("вроде начат");
    }
    void send() throws Exception{
        while (!chatSocket.isClosed()) {
            /*Scanner in = new Scanner(System.in);
            String var = in.next();
            System.out.println("poslano  "+var);
            PW.println(var);*/
            for (int g:var){
                PW.println(g);
            }
        }

    }

    public class incomreader implements Runnable{
        public void run(){
            try {
                if (chatSocket == null) {
                    chatconnect();
                    varvorIncomreader();
                } else {
                    varvorIncomreader();
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }

        private void varvorIncomreader() throws Exception {
            String fb;
            if (BR == null) {
                chatconnect();
                while ((fb = BR.readLine()) != null) {
                    System.out.println(fb);

                }}
            else{
                while ((fb = BR.readLine()) != null) {
                    System.out.println(fb);
                        fb=BR.readLine();
                        System.out.println("dd");
                        System.out.println(fb+"1");
                    fb=BR.readLine();
                    System.out.println("da");
                    System.out.println(fb+"2");
                }
            }
        }
    }

}
