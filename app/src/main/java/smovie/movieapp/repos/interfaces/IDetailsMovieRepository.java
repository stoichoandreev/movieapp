package smovie.movieapp.repos.interfaces;

import java.util.Map;
import rx.Observable;
import smovie.movieapp.api.pojos.ExtendedMoveData;

/**
 * Data Repository interface about DetailsMovieView.
 * It is responsible to deliver selected movie details data
 */
public interface IDetailsMovieRepository extends IBaseRepository {
    /**
     * Get movie details data by some movie attr
     * @param searchMap - map with all search params and they values
     */
    Observable<ExtendedMoveData> requestMovieDetails(Map<String, String> searchMap);
}
