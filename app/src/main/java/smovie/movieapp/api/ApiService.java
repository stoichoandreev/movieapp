package smovie.movieapp.api;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.api.pojos.MovieSearchQueryParseData;

/**
 * Retrofit Api Service interface.
 * NOTE : We have 2 diff requests because the API has only one EndPoint for all diff data requests
 * Otherwise we need some custom data converter which is not needed in the normal case.
 * The API should have diff end points
 */
public interface ApiService {
    /**
     * Get all movie matches base on the user query (by title, by artist or by something else)
     * @param queryMap - could include one or many query params
     * @return - parse object with movie matches list and some additional data, like total result and some others
     */
    @GET("?")
    Observable<MovieSearchQueryParseData> getMovieTitles(@QueryMap Map<String, String> queryMap);

    /**
     * Get all movie data by some param (imdbID)
     * @param queryMap - could include one or many query params (initially will be by imdbID)
     * @return - the movie data
     */
    @GET("?")
    Observable<ExtendedMoveData> getSelectedMovieData(@QueryMap Map<String, String> queryMap);
}
