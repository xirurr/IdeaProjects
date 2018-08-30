import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {
    public String sayHello(){
        return  "Сервер готовит";
    }
    public MyRemoteImpl() throws RemoteException{}

    public void go() {
        try{
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("remhi", service);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
