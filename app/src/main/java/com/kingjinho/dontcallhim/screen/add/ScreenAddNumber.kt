package com.kingjinho.dontcallhim.screen.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kingjinho.dontcallhim.R
import com.kingjinho.dontcallhim.screen.BaseScreen
import com.kingjinho.dontcallhim.screen.viewfactory.BaseViewMvcFactory
import com.kingjinho.dontcallhim.usecase.add.AddNumberUseCase
import com.kingjinho.dontcallhim.usecase.fetch.FetchNumbersUseCase
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVM
import com.kingjinho.dontcallhim.viewmodels.Result
import com.kingjinho.dontcallhim.viewmodels.ViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScreenAddNumber : BaseScreen(), ScreenAddNumberMvc.Listener {

    @Inject lateinit var viewMvcFactory: BaseViewMvcFactory
    @Inject lateinit var addNumberUseCase: AddNumberUseCase
    @Inject lateinit var fetchNumberUseCase: FetchNumbersUseCase

    private lateinit var viewMvc: ScreenAddNumberMvc

    @Inject
    lateinit var addNumberVMFactory: ViewModelFactory
    private val addNumberVM: OutgoingCallVM by lazy {
        ViewModelProvider(this, addNumberVMFactory)[OutgoingCallVM::class.java]
    }

    private var fetchAddNumbersJob: Job? = null

    override fun setOnAddNumberClickListener() {
        val number = viewMvc.numberToAdd.text.toString()
        viewLifecycleOwner.lifecycleScope.launch {
            addNumberVM.addNumber(number).collectLatest {
                if (it == Result.Failure) {
                    Toast
                        .makeText(requireContext(), R.string.msg_invalid_number, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewMvc = viewMvcFactory.newAddNumberMvc(container)
        viewMvc.addButtonClickListener(this)
        return viewMvc.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchAddNumbersJob = viewLifecycleOwner.lifecycleScope.launch {
            addNumberVM.fetchSavedNumbers().collectLatest {
                viewMvc.submitList(it)
            }
        }
    }

}