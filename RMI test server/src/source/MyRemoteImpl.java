package source;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import compute.*;


public class MyRemoteImpl {
    public MyRemoteImpl() throws RemoteException{}

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try{
            MyRemote serv = new testtest();
            Second serv2 = new bbtest();
            MyRemote stub = (MyRemote) UnicastRemoteObject.exportObject(serv,0);
            Second stub2 = (Second) UnicastRemoteObject.exportObject(serv2,0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("MRMT",stub);
            registry.rebind("MRMT2",stub2);
            System.out.println("bound");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
class testtest implements MyRemote{
    public String sayHello(){
        return  "Сервер готdddddовит";
    }
    public String sayHello2(){
        return  "Сервер 2";
    }

}

class bbtest implements Second{
    public String sayBB(){
        return  "ДОСВИДАНИЯ";
    }
    public String sayBB2(){
        return  "ДОСВИДАНИЯ2222";
    }
}
