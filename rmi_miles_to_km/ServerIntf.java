import java.rmi.*;

interface ServerIntf extends Remote{
    public double converter(double a) throws RemoteException;
}
