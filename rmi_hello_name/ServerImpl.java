import java.rmi.*;
import java.rmi.server.*;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{

    public ServerImpl() throws RemoteException{

    }

    public String hello(String name) throws RemoteException{
        String msg = " Hello "+name+ " !!!";
        System.out.println(msg);
        return msg;
    }
}
