package dimi.com.countryapp.ui.fragment

import android.app.SearchManager
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.SEARCH_SERVICE
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dimi.com.countryapp.CountryApp
import dimi.com.countryapp.R
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.ui.MainActivity
import dimi.com.countryapp.ui.adapter.CountryListAdapter
import dimi.com.countryapp.ui.viewmodel.MainViewModel
import dimi.com.countryapp.ui.viewmodel.MainViewModelKoin
import kotlinx.android.synthetic.main.country_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { CountryListFragment() }
}

class CountryListFragment : Fragment() {

//    private lateinit var mainVm: MainViewModel
    private val mainVmKoin: MainViewModelKoin by viewModel()
    lateinit var countriesAdapter: CountryListAdapter
    private lateinit var searchView: SearchView

    private val observer = Observer<List<Country>> {
        //Log.e("Countries", "${it.size}")
        setCountries(it)
        //fragmentInfo.text = "Temperature at ${it.name} is ${it.temp.temp} celcius"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        CountryApp.appComponent.inject(this)
        //ViewModelProviders.of(this).get(MainViewModel::class.java)
//        mainVm = ViewModelProvider(this).get(MainViewModel::class.java)
//        CountryApp.appComponent.inject(mainVm)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.country_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.country_list)
        hideList(getString(R.string.loading_data_text))
        countriesAdapter = CountryListAdapter()
        countriesList.layoutManager = LinearLayoutManager(context)
        countriesList.adapter = countriesAdapter
        countriesList.addItemDecoration(DividerItemDecoration(context, HORIZONTAL))
        countriesAdapter.notifyDataSetChanged()
//        mainVm.getAllCountries().observe(activity as MainActivity, { countries ->
//            setCountries(countries)
//        })
    }

    override fun onResume() {
        super.onResume()
        mainVmKoin.countries.value?.let { setCountries(it) } ?: run {
            mainVmKoin.countries.observe(activity as MainActivity, observer)
        }
    }

    private fun setCountries(countries: List<Country>) {
        if (countries.isEmpty()) {
            hideList(getString(R.string.empty_list_text))
        } else {
            val mutableCountries = countries.toMutableList()
            mutableCountries.sortByDescending { country -> country.population }
            countriesAdapter.updateData(mutableCountries, fromServer = true)
            showList()
        }
    }

    private fun showList() {
        emptyListView.visibility = View.GONE
        countriesList.visibility = View.VISIBLE
    }

    private fun hideList(text: String) {
        countriesList.visibility = View.GONE
        emptyListView.text = text
        emptyListView.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val searchManager = activity?.getSystemService(SEARCH_SERVICE) as SearchManager?
        searchView = menu.findItem(R.id.action_search)?.actionView as SearchView

        searchView.setSearchableInfo(
            searchManager?.getSearchableInfo(activity?.componentName)
        )

        searchView.maxWidth = Integer.MAX_VALUE

        val navHostFragment =
            activity?.supportFragmentManager?.fragments?.first() as? NavHostFragment
        val listFragment =
            navHostFragment?.childFragmentManager?.fragments?.get(0) as CountryListFragment

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                hideKeyboard()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.length?. let {
                    if (it < 3) {
                        return true
                    }
                }
                listFragment.countriesAdapter.filter.filter(query)
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            R.id.action_refresh -> {
                refreshList()
                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun refreshList() {
//        mainVm.refreshCountriesData().observe(this, { countries ->
//            setCountries(countries)
//        })
        mainVmKoin.countries.observe(activity as MainActivity, observer)
        searchView.setQuery("", false)
        searchView.isIconified = true
    }

    private fun hideKeyboard() {
        searchView.clearFocus()
        val im = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(searchView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}