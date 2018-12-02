package ru.tutu.stations.ui.mvp.core;

import android.support.annotation.NonNull;
import ru.tutu.stations.ui.mvp.presenter.MvpPresenter;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Хранилище презентеров.
 *
 * @author Grigoriy Pryamov
 */
class PresenterStore {

    private Map<String, MvpPresenter> presenters = new HashMap<>();

    @Inject
    PresenterStore() {

    }

    <P extends MvpPresenter> P getPresenter(@NonNull String tag) {
        return (P) presenters.get(tag);
    }

    void putPresenter(@NonNull String tag, @NonNull MvpPresenter presenter) {
        presenters.put(tag, presenter);
    }

    void removePresenter(@NonNull String tag) {
        presenters.remove(tag);
    }
}
