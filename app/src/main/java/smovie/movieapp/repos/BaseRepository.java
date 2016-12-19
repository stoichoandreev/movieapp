package smovie.movieapp.repos;

import smovie.movieapp.api.ApiService;
import smovie.movieapp.api.RetrofitServiceProvider;

/**
 * Created by sniper on 12/7/16.
 */

public abstract class BaseRepository {
    private ApiService mApiService = RetrofitServiceProvider.getApiServiceInstance();

    public ApiService getApiService() {
        return mApiService;
    }
}
