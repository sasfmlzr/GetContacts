package contact.di.core

import dagger.Subcomponent
import contact.activity.container.ContainerActivity
import contact.activity.start.StartActivity
import contact.di.base.FragmentScope

@FragmentScope
@Subcomponent
interface ActivityComponent {
    fun inject(activity: ContainerActivity)
    fun inject(activity: StartActivity)
}
