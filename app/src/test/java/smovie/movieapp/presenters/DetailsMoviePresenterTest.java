package smovie.movieapp.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Map;
import smovie.movieapp.repos.DetailsMovieRepository;
import smovie.movieapp.repos.interfaces.IBaseRepository;
import smovie.movieapp.repos.interfaces.IDetailsMovieRepository;
import smovie.movieapp.models.MovieDetailsData;
import smovie.movieapp.ui.views.DetailsMovieView;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sniper on 12/8/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsMoviePresenterTest {

    private static final String IMDB_MOCK_ID = "some_imdbID_here";

    @Mock
    private DetailsMovieRepository detailsMovieRepository;
    @Mock
    private DetailsMovieView detailsMovieView;
    @Captor
    ArgumentCaptor<IBaseRepository.Callback<MovieDetailsData>> repositoryCallbackCollaborator;
    @Mock
    private IDetailsMovieRepository.Callback<MovieDetailsData> callback;
    private DetailsMoviePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailsMoviePresenter(detailsMovieView, detailsMovieRepository);
    }

    /**
     * Test the view error callback for invalid imdbID
     * @throws Exception
     */
    @Test
    public void shouldShowErrorMessageIfImdbIdDoesNotExist() throws Exception {
        when(detailsMovieView.getImdbId()).thenReturn(null);
        presenter.getMovieDetailsById();
        verify(detailsMovieView).onRepositoryErrorOccurred(isA(Throwable.class));
    }
    /**
     * Test that repository receive map with the params
     * @throws Exception
     */
    @Test
    public void doesMovieDetailsRequestReceiveMapParams() throws Exception {
        when(detailsMovieView.getImdbId()).thenReturn(IMDB_MOCK_ID);
        presenter.getMovieDetailsById();
        verify(detailsMovieRepository).requestMovieDetails(repositoryCallbackCollaborator.capture(), isA(Map.class));
    }
}