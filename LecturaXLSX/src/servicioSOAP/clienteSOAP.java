package servicioSOAP;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.ws.Service;

import lectores.LectorCSV;
import lectores.lectorXML;

public class clienteSOAP 
{
    public static void main(String[] args) throws MalformedURLException, TransformerConfigurationException, TransformerException, ParserConfigurationException
    {
        URL url = new URL("http://localhost:9999/ws/ejemploSOAP?wsdl");
        QName qname = new QName("http://servicioSOAP/", "implementacionSOAPService");
        Service service = Service.create(url, qname);
        interfazSOAP interfazsoap = service.getPort(interfazSOAP.class);
        
        LectorCSV lectorcsv = new LectorCSV();
        lectorXML lectorxml = new lectorXML();
        String Todo = null;
        String Nodos = null;
        
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilderfactory.setNamespaceAware(true);
        
        
        // Aquí estoy leyendo todos los campos que seleccione de mi CSV, con esto puedo decir que las clases 'seleccionadorCSV.java' y 'LectorCSV.java' funcionan correctamente.
		System.out.println(lectorcsv.getTodo(Todo));
		
		// Aquí voy a imprimir todos mis nodos.
		System.out.println(lectorxml.getNodos(Nodos));
    }
}