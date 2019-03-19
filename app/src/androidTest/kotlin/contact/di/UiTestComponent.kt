package contact.di

import contact.test.ContainerActivityTest
import contact.test.StartActivityTest
import contact.di.base.UiTestScope
import dagger.Subcomponent

@UiTestScope
@Subcomponent
interface UiTestComponent {
    fun inject(uiTest: ContainerActivityTest)
    fun inject(uiTest: StartActivityTest)
}
