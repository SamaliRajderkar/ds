import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService(targetNamespace = "http://test/")
public interface MyServiceInterface {

    @WebMethod
    double mileToKm(double miles);
}

