package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;

import java.util.List;
import java.util.Map;

public interface ProcessNewsService {
    public Map<String, Integer> processFetchedNews(List<News> newsList);
}
