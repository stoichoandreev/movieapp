package smovie.movieapp.api.repos;

import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import smovie.movieapp.MovieApp;
import smovie.movieapp.api.pojos.MovieSearchQueryParseData;
import smovie.movieapp.api.repos.interfaces.ISearchMovieRepository;

public class SearchMovieRepository extends BaseRepository implements ISearchMovieRepository {

    @Override
    public void startSearchObserving(Callback<MovieSearchQueryParseData> callback, Observable<TextViewTextChangeEvent> textChangeEventObservable, String searchBy) {
        final Map<String, String> searchByMap = new HashMap<>();
        textChangeEventObservable.map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .switchMap(new Func1<String, Observable<MovieSearchQueryParseData>>() {
                    @Override
                    public Observable<MovieSearchQueryParseData> call(String s) {
                        //Make request to get data
                        searchByMap.put(searchBy, s);
                        callback.onDataObserveStart();//send callback to display the progress
                        return mApiService.getMovieTitles(searchByMap).onErrorResumeNext(Observable.empty());//subscription will be terminated once an error is emitted, we should avoid that
                    }
                })
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRxSearchObserver(callback));
    }
    private Observer<MovieSearchQueryParseData> getRxSearchObserver(Callback<MovieSearchQueryParseData> callback) {
        return new Observer<MovieSearchQueryParseData>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                callback.onError((!MovieApp.isOnline) ? new Throwable("You seems to be offline") : e);
            }

            @Override
            public void onNext(MovieSearchQueryParseData parseData) {
                callback.onDataUpdated(parseData);
            }
        };
    }
}
