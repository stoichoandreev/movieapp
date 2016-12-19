package smovie.movieapp.repos.interfaces;

import java.util.Map;

import smovie.movieapp.models.MovieDetailsData;

/**
 * Data Repository interface about DetailsMovieView.
 * It is responsible to deliver selected movie details data
 */
public interface IDetailsMovieRepository extends IBaseRepository {
    /**
     * Get movie details data by some movie attr
     * @param callback - the callback waiting for the data response
     * @param searchMap - map with all search params and they values
     */
    void requestMovieDetails(Callback<MovieDetailsData> callback, Map<String, String> searchMap);
}
