package escritores;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class escritorXML 
{
    public void hacerXML(List<String> nodos)
    {
        try
        {
            DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();

            Document document = documentbuilder.newDocument();
            
            // Estos van a ser los elementos que voy a recibir del String nodos para agregar uno por cada uno que reciba.
            Element element = document.createElement(nodos.toString());
            document.appendChild(element);
            
            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            Transformer transformer = transformerfactory.newTransformer();
            DOMSource domsource = new DOMSource(document);
            StreamResult streamresult = new StreamResult(new File("Nuevo.xml"));
            
            transformer.transform(domsource, streamresult);
            
            System.out.println("Archivo creado!");
        } 
        catch (ParserConfigurationException | TransformerException ex) 
        {
            Logger.getLogger(escritorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
