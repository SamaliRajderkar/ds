import java.rmi.*;

interface ServerIntf extends Remote{
    public int power(int a) throws RemoteException;
}
