import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
    
    public ServerImpl() throws RemoteException{

    }

    public int sub(int a , int b) throws RemoteException{
        return a-b;
    }
}