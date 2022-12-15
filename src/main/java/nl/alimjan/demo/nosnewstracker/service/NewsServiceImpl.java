package nl.alimjan.demo.nosnewstracker.service;

import lombok.RequiredArgsConstructor;
import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> findByTitleContains(String keyWord) {
        return newsRepository.findByTitleContains(keyWord);
    }

    @Override
    public List<News> findByDescriptionContains(String keyWord) {
        return newsRepository.findByDescriptionContains(keyWord);
    }
}
