package com.emasara.groceryapp.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Transition
import com.emasara.groceryapp.R
import com.emasara.groceryapp.model.Grocery
import com.emasara.groceryapp.viewmodel.GroceriesViewModel
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var allGroceriesViewModel: GroceriesViewModel
    private lateinit var listFragment: ListFragment




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allGroceriesViewModel = ViewModelProvider(this).get(GroceriesViewModel::class.java)

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentHolder.id, listFragment)
            .commit()
        allGroceriesViewModel.selectedItem.observe(this, Observer {
            it.let {
                showDetail(it.circleView, it.socketData)
            }
        })
    }


    private fun showDetail(view: View, grocery: Grocery) {
        val transitionName = view.transitionName
        val fragment = ItemFragment.newInstance(grocery.bagColor, view.transitionName)
        fragment.sharedElementEnterTransition = getTransition()
        fragment.sharedElementReturnTransition = getTransition()

        supportFragmentManager
            .beginTransaction()
            .addSharedElement(view, transitionName)
            .add(fragmentHolder.id, fragment)
            .hide(listFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getTransition(): Transition {
        return MaterialContainerTransform().apply {
            scrimColor = Color.TRANSPARENT
            containerColor = Color.TRANSPARENT
            duration = 500L
            interpolator = DecelerateInterpolator()
        }

    }
}