package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import com.learnjava.util.CommonUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MoviesClient moviesClient = new MoviesClient(webClient);
    private List<Long> movieInfoIdList;

    @Test
    void retrieveMovie() {
        stopWatchReset();
        startTimer();

        var movieInfoId = 1L;
        Movie movie = moviesClient.retrieveMovie(movieInfoId);

        timeTaken();
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @Test
    void retrieveMovieCF() {
        var movieInfoId = 1L;
        stopWatchReset();
        startTimer();

        var movieCF = moviesClient.retrieveMovieCF(movieInfoId).join();

        timeTaken();
        assertEquals("Batman Begins", movieCF.getMovieInfo().getName());
        assert movieCF.getReviewList().size() == 1;
    }

    @Test
    void retrieveMoviesList() {
        stopWatchReset();
        startTimer();

        var movieInfoIdList = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        var movies = moviesClient.retrieveMoviesList(movieInfoIdList);

        timeTaken();
        assert movies.size() == 7;
    }

    @Test
    void retrieveMovieListCF() {
        stopWatchReset();
        startTimer();

        var movieInfoIdList = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        var movies = moviesClient.retrieveMovieListCF(movieInfoIdList);

        timeTaken();
        assert movies.size() == 7;
    }

    @Test
    void retrieveMovieListCF_AllOff() {
        var movieInfoIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);

        //when
        CommonUtil.startTimer();
        var movies = moviesClient.retrieveMovieListCF_AllOff(movieInfoIds);

        System.out.println("movies : " + movies);
        CommonUtil.timeTaken();
        //then
        assert movies.size() == 7;
    }
}