package smovie.movieapp.presenters;

import android.view.View;
import android.widget.EditText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import smovie.movieapp.api.pojos.MovieSearchQueryParseData;
import smovie.movieapp.repos.interfaces.ISearchMovieRepository;
import smovie.movieapp.constants.RequestParameters;
import smovie.movieapp.listeners.RecyclerItemClickListener;
import smovie.movieapp.ui.views.SearchMovieView;

/**
 * Created by sniper on 12/7/16.
 */

public class SearchMoviePresenter extends BasePresenter<SearchMovieView, ISearchMovieRepository> implements RecyclerItemClickListener.OnItemClickListener {

    public SearchMoviePresenter(SearchMovieView view, ISearchMovieRepository repository){
        super(view, repository);
    }

    public void registerSearchTextChangeListener(EditText searchEditText){

        //We can implement some logic here for the search criteria
        final String searchBy = RequestParameters.MOVIE_TITLE_SEARCH;

        final Observable<TextViewTextChangeEvent> textChangeEventObservable = RxTextView.textChangeEvents(searchEditText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .filter(textViewTextChangeEvent -> {
                    final String searchText = textViewTextChangeEvent.text().toString();
                    //if the view is not resuming and searchText has some input then proceed further
                    final boolean filter = (searchText.length() > 0 && !mView.isResuming());
                    mView.setIsResuming(false);
                    return filter;
                });
        mRepository.startSearchObserving(new ISearchMovieRepository.Callback<MovieSearchQueryParseData>(){

            @Override
            public void onDataObserveStart() {
                mView.setProgressVisibility(View.VISIBLE);
            }
            @Override
            public void onDataUpdated(MovieSearchQueryParseData data) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onMoviesResultReady(data);
            }
            @Override
            public void onListDataUpdated(List<MovieSearchQueryParseData> data) {
            }
            @Override
            public void onError(Throwable throwable) {
                mView.setProgressVisibility(View.INVISIBLE);
                mView.onRepositoryErrorOccurred(throwable);
            }
        }, textChangeEventObservable, searchBy);
    }

    @Override
    public void onItemClick(View view, int position) {
        mView.openDetailsScreen(position);
    }
}
