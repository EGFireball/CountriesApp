package dimi.com.countryapp.ui.fragment

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dimi.com.countryapp.CountryApp
import dimi.com.countryapp.MainActivity
import dimi.com.countryapp.R
import dimi.com.countryapp.ui.adapter.CountryListAdapter
import dimi.com.countryapp.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.country_list_fragment.*

class CountryListFragment: Fragment() {

    lateinit var mainVm: MainViewModel
    lateinit var countriesAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        CountryApp.appComponent.inject(this)
        mainVm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        CountryApp.appComponent.inject(mainVm)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.country_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.country_list)
        mainVm.getAllCountries()
        countriesAdapter = CountryListAdapter(activity)
        countriesList.layoutManager = LinearLayoutManager(context)
        countriesList.adapter = countriesAdapter
        countriesList.addItemDecoration(DividerItemDecoration(context, HORIZONTAL))
        countriesAdapter.notifyDataSetChanged()
        mainVm.getAllCountries().observe(this, Observer { countries ->
            if (!countries.isNullOrEmpty()) {
                val mutableCountries = countries.toMutableList()
                mutableCountries.sortByDescending { country -> country.population }
                countriesAdapter.updateData(mutableCountries, fromServer = true)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val act: MainActivity = activity as MainActivity

        val searchManager = act.getSystemService(SEARCH_SERVICE) as SearchManager?
        act.searchView = menu.findItem(R.id.action_search)?.actionView as SearchView
        act.searchView.setSearchableInfo(
            searchManager!!
                .getSearchableInfo(act.componentName)
        )

        act.searchView.maxWidth = Integer.MAX_VALUE

        val navHostFragment = act.supportFragmentManager.fragments.first() as? NavHostFragment
        val listFragment = navHostFragment?.childFragmentManager?.fragments?.get(0) as CountryListFragment

        act.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                listFragment.countriesAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                listFragment.countriesAdapter.filter.filter(query)
                return false
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }
}