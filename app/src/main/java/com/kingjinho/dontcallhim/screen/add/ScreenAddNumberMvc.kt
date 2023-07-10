package com.kingjinho.dontcallhim.screen.add

import android.graphics.Rect
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.kingjinho.dontcallhim.databinding.ScreenAddNumberBinding
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.recyclerview.PhoneNumbersAdapter
import com.kingjinho.dontcallhim.utils.dp

class ScreenAddNumberMvc(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup? = null
) {

    interface Listener {
        fun setOnAddNumberClickListener()
    }

    private val binding = ScreenAddNumberBinding.inflate(layoutInflater, viewGroup, false)
    val rootView = binding.root
    val numberToAdd: EditText
        get() = binding.numberToAdd

    private val context = rootView.context
    private val adapter = PhoneNumbersAdapter()
    private var listener: Listener? = null


    init {
        setRecyclerViewAdapter()
        addDecorator()
        addTextWatcher()
        setAddNumberButtonClickListener()
    }

    fun addButtonClickListener(listener: Listener) {
        this.listener = listener
    }

    private fun setRecyclerViewAdapter() {
        binding.blockedList.apply {
            adapter = this@ScreenAddNumberMvc.adapter
            layoutManager = LinearLayoutManager(this@ScreenAddNumberMvc.context)
        }
    }

    private fun addDecorator() {
        if (binding.blockedList.itemDecorationCount == 0) {
            binding.blockedList.addItemDecoration(object : ItemDecoration() {

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.apply {
                        left += 24.dp
                        right += 24.dp
                        top += 16.dp
                    }
                }
            })
        }
    }

    private fun addTextWatcher() {
        binding.numberToAdd.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun setAddNumberButtonClickListener() {
        binding.addNumber.setOnClickListener {
            listener?.setOnAddNumberClickListener()
        }
    }

    fun submitList(items: List<PhoneNumber>) {
        adapter.submitList(items)
    }

}