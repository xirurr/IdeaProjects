package compute;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Second extends Remote {
    String sayBB() throws RemoteException;
    String sayBB2 ()throws RemoteException;
}
