package nl.alimjan.demo.nosnewstracker.controller;

import lombok.RequiredArgsConstructor;
import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.service.NewsService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @QueryMapping
    List<News> news() {
        return newsService.findAll();
    }

    @QueryMapping
    Optional<News> newsById(@Argument Long id) {
        return newsService.findById(id);
    }

    @QueryMapping
    List<News> newsByTitle(@Argument String keyWord) {
        return newsService.findByTitleContains(keyWord);
    }

    @QueryMapping
    List<News> newsByDescription(@Argument String keyWord) {
        return newsService.findByDescriptionContains(keyWord);
    }
}
