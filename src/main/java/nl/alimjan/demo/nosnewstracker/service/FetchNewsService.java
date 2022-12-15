package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

public interface FetchNewsService {
    List<News> fetchNews(String url) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException;
}
