package smovie.movieapp.repos.repo_subscribers;

import rx.Subscriber;
import smovie.movieapp.MovieApp;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.repos.interfaces.IBaseRepository;

/**
 * Created by sniper on 18-Dec-2016.
 * Subscriber for requesting movie details
 * It is in separate class because it is easier for test
 */
public class RequestMovieDetailsSubscriber extends Subscriber<ExtendedMoveData> {

    private IBaseRepository.Callback<MovieDetailsData> callback;

    public RequestMovieDetailsSubscriber(IBaseRepository.Callback<MovieDetailsData> callback) {
        super();
        this.callback = callback;
    }

    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {
        callback.onError((!MovieApp.isOnline) ? new Throwable("You seems to be offline") : e);
    }

    @Override
    public void onNext(ExtendedMoveData extendedMoveData) {
        callback.onDataUpdated(MovieDetailsData.from(extendedMoveData));
    }
}
