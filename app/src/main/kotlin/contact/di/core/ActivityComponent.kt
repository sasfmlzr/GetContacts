package contact.di.core

import contact.activity.container.ContainerActivity
import contact.activity.start.StartActivity
import contact.di.base.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {
    fun inject(activity: ContainerActivity)
    fun inject(activity: StartActivity)
}
