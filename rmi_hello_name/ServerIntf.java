import java.rmi.*;

interface ServerIntf extends Remote{
    public String hello(String name) throws RemoteException;
}
