package smovie.movieapp.presenters;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V, R> {

    protected V mView;
    protected R mRepository;
    protected WeakReference<V> reference;

    protected BasePresenter(){}

    public BasePresenter(V view, R repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    public void bindView(@NonNull V view) {
        this.reference = new WeakReference<>(view);
    }

    public void unbindView() {
        this.reference = null;
    }
}