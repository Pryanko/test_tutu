package ru.tutu.stations.ui.activity.launcher

import android.os.Bundle
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.R
import ru.tutu.stations.ui.activity.base.ActivityModule
import ru.tutu.stations.ui.activity.base.MvpActivity

/**
 * @author Grigoriy Pryamov.
 */
class LauncherActivity : MvpActivity(), LauncherView {

    private val logger = LoggerFactory.getLogger(LauncherActivity::class)

    // region DI
    private lateinit var screenComponent: LauncherScreenComponent
    private lateinit var component: LauncherComponent
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
        presenter = getMvpDelegate().getPresenter(component::launcherPresenter, LauncherPresenter::class.java)
        presenter.initialize()
    }

    private fun getScreenComponent(): LauncherScreenComponent {
        val saved = lastCustomNonConfigurationInstance
        return if (saved == null) appComponent.launcherScreenComponent() else saved as LauncherScreenComponent
    }

}