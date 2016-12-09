package smovie.movieapp.api.repos.interfaces;

import java.util.List;

import smovie.movieapp.api.ApiService;

/**
 * Data Repository interface.
 */
public interface IBaseRepository {

    interface Callback<T> {
        void onDataObserveStart();
        void onDataUpdated(T data);
        void onListDataUpdated(List<T> data);
        void onError(Throwable throwable);
    }

}
