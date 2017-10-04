package escritores;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class escritorXML 
{
    public void hacerXML(NodeList nodelist)
    {
        try
        {        	
        	DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            documentbuilderfactory.setValidating(true);
            documentbuilderfactory.setExpandEntityReferences(false);
        	DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
            Document document = documentbuilder.newDocument();
            Element rootElement = document.createElement("Root");
            document.appendChild(rootElement);

            for(int i = 0; i < nodelist.getLength(); i++)
            {
        		Node nodos = (Element) nodelist.item(i);
        		
        		Node copiarNodos = document.importNode(nodos, true);
        		// copiarNodos.setTextContent("Hola");
        		//Element element = document.createElement(nodelist.item(i));
        		rootElement.appendChild(copiarNodos);
            }

            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            Transformer transformer = transformerfactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource domsource = new DOMSource(document);
            StreamResult streamresult = new StreamResult(new File("Nuevos.xml"));
            transformer.transform(domsource, streamresult);
            
            System.out.println("Archivo creado!");
        } 
        catch (ParserConfigurationException | TransformerException ex) 
        {
            Logger.getLogger(escritorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
