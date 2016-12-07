package smovie.movieapp.ui.views;

/**
 * Created by sniper on 12/6/16.
 */

public interface BaseView {
    void onRepositoryErrorOccurred(Throwable error);
    void setProgressVisibility(int visibility);
}
