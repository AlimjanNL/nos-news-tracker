package nl.alimjan.demo.nosnewstracker.service;

import lombok.extern.log4j.Log4j2;
import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.util.NodeListIterator;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Log4j2
public class FetchNewsServiceImpl implements FetchNewsService {
    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    XPath xPath;

    public FetchNewsServiceImpl() throws ParserConfigurationException {
        docFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docFactory.newDocumentBuilder();
        xPath = XPathFactory.newInstance().newXPath();
    }

    @Override
    public List<News> fetchNews(String url) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
        Document doc = docBuilder.parse(new URL(url).openStream());

        String itemListPath = "/rss/channel/item";

        NodeList nodeList = (NodeList) xPath.compile(itemListPath).evaluate(doc, XPathConstants.NODESET);

        NodeListIterator nodeListIterator = new NodeListIterator(nodeList);

        List<News> newsList = new ArrayList<>();

        while (nodeListIterator.hasNext()) {
            Node node = nodeListIterator.next();
            Element element = (Element) node;
            String title = element.getElementsByTagName("title").item(0).getTextContent();
            String description = element.getElementsByTagName("description").item(0).getTextContent();

            String imageUrl = element.getElementsByTagName("enclosure").item(0).getAttributes().getNamedItem("url").getTextContent();

            String publishedDateString = element.getElementsByTagName("pubDate").item(0).getTextContent();
            OffsetDateTime publishedDate = OffsetDateTime.parse(publishedDateString, DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss X", Locale.US));

            News news = News.builder()
                    .title(title)
                    .description(description)
                    .imageUrl(imageUrl)
                    .publishedDate(publishedDate)
                    .build();

            newsList.add(news);

//            log.info("\nTitle: " + title + "\nDescription: " + description + "\nImageUrl: " + imageUrl + "\nPublished Date: " + publishedDate + "\n====================");
        }

        return newsList;
    }
}
