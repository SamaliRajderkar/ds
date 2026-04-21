import java.rmi.*;

interface ServerIntf extends Remote{
    
    public int sub(int a, int b) throws RemoteException;
}