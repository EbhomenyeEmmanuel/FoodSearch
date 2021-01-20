package com.esq.foodsearch.ui

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.biodun.networkMonitorManager.NetworkState
import com.esq.foodsearch.R
import com.esq.foodsearch.databinding.ActivityMainBinding
import com.esq.foodsearch.utils.longToast
import com.esq.foodsearch.utils.shortToast
import com.google.android.material.appbar.MaterialToolbar

open class SearchActivity : AppCompatActivity() {
    private var TAG = SearchActivity::class.simpleName
    private var searchKey: String? = null //String Value of food to search for
    protected lateinit var bind: ActivityMainBinding
    private lateinit var searchView: SearchView
    private var mToolbar: MaterialToolbar? = null
    private lateinit var networkStatus: NetworkStatus
    private var isNetworkAvailable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        networkStatus = NetworkStatus(this)
        mToolbar = bind.toolbar
        setSupportActionBar(mToolbar)
        mToolbar!!.setTitleTextColor(Color.parseColor("#FFFFFF"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)
        val item: MenuItem = menu!!.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = item.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.inputType = InputType.TYPE_CLASS_TEXT
        searchView.setIconifiedByDefault(false)
        setSearchListeners()
        return true
    }

    protected fun setSearchListeners() {
        val queryTextListener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                searchKey = newText
                if (isNetworkAvailable) {
                    // getList(newText)
                }
                shortToast("Query is $newText")
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (isNetworkAvailable){
                    getList(query)
                }
                searchKey = query
                shortToast("Query Submitted is $query")
                return true
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
    }

    open fun getList(searchKey: String) {}

     override fun onStart() {
        super.onStart()
        observeNetworkStateLiveData()
    }

    private fun observeNetworkStateLiveData() {
        networkStatus.networkMonitorManager.networkState.observe(this, Observer { network ->
            when (network) {
                is NetworkState.Available -> {
                    isNetworkAvailable = true
                    longToast("Network Available")
                }
                is NetworkState.UnAvailable -> longToast("Network  UnAvailable")
                is NetworkState.Lost -> longToast("Network Lost")
            }
        })
    }

}