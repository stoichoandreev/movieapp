package smovie.movieapp.repos;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.repos.repo_subscribers.RequestMovieDetailsSubscriber;
import smovie.movieapp.repos.interfaces.IDetailsMovieRepository;

public class DetailsMovieRepository extends BaseRepository implements IDetailsMovieRepository {

    @Override
    public void requestMovieDetails(Callback<MovieDetailsData> callback, Map<String, String> searchMap) {
        callback.onDataObserveStart();
        Observable<ExtendedMoveData> movieDetailsObservable = getApiService().getSelectedMovieData(searchMap);
        movieDetailsObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestMovieDetailsSubscriber(callback));
    }
}
