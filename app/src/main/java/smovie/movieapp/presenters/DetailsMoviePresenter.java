package smovie.movieapp.presenters;

import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smovie.movieapp.api.repos.interfaces.IDetailsMovieRepository;
import smovie.movieapp.constants.RequestParameters;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.ui.views.DetailsMovieView;

/**
 * Created by sniper on 12/7/16.
 */

public class DetailsMoviePresenter extends BasePresenter<DetailsMovieView, IDetailsMovieRepository> {

    public DetailsMoviePresenter(DetailsMovieView view, IDetailsMovieRepository repository){
        super(view, repository);
    }

    public void getMovieDetailsById(String imdbId){
        if(imdbId == null){
            mView.onRepositoryErrorOccurred(new Throwable("Please provide imdbID !"));
            return;
        }
        final Map<String, String> searchMap = new HashMap<>();
        searchMap.put(RequestParameters.MOVIE_IMDB_ID_SEARCH, imdbId);
        mRepository.requestMovieDetails(new IDetailsMovieRepository.Callback<MovieDetailsData>(){
            @Override
            public void onDataObserveStart() {
                mView.setProgressVisibility(View.VISIBLE);
            }
            @Override
            public void onDataUpdated(MovieDetailsData data) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onMovieDetailsReady(data);
            }
            @Override
            public void onListDataUpdated(List<MovieDetailsData> data) {
            }
            @Override
            public void onError(Throwable throwable) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onRepositoryErrorOccurred(throwable);
            }
        }, searchMap);
    }
}
