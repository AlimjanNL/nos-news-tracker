package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class FetchNewsServiceTest {
    private static final String feedUrl = "https://feeds.nos.nl/jeugdjournaal?format=xml";
    @InjectMocks
    FetchNewsServiceImpl fetchNewsServiceImpl;

    @Test
    public void testFetchNews() {
        List<News> newsList = null;
        try {
            newsList = fetchNewsServiceImpl.fetchNews(feedUrl);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertNotNull(newsList);
        Assertions.assertNotEquals(newsList.size(), 0);
    }
}