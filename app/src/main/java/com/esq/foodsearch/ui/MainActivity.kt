package com.esq.foodsearch.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.esq.foodsearch.model.Food
import com.esq.foodsearch.ui.UserAdapter.OnFoodItemListener
import com.esq.foodsearch.utils.Status
import com.esq.foodsearch.utils.longToast
import com.esq.foodsearch.utils.shortToast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.skydoves.androidveil.VeilRecyclerFrameView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : SearchActivity(), OnFoodItemListener {
    private var recyclerView: VeilRecyclerFrameView? = null
    private var TAG = MainActivity::class.simpleName
    private var tokenTimeLeft: Int = -1//From intent = 0
    private var searchListResponseData: ArrayList<Food>? = null //List of results found
    private var searchKey: String? = null //String Value of food to search for

    //private lateinit var bind: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var searchView: SearchView
    private var mToolbar: MaterialToolbar? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(application)).get(MainActivityViewModel::class.java)
        recyclerView = bind.recyclerView
        recyclerView!!.veil()

        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView!!.setLayoutManager(linearLayoutManager)
        recyclerView!!.addVeiledItems(10)
        recyclerView!!.getVeiledRecyclerView().setHasFixedSize(true)
    }

    override fun getList(searchKey: String) {
        shortToast("GetList Called!!!")
        scope.launch {
            delay(2000)
            viewModel.getSearchList(searchKey = searchKey).observe(this@MainActivity, Observer {
                it.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            recyclerView!!.veil()
                            longToast("Loading")
                            Log.d(TAG, "Status.LOADING: Loading")
                        }
                        Status.SUCCESS -> {
                            shortToast("Success")
                            Log.d(TAG, "Status.SUCCESS: Success")
                            val data = it.data?.foods?.food
                            setDataInRecyclerView(data!!)
                            recyclerView!!.unVeil()
                        }
                        Status.ERROR -> {
                            longToast("Error")
                            Log.d(TAG, "Status.ERROR: Failed")
                            Snackbar.make(bind.root, resource.message!!, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun setDataInRecyclerView(searchListResponseData: ArrayList<Food>) {
        Log.d(TAG, "setDataInRecyclerView(): Data in recyclerView is set up")
        val usersAdapter = UserAdapter(this@MainActivity, searchListResponseData, this)
        recyclerView!!.setAdapter(usersAdapter)
    }

    override fun onFoodItemClick(position: Int) {
       longToast(searchListResponseData!![position].foodName!!)
        Log.d(TAG, String.format("%s%d%s", "onFoodItemClick: Item ", position, "clicked"))
    }

}