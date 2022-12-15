package nl.alimjan.demo.nosnewstracker.controller;

import nl.alimjan.demo.nosnewstracker.model.News;
import nl.alimjan.demo.nosnewstracker.service.NewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(NewsController.class)
class NewsControllerTest {
    @MockBean
    NewsServiceImpl newsService;
    @Autowired
    GraphQlTester graphQlTester;

    @Test
    void testNews() {
        // language=GraphQL
        String doc = """
                query {
                news{
                  id
                  title
                  description
                }
                }
                """;
        graphQlTester.document(doc).execute().path("news").entityList(News.class);
    }

//    @Test
//    void testNewsById() {
//        // language=GraphQL
//        String doc = """
//                query {
//                newsById(id: 1){
//                  title
//                  description
//                }
//                }
//                """;
//        graphQlTester.document(doc)
//                .variable("id", 1)
//                .execute()
//                .path("newsById")
//                .entity(News.class);
//    }

    @Test
    void testNewsByTitle() {
        // language=GraphQL
        String doc = """
                query {
                newsByTitle(keyWord: "De 9"){
                  title
                  description
                }
                }
                """;
        graphQlTester.document(doc).variable("keyWord", "De 9").execute().path("newsByTitle").entityList(News.class);
    }

    @Test
    void testNewsByDescription() {
        // language=GraphQL
        String doc = """
                query {
                newsByDescription(keyWord: "In New York"){
                  title
                  description
                }
                }
                """;
        graphQlTester.document(doc).variable("keyWord", "In New York").execute().path("newsByDescription").entityList(News.class);
    }
}