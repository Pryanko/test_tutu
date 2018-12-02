package ru.tutu.stations.ui.activity.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.di.Dagger
import ru.tutu.stations.ui.mvp.MvpDelegate
import ru.tutu.stations.ui.mvp.MvpView

/**
 * Базовая активити с нормальной реализацией биндинга {@link MvpView}.
 *
 * @author Grigoriy Pryamov.
 */
sealed class BaseActivity : AppCompatActivity() {
    protected var appComponent = Dagger.get()
}

abstract class MvpActivity : BaseActivity() {

    private val logger = LoggerFactory.getLogger(BaseActivity::class.java)
    /**
     * Делегат для хранения/получения тега
     */
    private lateinit var mvpDelegate: MvpDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
        logger.trace("onCreate: " + this)
        super.onCreate(savedInstanceState)
        mvpDelegate = MvpDelegate(appComponent.mvpProcessor(), this as MvpView)
        mvpDelegate.init(savedInstanceState)
    }

    override fun onStart() {
        logger.trace("onStart: " + this)
        super.onStart()
        mvpDelegate.bindView()
    }

    override fun onStop() {
        logger.trace("onStop" + this)
        mvpDelegate.unbindView()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mvpDelegate.saveState(outState!!)
    }

    override fun onDestroy() {
        logger.trace("onDestroy: " + this)
        mvpDelegate.destroy(keepAlive())
        super.onDestroy()
    }

    private fun keepAlive(): Boolean = !isFinishing
}