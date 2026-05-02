import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
    public ServerImpl() throws RemoteException{

    }
    public int fact(int a) throws RemoteException{
        int f = 1;
        for(int i=1; i<=a; i++){
            f *= i;
        }
        return f;
    }
}
