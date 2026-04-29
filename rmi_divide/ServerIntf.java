import java.rmi.*;

interface ServerIntf extends Remote{
    public double divide(int a, int b) throws RemoteException;
}