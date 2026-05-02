import java.rmi.*;

interface ServerIntf extends Remote{
    public int fact(int a ) throws RemoteException;
}
