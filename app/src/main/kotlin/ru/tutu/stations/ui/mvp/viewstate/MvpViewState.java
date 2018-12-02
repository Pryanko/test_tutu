package ru.tutu.stations.ui.mvp.viewState;

import ru.tutu.stations.ui.mvp.view.MvpView;

/**
 * Состояние {@link MvpView}.
 *
 * @param <V> {@link MvpView}
 * @author Grigoriy Pryamov
 */
public interface MvpViewState<V extends MvpView> {

    /**
     * Присоединение view.
     *
     * @param view Присоединенная view
     */
    void attachView(V view);

    /**
     * Отсоединение view.
     *
     * @param view Отсоединенная view
     */
    void detachView(V view);
}
