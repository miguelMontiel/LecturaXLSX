package servicioSOAP;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class clienteSOAP 
{
    public static void main(String[] args) throws MalformedURLException
    {
        URL url = new URL("http://localhost:9999/ws/");
        QName qname = new QName("http://lecturaxlsx/", "implementacionSOAP");
        Service service = Service.create(url, qname);
        interfazSOAP interfazsoap = service.getPort(interfazSOAP.class);
        
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilderfactory.setNamespaceAware(true);
        
        DocumentBuilder documentbuilder;
        Document document;
        
        try
        {
            documentbuilder = documentbuilderfactory.newDocumentBuilder();
            document = documentbuilder.parse("src/ejemploCSV.csv");
            
            XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();
            XPathExpression xpathexpression = xpath.compile("//*[not(*)]");
            NodeList nodelist = (NodeList) xpathexpression.evaluate(document, XPathConstants.NODESET);
            
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                System.out.println(nodelist.item(i));
            }
        }
        catch(ParserConfigurationException | SAXException | IOException | XPathExpressionException e)
        {
            e.printStackTrace();
        }
    }
}
