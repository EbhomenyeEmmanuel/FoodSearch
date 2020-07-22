package com.esq.foodsearch.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.esq.foodsearch.R
import com.esq.foodsearch.databinding.ActivityMainBinding
import com.esq.foodsearch.model.Food
import com.esq.foodsearch.ui.UserAdapter.OnFoodItemListener
import com.esq.foodsearch.utils.Status
import com.esq.foodsearch.utils.longToast
import com.esq.foodsearch.utils.shortToast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.skydoves.androidveil.VeilRecyclerFrameView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), OnFoodItemListener {
    var recyclerView: VeilRecyclerFrameView? = null
    var TAG = MainActivity::class.simpleName
    var tokenTimeLeft: Int = -1//From intent = 0
    var searchListResponseData: ArrayList<Food>? = null //List of results found
    var searchKey: String? = null //String Value of food to search for
    private lateinit var bind: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var searchView: MaterialSearchView
    private var mToolbar: MaterialToolbar? = null
    val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mToolbar = bind.toolbar
        setSupportActionBar(mToolbar)
        viewModel = ViewModelProvider(this, MainActivityViewModelFactory(application)).get(MainActivityViewModel::class.java)
        recyclerView = bind.recyclerView
        searchView = bind.searchView
        setSearchListeners()
        getList("Rice")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search)
        searchView.setMenuItem(item)
        return true
    }

    private fun setSearchListeners() {
        bind.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        bind.searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewClosed() {

            }

            override fun onSearchViewShown() {

            }

        })
    }

    private fun getList(searchKey: String) {
        scope.launch {
            delay(2000)
            viewModel.getSearchList(searchKey = searchKey).observe(this@MainActivity, Observer {
                it.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            bind.linearLayout1.visibility = View.GONE
                            bind.recyclerView.visibility = View.VISIBLE
                            recyclerView!!.veil()
                            longToast("Loading")
                            Log.d(TAG, "Status.LOADING: Loading")
                        }
                        Status.SUCCESS -> {
                            val data = it.data?.foods?.food
                            setDataInRecyclerView(data!!)
                            recyclerView!!.unVeil()
                            shortToast("Success")
                            Log.d(TAG, "Status.SUCCESS: Success")
                        }
                        Status.ERROR -> {
                            longToast("Error")
                            Log.d(TAG, "Status.ERROR: Failed")
                            Snackbar.make(bind.root, resource.message!!, Snackbar.LENGTH_SHORT).show()
                            bind.recyclerView.visibility = View.GONE
                            bind.lottie.visibility = View.VISIBLE
                            bind.lottie.setAnimation(R.raw.empty)
                            bind.noSearchTextView.visibility = View.GONE
                            bind.errorMsgTextView.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }

    }

    private fun setDataInRecyclerView(searchListResponseData: ArrayList<Food>) {
        Log.d(TAG, "setDataInRecyclerView(): Data in recyclerView is set up")
        // set a LinearLayoutManager with default vertical orientation
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        val usersAdapter = UserAdapter(this@MainActivity, searchListResponseData, this)
        recyclerView!!.setAdapter(usersAdapter) // set the Adapter to RecyclerView
        recyclerView!!.setLayoutManager(linearLayoutManager)
        recyclerView!!.getVeiledRecyclerView().setHasFixedSize(true)
    }

    override fun onFoodItemClick(position: Int) {
        Toast.makeText(this@MainActivity, searchListResponseData!![position].foodName, Toast.LENGTH_SHORT).show()
        Log.d(TAG, String.format("%s%d%s", "onFoodItemClick: Item ", position, "clicked"))
    }

}