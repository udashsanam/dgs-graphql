package com.learn.dgsgraphql.datafetcher;

import com.learn.dgsgraphql.codegen.types.SearchFilter;
import com.learn.dgsgraphql.codegen.types.Show;
import com.learn.dgsgraphql.codegen.types.ShowCategory;
import com.learn.dgsgraphql.repository.ShowRepository;
import com.learn.dgsgraphql.service.ArtWorkService;
import com.netflix.graphql.dgs.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class LolomoDatafetcher {

    private final ShowRepository showRepository;
    private final ArtWorkService artWorkService;

    public LolomoDatafetcher(ShowRepository showRepository,
                             ArtWorkService artWorkService) {
        this.showRepository = showRepository;
        this.artWorkService = artWorkService;
    }

    @DgsQuery
    public List<ShowCategory> loloma(){
        return List.of(ShowCategory.newBuilder().id(1).name("Top 10").shows(showRepository.showForCategory(1)).build(),
                ShowCategory.newBuilder().id(1).name("Continue Watching").shows(showRepository.showForCategory(2)).build());
    }

    @DgsData(parentType = "Show")
    public String artworkUrl(DgsDataFetchingEnvironment dfe){
        Show show = dfe.getSource();
        return artWorkService.generateForTitle(show.getTitle());
    }

    @DgsQuery
    public List<Show> search(@InputArgument SearchFilter searchFilter){
        return showRepository.allShows().stream().filter(show -> show.getTitle().toLowerCase().startsWith(searchFilter.getTitle().toLowerCase()))
                .toList();
    }


}
