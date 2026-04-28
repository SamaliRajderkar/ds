import java.rmi.*;

interface ServerIntf extends Remote{
    public int multiply(int a, int b) throws RemoteException ;
}
