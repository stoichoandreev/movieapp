package smovie.movieapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import smovie.movieapp.R;
import smovie.movieapp.adapter.MovieAdapter;
import smovie.movieapp.api.pojos.MovieData;
import smovie.movieapp.api.pojos.MovieSearchQueryParseData;
import smovie.movieapp.api.repos.SearchMovieRepository;
import smovie.movieapp.databinding.FragmentMovieSearchBinding;
import smovie.movieapp.enums.FragmentAction;
import smovie.movieapp.enums.ToolbarAction;
import smovie.movieapp.listeners.RecyclerItemClickListener;
import smovie.movieapp.presenters.SearchMoviePresenter;
import smovie.movieapp.ui.views.SearchMovieView;

/**
 * Created by sniper on 12/6/16.
 * This screen could be extended if we make paging implementation into the recyclerview. When user scroll the list and scroll goes to the end (or closer to the end)
 * then we can make request to get the next page from the list.
 */
public class SearchMovieFragment extends BaseFragment implements SearchMovieView {

    private FragmentMovieSearchBinding fragmentBinding;
    private MovieAdapter movieRecyclerAdapter;
    private List<MovieData> foundMoviesList;
    private boolean isResuming;

    @UiThread
    public static SearchMovieFragment newInstance(@Nullable Bundle args) {
        SearchMovieFragment fragment = new SearchMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SearchMoviePresenter(this, new SearchMovieRepository());
    }

    @Override //No implementation need for now
    protected void initDataInstances(Bundle savedInstanceState) {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_search, container, false);
        fragmentBinding.searchResultsRv.setLayoutManager(new LinearLayoutManager(mActivity));
        fragmentBinding.searchResultsRv.addOnItemTouchListener(new RecyclerItemClickListener(mActivity, (SearchMoviePresenter)mPresenter));

        movieRecyclerAdapter = new MovieAdapter(foundMoviesList != null ? foundMoviesList : new ArrayList<>(0));
        fragmentBinding.searchResultsRv.setAdapter(movieRecyclerAdapter);

        ((SearchMoviePresenter)mPresenter).registerSearchTextChangeListener(fragmentBinding.searchInputView);

        return fragmentBinding.getRoot();
    }

    @Override
    public void openDetailsScreen(int position) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsMovieFragment.MOVIE_DATA, movieRecyclerAdapter.getMovie(position));
        mActivity.replaceFragment(DetailsMovieFragment.newInstance(bundle), true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setToolbarAction(ToolbarAction.ACTION_SET_TITLE, getStringResourceSafety(R.string.search_title));
        mActivity.setToolbarAction(ToolbarAction.ACTION_REMOVE_BACK_TO_PREVIOUS_SCREEN);
    }

    @Override
    public void onPause() {
        super.onPause();
        isResuming = true;
    }

    @Override
    public void notifyFragmentAboutAction(FragmentAction fragmentAction) {
        switch (fragmentAction) {
            case ACTION_CONNECTIVITY_CHANGE:
                //Can Implement something here if connection change (present diff look and feel)
                break;
        }
    }

    @Override
    public void onMoviesResultReady(MovieSearchQueryParseData moviesSearchResult) {
        foundMoviesList = moviesSearchResult.getMovieList();
        movieRecyclerAdapter.setMovies(foundMoviesList);
    }

    @Override
    public void setIsResuming(boolean isResume) {
        this.isResuming = isResume;
    }

    @Override
    public boolean isResuming() {
        return isResuming;
    }

    @Override
    public void onRepositoryErrorOccurred(Throwable error) {
        Snackbar.make(getView(),error.getMessage(),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setProgressVisibility(int visibility) {
        mActivity.runOnUiThread(() -> {
            fragmentBinding.searchProgressBar.setVisibility(visibility);//simple progress bar can't be stop, but we can hide it
        });
    }
}
