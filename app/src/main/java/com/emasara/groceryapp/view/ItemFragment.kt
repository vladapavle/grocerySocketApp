package com.emasara.groceryapp.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emasara.groceryapp.R
import kotlinx.android.synthetic.main.fragment_item.*


class ItemFragment : Fragment() {
    companion object {
        private const val COLOR = "color"
        private const val TRANSITION_NAME = "transitionName"

        fun newInstance(color: String, transactionName: String): ItemFragment {
            val args = Bundle()
            args.putString(COLOR, color)
            args.putString(TRANSITION_NAME, transactionName)
            val fragment = ItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var color: String
    private lateinit var transactionName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            color = requireArguments().getString(COLOR, "")
            transactionName = requireArguments().getString(TRANSITION_NAME, "")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        holder.setBackgroundColor(Color.parseColor(color))
        holder.transitionName = transactionName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }
}