package servicioSOAP;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import lectores.LectorCSV;
import lectores.lectorXML;

public class clienteSOAP 
{
    public static void main(String[] args) throws MalformedURLException 
    {
        URL url = new URL("http://localhost:9999/ws/ejemploSOAP?wsdl");
        QName qname = new QName("http://servicioSOAP/", "implementacionSOAPService");
        Service service = Service.create(url, qname);
        interfazSOAP interfazsoap = service.getPort(interfazSOAP.class);
        
        LectorCSV lectorcsv = new LectorCSV();
        lectorXML lectorxml = new lectorXML();
        String texto = null;
        String nodos = null;
        
        // Aquí estoy leyendo todos los campos que seleccione de mi CSV, con esto puedo decir que las clases 'seleccionadorCSV.java' y 'LectorCSV.java' funcionan correctamente.
        System.out.println(lectorcsv.getTodo(texto));
        
        // Aquí quiero que me imprimas todos los nodos que se encuentran en el XML que declaré en el lectorXML.java
        System.out.println(lectorxml.getNodos(nodos));    
     }
}