package com.learn.dgsgraphql.datafetcher;

import com.learn.dgsgraphql.codegen.types.ShowCategory;
import com.learn.dgsgraphql.repository.ShowRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;

@DgsComponent
public class LolomoDatafetcher {

    private final ShowRepository showRepository;

    public LolomoDatafetcher(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @DgsQuery
    public List<ShowCategory> loloma(){
        return List.of(ShowCategory.newBuilder().id(1).name("Top 10").shows(showRepository.showForCategory(1)).build(),
                ShowCategory.newBuilder().id(1).name("Continue Watching").shows(showRepository.showForCategory(2)).build());
    }
}
