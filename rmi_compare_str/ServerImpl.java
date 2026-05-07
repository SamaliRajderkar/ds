import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
    public ServerImpl() throws RemoteException{

    }

    public String compare(String s1, String s2) throws RemoteException{
        if(s1.compareTo(s2)>0){
            return s1;
        }
        else{
            return s2;
        }
    }
}
