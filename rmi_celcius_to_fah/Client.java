import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

public class Client{
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);

            String url = "rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter Temperature in Celcius : ");
            double a = sc.nextDouble();

            System.out.print("Temperature in Fahrenheit : " + serverintf.converter(a));

        }   
        catch(Exception e){
            System.out.println("Exception");
        }
    }
}
