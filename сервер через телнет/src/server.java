import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

public class server {
    Map cdbase = new HashMap<String,String>();

    static ExecutorService executeIt = Executors.newFixedThreadPool(8);
    public static void main(String[] args) {
        server srv = new server();
        srv.clientmap();
        srv.startServ();


        try {
            new MyRemoteImpl().go();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    void startServ() {
        try(ServerSocket SS = new ServerSocket(9000)) {
            while (!SS.isClosed()) {
            Socket sockA = SS.accept();
            System.out.println("соединение получено");
            executeIt.execute(new connectAcceptor(sockA));
            System.out.println("после егзекьюта");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void clientmap (){
        try {
            BufferedReader br = new BufferedReader(new FileReader("dbase.txt"));
            clientbase cbase = new clientbase();
            while (br.ready()){
                String var = br.readLine();
                String[]var1 = var.split(" ");
                cbase.baseadd(var1[0],var1[1]);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


