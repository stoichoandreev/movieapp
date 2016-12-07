package smovie.movieapp.api.repos;

import smovie.movieapp.api.ApiService;
import smovie.movieapp.api.RetrofitServiceProvider;

/**
 * Created by sniper on 12/7/16.
 */

public class BaseRepository {
    protected ApiService mApiService = RetrofitServiceProvider.getApiServiceInstance();
}
