package escritores;

import org.w3c.dom.*;

public class escritorXML 
{
	public String hacerXML(String nodos)
	{
		Node item = null;
        Document xmlDoc = new DocumentImpl();
        Element root = xmlDoc.createElement("booking");
        item = xmlDoc.createElement("bookingID");
        item.appendChild(xmlDoc.createTextNode("115"));
        root.appendChild(item);
        xmlDoc.appendChild(root);

        try {
            Source source = new DOMSource(xmlDoc);
            File xmlFile = new File("yourFile.xml");
            StreamResult result = new StreamResult(new OutputStreamWriter(
                                  new FileOutputStream(xmlFile), "ISO-8859-1"));
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
}
