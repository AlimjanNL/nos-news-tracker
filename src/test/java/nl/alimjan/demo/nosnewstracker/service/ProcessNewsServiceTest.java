package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.repository.NewsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProcessNewsServiceTest {
    @Mock
    NewsRepository newsRepository;
    @InjectMocks
    ProcessNewsServiceImpl processNewsServiceImpl;

    @Test
    void testProcessFetchedNews() {
        var testNews1 = News.builder().title("Test news 1").description("Test description 1").publishedDate(OffsetDateTime.MIN).imageUrl("test_url_1").build();
        var testNews2 = News.builder().title("Test news 2").description("Test description 2").publishedDate(OffsetDateTime.MAX).imageUrl("test_url_2").build();
        var testNews3 = News.builder().title("Test news 3").description("Test description 3").publishedDate(OffsetDateTime.MAX).imageUrl("test_url_3").build();

        List<News> fetchedNews = new ArrayList<>();
        fetchedNews.add(testNews1);
        fetchedNews.add(testNews2);
        fetchedNews.add(testNews3);

        testNews1.setDescription("new test 1 description");
        Mockito.when(newsRepository.findByTitle("Test news 1")).thenReturn(Optional.of(testNews1));
        Mockito.when(newsRepository.findByTitle("Test news 2")).thenReturn(Optional.empty());
        Mockito.when(newsRepository.findByTitle("Test news 3")).thenReturn(Optional.empty());

        var result = processNewsServiceImpl.processFetchedNews(fetchedNews);

        Assertions.assertEquals(result.get("fetchedNews"), 3);
        Assertions.assertEquals(result.get("existingNews"), 1);
        Assertions.assertEquals(result.get("needUpdateNews"), 0);
        Assertions.assertEquals(result.get("needAddNews"), 2);
        Assertions.assertEquals(result.get("updatedNews"), 0);
        Assertions.assertEquals(result.get("addedNews"), 0);
    }
}