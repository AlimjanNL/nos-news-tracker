package nl.alimjan.demo.nosnewstracker.service;

import nl.alimjan.demo.nosnewstracker.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    public List<News> findAll();

    public Optional<News> findById(Long id);

    public List<News> findByTitleContains(String keyWord);

    public List<News> findByDescriptionContains(String keyWord);

}
