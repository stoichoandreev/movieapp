package smovie.movieapp.api.repos;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import smovie.movieapp.MovieApp;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.api.repos.interfaces.IDetailsMovieRepository;
import smovie.movieapp.models.MovieDetailsData;

public class DetailsMovieRepository extends BaseRepository implements IDetailsMovieRepository {

    @Override
    public void requestMovieDetails(Callback<MovieDetailsData> callback, Map<String, String> searchMap) {
        callback.onDataObserveStart();
        Observable<ExtendedMoveData> movieDetailsObservable = getApiService().getSelectedMovieData(searchMap);
        movieDetailsObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ExtendedMoveData>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        callback.onError((!MovieApp.isOnline) ? new Throwable("You seems to be offline") : e);
                    }

                    @Override
                    public void onNext(ExtendedMoveData parseObject) {
                        callback.onDataUpdated(MovieDetailsData.from(parseObject));
                    }
                });
    }
}
