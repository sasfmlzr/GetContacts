package contact.fragment.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import contact.architecture.base.BaseFragment
import contact.databinding.FragmentContactsBinding
import contact.di.core.FragmentComponent
import contact.di.core.Injector
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : BaseFragment<ContactsVM,
        FragmentContactsBinding>(ContactsVM::class.java) {


    override fun inject(component: FragmentComponent) = Injector.viewComponent().inject(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentContactsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contacts.text = viewModel.contacts
        super.onViewCreated(view, savedInstanceState)
    }


}
