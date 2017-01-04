package smovie.movieapp.presenters;

import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import smovie.movieapp.MovieApp;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.constants.RequestParameters;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.repos.DetailsMovieRepository;
import smovie.movieapp.repos.interfaces.IDetailsMovieRepository;
import smovie.movieapp.ui.views.DetailsMovieView;

/**
 * Created by sniper on 12/7/16.
 */

public class DetailsMoviePresenter extends BasePresenter<DetailsMovieView, IDetailsMovieRepository> {

    public DetailsMoviePresenter(DetailsMovieView view, DetailsMovieRepository repository){
        super(view, repository);
    }

    public void getMovieDetailsById(){
        if(mView.getImdbId() == null){
            mView.onRepositoryErrorOccurred(new Throwable("Please provide imdbID !"));
            return;
        }
        final Map<String, String> searchMap = new HashMap<>();
        searchMap.put(RequestParameters.MOVIE_IMDB_ID_SEARCH, mView.getImdbId());
        mView.setProgressVisibility(View.VISIBLE);
        final Observable<ExtendedMoveData> observable = mRepository.requestMovieDetails(searchMap);
        addSubscription(observable.subscribe(new Subscriber<ExtendedMoveData>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onRepositoryErrorOccurred((!MovieApp.isOnline) ? new Throwable("You seems to be offline") : e);
            }

            @Override
            public void onNext(ExtendedMoveData extendedMoveData) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onMovieDetailsReady(MovieDetailsData.from(extendedMoveData));
            }
        }));
    }
}
