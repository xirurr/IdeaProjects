import java.io.ByteArrayOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

public class server {

    static ExecutorService executeIt = Executors.newFixedThreadPool(8);
    public static void main(String[] args) {
        server srv = new server();
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
}

class chatlog {
    static ArrayList<String> chaty = new ArrayList<String>();


    public synchronized ArrayList<String> setgetChaty(String text){
        chaty.add(text);
        return chaty;
    }
}


