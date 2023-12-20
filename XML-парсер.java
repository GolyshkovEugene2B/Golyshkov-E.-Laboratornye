import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class XmlParser {

    private static final String TAG_NAME = "name";
    private static final String PARAM_NAME = "paramName";
    private static final Pattern TAG_PARAM_PATTERN = Pattern.compile("<(\\w+)\\s+(\\w*)\\s*/>");

    // Класс SAX-обработчика, наследующий класс DefaultHandler
    private class MyHandler extends DefaultHandler {
private List<DefaultMutableTreeNode> nodes = new ArrayList<>();
private StringBuilder currentTagName = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        // Проверяем, содержит ли тег параметры
        Matcher matcher = TAG_PARAM_PATTERN.matcher(qName);
        if (matcher.find()) {
            String tagName = matcher.group(1);
            String paramName = matcher.group(2);
            currentTagName.append(tagName).append(" ");
            if (paramName.isEmpty()) {
                nodes.add(new DefaultMutableTreeNode(currentTagName.toString()));
            } else {
                String[] params = paramName.split(" ");
                for (String param : params) {
                    nodes.add(new DefaultMutableTreeNode(" " + param));
                }
            }
        } else { // Если тег не содержит параметров
            nodes.add(new DefaultMutableTreeNode(qName));
        }
    }

    @Override
      public void endElement(String uri, String localName, String qName) {
currentTagName = currentTagName.delete(currentTagName.length() - 1, currentTagName.length());
}
}

/**
 * Метод парсит XML-файл и выводит его содержимое в виде дерева в компоненте Swing JTree.
 * @param filePath Путь к XML-файлу.
 */
public void parseXmlFile(String filePath) {
    try {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();

        MyHandler myHandler = new MyHandler();
        xr.setContentHandler(myHandler);

        InputStream inputStream = XmlParser.class.getResourceAsStream(filePath);
        InputSource is = new InputSource(inputStream);
        is.setSystemId(filePath);
        xr.parse(is);

        DefaultMutableTreeNode root = myHandler.nodes.get(0);
      JTree tree = new JTree(root);
tree.expandPath(new TreePath(root.getPath()));
} catch (Exception e) {
e.printStackTrace();
}
}

}
