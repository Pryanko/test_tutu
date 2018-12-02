package ru.tutu.stations.ui.mvp;

import ru.tutu.stations.ui.mvp.presenter.MvpPresenter;

/**
 * @author Grigoriy Pryamov
 */
public interface PresenterProvider<P extends MvpPresenter> {
    P providePresenter();
}
