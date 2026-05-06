import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = sc.nextDouble();

        System.out.print("Enter second number: ");
        double b = sc.nextDouble();

        URL url = new URL("http://localhost:8080/test?wsdl");
        QName qname = new QName("http://test/", "MyServiceService");

        Service service = Service.create(url, qname);
        MyServiceInterface obj = service.getPort(MyServiceInterface.class);

        System.out.println("Addition = " + obj.add(a, b));
        System.out.println("Subtraction = " + obj.sub(a, b));
        System.out.println("Multiplication = " + obj.mul(a, b));
        System.out.println("Division = " + obj.div(a, b));
    }
}
