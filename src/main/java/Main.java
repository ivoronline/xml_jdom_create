import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

    //CREATE DOCUMENT BUILDER
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder        builder = factory.newDocumentBuilder();

    //CREATE NEW DOCUMENT
    Document               document = builder.newDocument();

    //CREATE <person> ---------------------------------------------------------------------------------------

    //CREATE <name>
    Element                name1 = document.createElement("name");
                           name1.appendChild(document.createTextNode("John"));

    //CREATE <age>
    Element                age1 = document.createElement("age");
                           age1.appendChild(document.createTextNode("20"));

    //CREATE <person>
    Attr                   id1 = document.createAttribute("id");
                           id1.setValue("1");

    Element                person1 = document.createElement("person");
                           person1.setAttributeNode(id1);
                           person1.appendChild(name1);
                           person1.appendChild(age1);

    //CREATE <people> ---------------------------------------------------------------------------------------
    Element                people = document.createElement("people");
                           document.appendChild(people);
                           people.appendChild(person1);

    //WRITE XML ---------------------------------------------------------------------------------------------

    //PREPARE TO WRITE
    DOMSource             source = new DOMSource(document);
    TransformerFactory    transformerFactory = TransformerFactory.newInstance();
    Transformer           transformer = transformerFactory.newTransformer();

    //WRITE TO XML FILE
    StreamResult          result = new StreamResult(new File("Test.xml"));
                          transformer.transform(source, result);

    //WRITE TO CONSOLE
    StreamResult          consoleResult = new StreamResult(System.out);
                          transformer.transform(source, consoleResult);

  }

}
