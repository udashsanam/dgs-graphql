package com.learn.dgsgraphql.datafetcher;

import com.learn.dgsgraphql.repository.ShowRepository;
import com.learn.dgsgraphql.service.ArtWorkService;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.test.EnableDgsTest;
import graphql.ExecutionResult;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.ExecutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {LolomoDatafetcher.class, ArtWorkService.class, ShowRepository.class})
@EnableDgsTest
class LolomoDatafetcherTest {

    @Autowired
    DgsQueryExecutor  dgsQueryExecutor;
    @Test
    void search() {
        @Language("GraphQL")
        var query = """
                query Search {
                                           search(searchFilter: { title: "the" }) {
                                               title
                
                                           }
                                       }
                """;
//       ExecutionResult result = dgsQueryExecutor.execute(query);
        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(query, "data.search[*].title");
    assertThat(titles).containsExactly("The Witcher", "The Last Dance");
    }
}