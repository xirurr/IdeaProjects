package compute;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
    String sayHello2() throws RemoteException;
}
