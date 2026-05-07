import java.rmi.*;
import java.rmi.server.*;

interface ServerIntf extends Remote{
     public int count(String str) throws RemoteException;
}
