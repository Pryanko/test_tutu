package ru.tutu.stations.ui.activity.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.R
import ru.tutu.stations.ui.activity.base.ActivityModule
import ru.tutu.stations.ui.activity.base.MvpActivity
import javax.inject.Inject

/**
 * @author Grigoriy Pryamov.
 */
class MainActivity : MvpActivity(), MainView {

    private val logger = LoggerFactory.getLogger(MainActivity::class)

    // region DI
    private lateinit var screenComponent: MainScreenComponent
    private lateinit var component: MainComponent
    //endregion
    //region VIEW
    //endregion
    // region OTHER
    private lateinit var presenter: MainPresenter
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        screenComponent = getScreenComponent()
        component = screenComponent.mainComponentBuilder()
            .activityModule(ActivityModule(this))
            .build()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = getMvpDelegate().getPresenter(component::mainPresenter, MainPresenter::class.java)
        presenter.initialize()
    }

    private fun getScreenComponent(): MainScreenComponent {
        val saved = lastCustomNonConfigurationInstance
        return if (saved == null) appComponent.mainScreenComponent() else saved as MainScreenComponent
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}