package com.learn.dgsgraphql.repository;

import com.learn.dgsgraphql.codegen.types.Show;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowRepository {


    private final static List<Show> shows = List.of(
            Show.newBuilder().title("The Witcher").build(),
            Show.newBuilder().title("Wednesday").build(),
            Show.newBuilder().title("Sweet To0th").build(),
            Show.newBuilder().title("Black Mirror").build(),
            Show.newBuilder().title("Sex Education").build(),
            Show.newBuilder().title("Manifest").build(),
            Show.newBuilder().title("Love is Blind").build(),
            Show.newBuilder().title("You").build(),
            Show.newBuilder().title("The Last Dance").build()
    );

    public List<Show> showForCategory(int id) {
       return switch (id) { case 1 -> shows;
        case 2 ->  List.of(shows.get(8), shows.get(7), shows.get(6), shows.get(5));
        default ->   List.of();
       };
    }

    public List<Show> allShows() {
        return shows;
    }
}
