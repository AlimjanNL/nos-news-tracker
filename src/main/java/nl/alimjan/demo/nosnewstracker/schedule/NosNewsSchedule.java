package nl.alimjan.demo.nosnewstracker.schedule;

import lombok.extern.log4j.Log4j2;
import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.service.FetchNewsService;
import nl.alimjan.demo.nosnewstracker.service.ProcessNewsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Log4j2
public class NosNewsSchedule {
    FetchNewsService fetchNewsService;
    ProcessNewsService processNewsService;
    private static final String feedUrl = "https://feeds.nos.nl/jeugdjournaal?format=xml";

    public NosNewsSchedule(FetchNewsService fetchNewsService, ProcessNewsService processNewsService) {
        this.fetchNewsService = fetchNewsService;
        this.processNewsService = processNewsService;
    }

    @Scheduled(fixedRate = 300000)
    public void processNosNews() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("Fixed Rate" + dtf.format(LocalDateTime.now()));

        try {
            List<News> newsList = fetchNewsService.fetchNews(feedUrl);
            processNewsService.processFetchedNews(newsList);
        } catch (Exception e) {
            log.error("Exception message: " + e.getMessage());
        }
    }
}
