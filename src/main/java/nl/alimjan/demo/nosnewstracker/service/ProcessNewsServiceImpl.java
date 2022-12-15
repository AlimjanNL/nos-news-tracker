package nl.alimjan.demo.nosnewstracker.service;

import lombok.extern.log4j.Log4j2;
import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log4j2
public class ProcessNewsServiceImpl implements ProcessNewsService {
    private final NewsRepository newsRepository;

    public ProcessNewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Map<String, Integer> processFetchedNews(List<News> fetchedNews) {
        var existingNews = fetchedNews.stream()
                .map(news -> new AbstractMap.SimpleEntry<>(news, newsRepository.findByTitle(news.getTitle())))
                .filter(entry -> entry.getValue().isPresent())
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().get()))
                .toList();

        var needUpdateNews = existingNews.stream()
                .filter(entry -> entry.getKey().getPublishedDate().equals(entry.getValue().getUpdateDate()))
                .filter(entry -> entry.getKey().getDescription().equals(entry.getValue().getDescription()))
                .peek(entry -> entry.getKey().setId(entry.getValue().getId()))
                .map(AbstractMap.SimpleEntry::getKey)
                .toList();

        var newNews = fetchedNews.stream()
                .filter(news -> !existingNews.stream().map(entry -> entry.getValue().getTitle()).toList().contains(news.getTitle()))
                .toList();

        var updatedNews = newsRepository.saveAllAndFlush(needUpdateNews);
        var addedNews = newsRepository.saveAllAndFlush(newNews);

        log.info("\nFetched: {} news.\nExisting {} news.\nNeedUpdate {} news.\nNeedAdd {} news.\nUpdated {} news.\nAdded {} news.",
                fetchedNews.size(), existingNews.size(), needUpdateNews.size(), newNews.size(), updatedNews.size(), addedNews.size());

        return Map.ofEntries(
                Map.entry("fetchedNews", fetchedNews.size()),
                Map.entry("existingNews", existingNews.size()),
                Map.entry("needUpdateNews", needUpdateNews.size()),
                Map.entry("needAddNews", newNews.size()),
                Map.entry("updatedNews", updatedNews.size()),
                Map.entry("addedNews", addedNews.size())
        );
    }
}
