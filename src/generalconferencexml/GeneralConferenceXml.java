import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


/**
 * General Conference Xml parser that spits out speaker names from an xml file.
 * Assignment instructions https://content.byui.edu/file/293bd79c-74bc-4a59-95e4-3b064557ad86/1/teach/246-03-team-activity.html
 * Github Repo: ??
 *
 * @author Stephen Nielson smnche6
 * @author John Okleberry
 * @author Keith Higbee
 */
public class GeneralConferenceXml {

  /**
   * Reads in the general conference xml file and prints the speaker names.
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    // Open up the file
    File file = new File("246-03-conference.xml");
    if (!file.exists()) {
      // print error message
      System.err.println("File not found at " + file.getAbsolutePath() + " cannot list speakers");
      return;
    }

    // handle any errors with that

    // build DocumentBuilderFactory to load the xml file
    DocumentBuilder builder;
    try {
      builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    } catch (ParserConfigurationException ex) {
      System.out.println("Could not build document builder factory... "
          + "This should never happen....");
      ex.printStackTrace();
      return;
    }

    // then we need to grab the list of speaker nodes
    // https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilderFactory.html
    // https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html

    Document doc;
    try {
      doc = builder.parse(file);
    } catch (SAXException | IOException ex) {
      // TODO: stephen do something here.
      System.out.println("Could not parse the XML file");
      ex.printStackTrace();
      return; // do something
    }

    // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Document.html#getElementsByTagName(java.lang.String)
    NodeList nodes = doc.getElementsByTagName("speaker");
    for (int i = 0; i < nodes.getLength(); i++) {
      Node node = nodes.item(i);
      if (node instanceof Element) {
        Element el = (Element) node;
        String name = el.getAttribute("name");
        // then just print out each speaker
        System.out.println(name);
      }
    }
  }

}
