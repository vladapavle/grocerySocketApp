package com.emasara.groceryapp.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emasara.groceryapp.R
import kotlinx.android.synthetic.main.fragment_item.*


class ItemFragment : Fragment() {
    companion object {
        fun newInstance(color: String, transactionName: String): ItemFragment {
            val args = Bundle()
            args.putString("color", color)
            args.putString("transitionName", transactionName)
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
            color = requireArguments().getString("color", "")
            transactionName = requireArguments().getString("transitionName", "")
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

    private fun drawRectangle(backgroundColor: String): GradientDrawable? {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(Color.parseColor(backgroundColor))
        return shape
    }

}