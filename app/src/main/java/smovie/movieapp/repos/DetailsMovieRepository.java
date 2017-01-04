package smovie.movieapp.repos;

import java.util.Map;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import smovie.movieapp.api.pojos.ExtendedMoveData;
import smovie.movieapp.repos.interfaces.IDetailsMovieRepository;

public class DetailsMovieRepository extends BaseRepository implements IDetailsMovieRepository {

    @Override
    public Observable<ExtendedMoveData> requestMovieDetails(Map<String, String> searchMap) {
        return getApiService().getSelectedMovieData(searchMap).observeOn(AndroidSchedulers.mainThread());
    }
}
