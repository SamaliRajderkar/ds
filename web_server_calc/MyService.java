import javax.jws.WebService;

@WebService(
    endpointInterface = "MyServiceInterface",
    targetNamespace = "http://test/"
)
public class MyService implements MyServiceInterface {

    public double add(double a, double b) {
        double res = a + b;
        System.out.println("Add: " + res);
        return res;
    }

    public double sub(double a, double b) {
        double res = a - b;
        System.out.println("Sub: " + res);
        return res;
    }

    public double mul(double a, double b) {
        double res = a * b;
        System.out.println("Mul: " + res);
        return res;
    }

    public double div(double a, double b) {
        double res = a / b;
        System.out.println("Div: " + res);
        return res;
    }
}