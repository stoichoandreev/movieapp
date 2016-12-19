package smovie.movieapp.repos.interfaces;

import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;
import smovie.movieapp.api.pojos.MovieSearchQueryParseData;

/**
 * Data Repository interface about SearchMovieView.
 * It is responsible to deliver movies list by search criteria
 */
public interface ISearchMovieRepository extends IBaseRepository {
    /**
     * Register search field text change events
     */
    void startSearchObserving(Callback<MovieSearchQueryParseData> callback, Observable<TextViewTextChangeEvent> textChangeEventObservable, String searchBy);
}
