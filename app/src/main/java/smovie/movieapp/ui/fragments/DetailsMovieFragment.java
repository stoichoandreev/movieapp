package smovie.movieapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smovie.movieapp.R;
import smovie.movieapp.api.pojos.MovieData;
import smovie.movieapp.repos.DetailsMovieRepository;
import smovie.movieapp.databinding.FragmentMovieDetailsBinding;
import smovie.movieapp.enums.FragmentAction;
import smovie.movieapp.enums.ToolbarAction;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.presenters.DetailsMoviePresenter;
import smovie.movieapp.ui.views.DetailsMovieView;

/**
 * Created by sniper on 12/6/16.
 * This Screen is cover the case when user enter on the screen without internet connection
 * The screen will resume automatically data load if error happen with the request
 *
 */
public class DetailsMovieFragment extends BaseFragment implements DetailsMovieView{

    public static final String MOVIE_DATA = "movie_data";

    private FragmentMovieDetailsBinding fragmentBinding;
    private MovieData movieData;
    private boolean doesDataLoadErrorHappens;

    @UiThread
    public static DetailsMovieFragment newInstance(@Nullable Bundle args) {
        DetailsMovieFragment fragment = new DetailsMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DetailsMoviePresenter(this, new DetailsMovieRepository());
        movieData = getArguments() != null ? (MovieData) getArguments().getSerializable(MOVIE_DATA) : new MovieData();
    }

    @Override
    protected void initDataInstances(Bundle savedInstanceState) {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        ((DetailsMoviePresenter)mPresenter).getMovieDetailsById();

        return fragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity.setToolbarAction(ToolbarAction.ACTION_SET_TITLE, getStringResourceSafety(R.string.details_title));
        mActivity.setToolbarAction(ToolbarAction.ACTION_BACK_TO_PREVIOUS_SCREEN);
    }

    @Override
    public void notifyFragmentAboutAction(FragmentAction fragmentAction) {
        switch (fragmentAction) {
            case ACTION_CONNECTIVITY_CHANGE:
                if(doesDataLoadErrorHappens){
                    doesDataLoadErrorHappens = false;
                    ((DetailsMoviePresenter)mPresenter).getMovieDetailsById();
                }
                break;
        }
    }

    @Override
    public void onMovieDetailsReady(MovieDetailsData movieDetails) {
        fragmentBinding.setMovieDetails(movieDetails);
    }

    @Override
    public String getImdbId() {
        return movieData.getImdbID();
    }

    @Override
    public void onRepositoryErrorOccurred(Throwable error) {
        Snackbar.make(fragmentBinding.loadingView,error.getMessage(),Snackbar.LENGTH_LONG).show();
        fragmentBinding.setMovieDetails(new MovieDetailsData.Builder().build());//this will init all views with default values, otherwise they will stay empty
        doesDataLoadErrorHappens = true;
    }

    @Override
    public void setProgressVisibility(int visibility) {
        fragmentBinding.loadingView.setVisibility(visibility);
        switch (visibility){
            case View.VISIBLE:
                fragmentBinding.detailsContainer.setVisibility(View.GONE);
                break;
            case View.GONE:
            case View.INVISIBLE:
                fragmentBinding.detailsContainer.setVisibility(View.VISIBLE);
                break;
        }
    }
}
