package contact.di

import contact.activity.ContainerActivityTest
import contact.activity.StartActivityTest
import contact.di.base.UiTestScope
import dagger.Subcomponent

@UiTestScope
@Subcomponent
interface UiTestComponent {
    fun inject(uiTest: ContainerActivityTest)
    fun inject(uiTest: StartActivityTest)
}
