package com.kingjinho.dontcallhim.screen.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kingjinho.dontcallhim.R
import com.kingjinho.dontcallhim.utils.getAppComponent
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVM
import com.kingjinho.dontcallhim.viewmodels.OutgoingCallVMFactory
import com.kingjinho.dontcallhim.viewmodels.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScreenAddNumber : Fragment(), ScreenAddNumberMvc.Listener {

    private lateinit var viewMvc: ScreenAddNumberMvc
    private var fetchAddNumbersJob: Job? = null

    private val addNumberVM by viewModels<OutgoingCallVM> {
        OutgoingCallVMFactory(
            addNumberUseCase = getAppComponent().newPresentationComponent().getAddNumberUseCase(),
            fetchNumbersUseCase = getAppComponent().newPresentationComponent().getFetchNumbersUseCase()
        )
     }

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewMvc = ScreenAddNumberMvc(inflater, container)
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