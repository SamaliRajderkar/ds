import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(targetNamespace = "http://test/")
public interface MyServiceInterface {

    @WebMethod
    double add(double a, double b);

    @WebMethod
    double sub(double a, double b);

    @WebMethod
    double mul(double a, double b);

    @WebMethod
    double div(double a, double b);
}
