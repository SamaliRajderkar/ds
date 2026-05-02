import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client{
    public static void main(String[] args ){
        try{
            Scanner sc = new Scanner(System.in);
            String url = "rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter miles : ");
            double a = sc.nextDouble();

            System.out.print("Kilometers : " + serverintf.converter(a));
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }
}
