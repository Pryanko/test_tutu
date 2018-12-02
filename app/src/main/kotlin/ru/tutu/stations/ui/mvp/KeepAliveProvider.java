package ru.tutu.stations.ui.mvp;

/**
 * @author Grigoriy Pryamov
 */
public interface KeepAliveProvider {
    boolean keepAlive(boolean parentKeepAlive);
}
