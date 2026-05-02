import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{

    public ServerImpl() throws RemoteException{

    }

    public double converter(double a) throws RemoteException{
        return ((a * 1.8) + 32);  
    }
}
