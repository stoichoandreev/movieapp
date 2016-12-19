package smovie.movieapp.presenters;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.repos.SearchMovieRepository;
import smovie.movieapp.repos.interfaces.IBaseRepository;
import smovie.movieapp.repos.interfaces.ISearchMovieRepository;
import smovie.movieapp.ui.views.SearchMovieView;

import static org.mockito.Mockito.verify;

/**
 * Created by sniper on 19-Dec-2016.
 */
public class SearchMoviePresenterTest {
    @Mock
    private SearchMovieRepository searchMovieRepository;
    @Mock
    private SearchMovieView searchMovieView;
    @Captor
    ArgumentCaptor<IBaseRepository.Callback<MovieDetailsData>> repositoryCallbackCollaborator;
    @Mock
    private ISearchMovieRepository.Callback<MovieDetailsData> callback;
    private SearchMoviePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchMoviePresenter(searchMovieView, searchMovieRepository);
    }

    /**
     * Test does Presenter onItemClick method is calling view method to open Movie Details Screen on position 1
     * @throws Exception
     */
    @Test
    public void shouldMovieClickOpenMovieDetailsOnPosition() throws Exception {
        presenter.onItemClick(Mockito.mock(View.class), 1);
        verify(searchMovieView).openDetailsScreen(1);
    }
    /**
     * Test does Presenter is registering the search text queries
     * @throws Exception
     */
    @Test
    public void shouldRegisterSearchTextChangeEventsListener() throws Exception {

    }
}