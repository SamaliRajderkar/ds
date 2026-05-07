import java.rmi.*;
import java.rmi.server.*;

interface ServerIntf extends Remote{
    public String compare(String s1, String s2) throws RemoteException;
}
