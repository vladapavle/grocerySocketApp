package com.emasara.groceryapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emasara.groceryapp.R
import com.emasara.groceryapp.model.Grocery
import com.emasara.groceryapp.util.AppTransition.Companion.getTransition
import com.emasara.groceryapp.viewmodel.GroceriesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var allGroceriesViewModel: GroceriesViewModel
    private lateinit var listFragment: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allGroceriesViewModel = ViewModelProvider(this)[GroceriesViewModel::class.java]

        listFragment = ListFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentHolder.id, listFragment)
            .commit()
        allGroceriesViewModel.selectedItem.observe(this) {
            it.let {
                showDetail(it.circleView, it.socketData)
            }
        }
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
}