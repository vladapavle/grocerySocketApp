package com.emasara.groceryapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emasara.groceryapp.R
import com.emasara.groceryapp.model.Grocery
import com.emasara.groceryapp.viewmodel.GroceriesViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment :
    Fragment(), GroceryAdapter.OnItemClickListener {
    private lateinit var allGroceriesViewModel: GroceriesViewModel

    private val adapter = GroceryAdapter(mutableListOf(), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allGroceriesViewModel =
            ViewModelProvider(requireActivity()).get(GroceriesViewModel::class.java)
        groceryView.adapter = adapter
        connectButton.setOnClickListener {
            allGroceriesViewModel.changeConnectionStatus()
        }
        setupObservers()
    }

    private fun setupObservers() {
        allGroceriesViewModel.getConnectionStatus()
            .observe(viewLifecycleOwner, Observer { connectionStatus ->
                connectButton.text =
                    getString(if (connectionStatus) R.string.disconnect else R.string.connect)
                if (!connectionStatus) {
                    adapter.orderData()
                }
            })
        allGroceriesViewModel.getAllGroceriesLiveData()
            .observe(viewLifecycleOwner, Observer { groceries ->
                groceries?.let {
                    adapter.updateData(groceries)
                    if (groceries.isNotEmpty()) {
                        groceryView.scrollToPosition(0)
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onItemClick(socketData: Grocery, circleView: ImageView) {
        allGroceriesViewModel.onItemClick(socketData, circleView)
    }
}