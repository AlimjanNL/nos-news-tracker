package nl.alimjan.demo.nosnewstracker.repository;

import nl.alimjan.demo.nosnewstracker.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(String title);
    List<News> findByTitleContains(String keyWord);
    List<News> findByDescriptionContains(String keyWord);
}
