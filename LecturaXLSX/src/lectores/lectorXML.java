package lectores;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import escritores.escritorXML;

// De esta clase lo que quiero para poder hacer el nuevo XML es solamente el nombre de los nodos.
public class lectorXML 
{
	// Quiero que me regrese un String, el cual va a ser iterado por cada uno de los nodos.
	public String getNodos(String nodos)
	{   
        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        documentbuilderfactory.setNamespaceAware(true);
        Document document;
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        escritorXML escritorxml = new escritorXML();
        
        try
        {  
            DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
        	
            // El documento de donde quiero obtener los nodos
            document = documentbuilder.parse("src/siniestrosDanos.xml");
            
            // Imprime todo, pero no me imprimas el signo '*' en si.
            XPathExpression xpathexpression = xpath.compile("*");
            
            // Estoy dando a conocer cual es el tipo de dato donde se van a guardar los nodos del XML.
            NodeList nodelist = (NodeList) xpathexpression.evaluate(document, XPathConstants.NODESET);
            
            // Iterar por cada campo e imprimelo.
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                // System.out.println(nodelist.item(i).getNodeName() + ": " + node.getTextContent());
                nodos = nodelist.item(i).getNodeName().toString();
                System.out.println("Nodo raiz: " + nodos);
                // De aqui voy a llamar al escritor para que me vaya escribiendo cada nodo en un nuevo XML.
            }
            escritorxml.hacerXML(nodelist);
        }
        catch(SAXException | IOException | XPathExpressionException | ParserConfigurationException e)
        {
        	e.printStackTrace();
        }
		return nodos;
    }
}