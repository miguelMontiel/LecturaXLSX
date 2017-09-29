package servicioSOAP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lectores.LectorCSV;
import org.w3c.dom.Element;

public class clienteSOAP 
{
    public static void main(String[] args) throws MalformedURLException, TransformerConfigurationException, TransformerException, ParserConfigurationException
    {
        URL url = new URL("http://localhost:9999/ws/ejemploSOAP?wsdl");
        QName qname = new QName("http://servicioSOAP/", "implementacionSOAPService");
        Service service = Service.create(url, qname);
        interfazSOAP interfazsoap = service.getPort(interfazSOAP.class);
        LectorCSV lectorcsv = new LectorCSV();
        String Todo = null;
        
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilderfactory.setNamespaceAware(true);
        
        DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
        Document document;
        Element element;
        
        try
        {
            // Escritura
            document = documentbuilder.newDocument();
            Element rootelement = document.createElement("root");
            element = document.createElement("role1");
            element.appendChild(document.createTextNode(Todo));
            rootelement.appendChild(element);

            document.appendChild(rootelement);
            
            try 
            {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("src/ejemploXML.xml")));

            } 
            catch(TransformerException | IOException te) 
            {
                System.out.println(te.getMessage());
            }
            
            System.out.println(lectorcsv.getTodo(Todo));
            
            // Lectura
            
            
            document = documentbuilder.parse("src/ejemploXML.xml");
            
            XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();
            XPathExpression xpathexpression = xpath.compile("//*[not(*)]");
            NodeList nodelist = (NodeList) xpathexpression.evaluate(document, XPathConstants.NODESET);
            
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                System.out.println(nodelist.item(i));
            }
        }
        catch(SAXException | IOException | XPathExpressionException e){}
    }
}
