package smovie.movieapp.repos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import rx.Observable;
import rx.observers.TestSubscriber;
import smovie.movieapp.api.pojos.ExtendedMoveData;

/**
 * Created by sniper on 19-Dec-2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsMovieRepositoryTest {

    private DetailsMovieRepository detailsMovieRepository;

    @Mock
    private Map<String, String> searchMap;
    @Mock
    private ExtendedMoveData data;
    @Mock
    private TestSubscriber<ExtendedMoveData> testSubscriber;

    @Before
    public void setUp() throws Exception {
        detailsMovieRepository = new DetailsMovieRepository();
    }

    @Test
    public void shouldSuccessWhenRequestMovieDetails() throws Exception {
        Observable<ExtendedMoveData> movieDetailsObservable = Observable.just(data);//replace our long taking observable with a mocked
        movieDetailsObservable.subscribe(testSubscriber);
        detailsMovieRepository.requestMovieDetails(searchMap);
        testSubscriber.assertNoErrors();//no errors
        testSubscriber.assertValue(data);//subscriber has same value from the observable
        testSubscriber.assertCompleted();//subscriber completed
    }
//    /**
//     * Test does subscriber return in onNext the right view model data (MovieDetailsData.class)
//     * @throws Exception
//     */
//    @Test
//    public void shouldReturnMovieDetailsViewModelDataToPresenterFromSubscriberNext() throws Exception {
//        RequestMovieDetailsSubscriber requestMovieDetailsSubscriber = new RequestMovieDetailsSubscriber(callback);
//        requestMovieDetailsSubscriber.onNext(data);
//        verify(callback, times(1)).onDataUpdated(isA(MovieDetailsData.class));
//    }
//
//    /**
//     * Test does subscriber return error to the presenter (with the callback)
//     * @throws Exception
//     */
//    @Test
//    public void shouldReturnThrowableErrorToPresenter() throws Exception {
//        RequestMovieDetailsSubscriber requestMovieDetailsSubscriber = new RequestMovieDetailsSubscriber(callback);
//        Throwable error = Mockito.mock(Throwable.class);
//        requestMovieDetailsSubscriber.onError(error);
//        verify(callback, times(1)).onError(error);
//    }
}