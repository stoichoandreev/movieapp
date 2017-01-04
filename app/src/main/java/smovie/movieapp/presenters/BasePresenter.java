package smovie.movieapp.presenters;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V, R> {

    protected V mView;
    protected R mRepository;
    protected WeakReference<V> reference;
    protected CompositeSubscription mCompositeSubscription;

    protected BasePresenter() {
        mCompositeSubscription = new CompositeSubscription();
    }

    public BasePresenter(V view, R repository) {
        this();
        this.mView = view;
        this.mRepository = repository;
    }

    public void bindView(@NonNull V view) {
        this.reference = new WeakReference<>(view);
    }

    public void unbindView() {
        this.reference = null;
    }

    /**
     * Add subscription to the CompositeSubscription set
     * @param newSubscription - the subscription to be added
     */
    public void addSubscription(Subscription newSubscription){
        if(newSubscription != null){
            mCompositeSubscription.add(newSubscription);
        }
    }

    /**
     * Call this method to release all subscriptions added previously.
     * This will help to prevent memory leak in case of View destroy until the observable is still working
     * We are calling unsubscribe() method not clear() and we are creating new instance of the mCompositeSubscription , because I found it useful.
     * NOTE : Remember when you call unsubscribe() you can not reuse the CompositeSubscription instance any more, you have to create new instance
     *       If you call clear you can continue using same instance of mCompositeSubscription but in some scenarios the subscription may not work as expected
     */
    public void destroyAllSubscriptions(){
        if(mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = new CompositeSubscription();
//            mCompositeSubscription.clear();
        }
    }
}