package co.icanteach.projectx

import android.app.Activity
import android.app.Application
import android.content.Context
import co.icanteach.projectx.common.di.component.DaggerAppComponent
import com.facebook.stetho.DumperPluginsProvider
import com.facebook.stetho.Stetho
import com.facebook.stetho.dumpapp.DumperPlugin
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.util.*
import javax.inject.Inject

class InterviewApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableDumpapp(SampleDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )

        DaggerAppComponent
            .builder()
            .app(this)
            .create(this)
            .inject(this)
    }

    private class SampleDumperPluginsProvider(private val mContext: Context) :
        DumperPluginsProvider {

        override fun get(): Iterable<DumperPlugin> {
            val plugins = ArrayList<DumperPlugin>()
            for (defaultPlugin in Stetho.defaultDumperPluginsProvider(mContext).get()) {
                plugins.add(defaultPlugin)
            }
            return plugins
        }
    }
}