package client;

import compute.MyRemote;
import compute.Second;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;

public class MyRemoteClient {
    /*public static void main(String[] args) {
        new MyRemoteClient().go();
    }*/
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try{
            Registry registry = LocateRegistry.getRegistry("localhost");
            MyRemote mr = (MyRemote) registry.lookup("MRMT");
            Second mr2 = (Second) registry.lookup("MRMT2");
            String s = mr.sayHello();
            String s2 = mr2.sayBB();
            System.out.println(s);
            System.out.println(s2);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
