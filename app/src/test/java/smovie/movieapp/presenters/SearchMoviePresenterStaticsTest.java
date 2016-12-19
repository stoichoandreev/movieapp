package smovie.movieapp.presenters;

import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.Observable;

import static org.mockito.Mockito.when;


/**
 * Created by sniper on 19-Dec-2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(RxTextView.class)
public class SearchMoviePresenterStaticsTest {
    @Test
    public void test() {
        PowerMock.mockStatic(RxTextView.class);
        TextViewTextChangeEvent textView = PowerMock.createMock(TextViewTextChangeEvent.class);
//        TextViewTextChangeEvent textView = Mockito.mock(TextViewTextChangeEvent.class);
        EditText searchEditText = Mockito.mock(EditText.class);
        Observable<TextViewTextChangeEvent> observable = Observable.just(textView);
        when(RxTextView.textChangeEvents(searchEditText)).thenReturn(observable);

        Assert.assertEquals(observable,
                RxTextView.textChangeEvents(searchEditText));
    }
}