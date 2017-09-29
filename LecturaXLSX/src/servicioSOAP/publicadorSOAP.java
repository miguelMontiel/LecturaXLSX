package servicioSOAP;

import javax.xml.ws.Endpoint;

public class publicadorSOAP 
{
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:9999/ws/ejemploSOAP", new implementacionSOAP());
    }
}
