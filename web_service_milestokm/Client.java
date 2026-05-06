import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter miles: ");
        double miles = sc.nextDouble();

        URL url = new URL("http://localhost:8080/test?wsdl");

        QName qname = new QName("http://test/", "MyServiceService");

        Service service = Service.create(url, qname);

        MyServiceInterface obj = service.getPort(MyServiceInterface.class);

        double result = obj.mileToKm(miles);

        System.out.println("KM = " + result);
    }
}


// T1 : javac *.java
// T1 : java Server
// check : http://localhost:8080/test?wsdl
// T2 : java Client 
