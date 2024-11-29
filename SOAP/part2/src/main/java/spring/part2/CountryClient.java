package spring.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetCountryRequest;
import com.example.consumingwebservice.wsdl.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting data for {}", country);

        return (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback("http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));
    }
}
