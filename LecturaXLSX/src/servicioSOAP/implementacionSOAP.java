package servicioSOAP;

import javax.jws.WebService;

@WebService(endpointInterface = "servicioSOAP.interfazSOAP")

public class implementacionSOAP implements interfazSOAP
{  
    @Override
    public String getSiniestros(String siniestro)
    {
        return "Hello world!" + siniestro;
    }
}
