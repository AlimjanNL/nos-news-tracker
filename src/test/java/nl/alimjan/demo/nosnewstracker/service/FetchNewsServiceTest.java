package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.xml.sax.SAXException;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FetchNewsServiceTest {
    private static final String feedUrl = "https://feeds.nos.nl/jeugdjournaal?format=xml";
    @InjectMocks
    FetchNewsServiceImpl fetchNewsServiceImpl;

    @Test
    public void testFetchNews() throws XPathExpressionException, IOException, SAXException {
        List<News> newsList = fetchNewsServiceImpl.fetchNews(feedUrl);
        Assertions.assertNotNull(newsList);
        Assertions.assertNotEquals(newsList.size(), 0);
    }
}