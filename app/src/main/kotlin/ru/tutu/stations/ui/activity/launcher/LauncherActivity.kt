package ru.tutu.stations.ui.activity.launcher

import android.os.Bundle
import android.support.annotation.StringRes
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.R
import ru.tutu.stations.data.*
import ru.tutu.stations.ui.activity.base.ActivityModule
import ru.tutu.stations.ui.activity.base.MvpActivity
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class LauncherActivity : MvpActivity(), LauncherView {

    private val logger = LoggerFactory.getLogger(LauncherActivity::class)

    // region DI
    private lateinit var screenComponent: LauncherScreenComponent
    private lateinit var component: LauncherComponent
    @Inject
    lateinit var navigator: Navigator
    // endregion
    // region VIEW
    private lateinit var progressBar: ProgressBar
    private lateinit var syncStatus: TextView
    private lateinit var buttonNext: Button
    private lateinit var buttonRefresh: Button
    // endregion
    // region OTHER
    private lateinit var presenter: LauncherPresenter
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        screenComponent = getScreenComponent()
        component = screenComponent.launcherComponentBuilder()
            .activityModule(ActivityModule(this))
            .build()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        progressBar = findViewById(R.id.progressBar)
        syncStatus = findViewById(R.id.syncStatus)
        buttonNext = findViewById(R.id.buttonNext)
        buttonRefresh = findViewById(R.id.buttonRefresh)
        buttonNext.setOnClickListener { _ -> presenter.onClickedBtnNext() }
        buttonRefresh.setOnClickListener { _ -> presenter.onClickedBtnRefresh() }
        presenter = getMvpDelegate().getPresenter(component::launcherPresenter, LauncherPresenter::class.java)
        presenter.initialize()
    }

    private fun getScreenComponent(): LauncherScreenComponent {
        val saved = lastCustomNonConfigurationInstance
        return if (saved == null) appComponent.launcherScreenComponent() else saved as LauncherScreenComponent
    }

    override fun onResume() {
        navigator.onResume(this)
        super.onResume()
    }

    override fun onPause() {
        navigator.onPause()
        super.onPause()
    }

    override fun setSyncStatus(syncStatus: SyncStatus) {
        when (syncStatus) {
            is UnknownStatus -> {
                /*nop*/
            }
            is DownloadStatus -> {
                progressBar.visibility = View.VISIBLE
                applySyncText(R.string.download_sync_status)
                hide(buttonNext)
                hide(buttonRefresh)
            }
            is ProcessStatus -> {
                applySyncText(R.string.process_sync_status)
            }
            is SuccessStatus -> {
                progressBar.visibility = View.GONE
                applySyncText(R.string.success_sync_status)
                show(buttonNext)
            }
            is NotRequireStatus -> {
                progressBar.visibility = View.GONE
                applySyncText(R.string.not_require_sync_status)
                show(buttonNext)
            }
            is ErrorStatus -> {
                progressBar.visibility = View.GONE
                applySyncText(R.string.error_sync_status)
                show(buttonRefresh)
            }
            is DeferredStatus -> {
                progressBar.visibility = View.GONE
                applySyncText(R.string.deferred_sync_status)
                show(buttonNext)
            }
        }
    }

    private fun hide(button: Button) {
        button.visibility = View.GONE
    }

    private fun show(button: Button) {
        button.visibility = View.VISIBLE
    }

    private fun applySyncText(@StringRes statusText: Int) {
        syncStatus.text = resources.getString(statusText)
    }

}