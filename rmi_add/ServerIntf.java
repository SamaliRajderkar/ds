import java.rmi.*;

interface ServerIntf extends Remote{
    public int addition(int a, int b) throws RemoteException;
}