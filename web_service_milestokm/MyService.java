import javax.jws.WebService;

@WebService(
    endpointInterface = "MyServiceInterface",
    targetNamespace = "http://test/"
)
public class MyService implements MyServiceInterface {

    public double mileToKm(double miles) {
        return miles * 1.6;
    }
}