package smovie.movieapp.ui.views;

import smovie.movieapp.models.MovieDetailsData;

/**
 * Created by sniper on 12/7/16.
 */

public interface DetailsMovieView extends BaseView{
    void onMovieDetailsReady(MovieDetailsData movieDetails);
}
