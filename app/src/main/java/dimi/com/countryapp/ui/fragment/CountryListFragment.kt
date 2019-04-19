package dimi.com.countryapp.ui.fragment

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dimi.com.countryapp.CountryApp
import dimi.com.countryapp.R
import dimi.com.countryapp.ui.adapter.CountryListAdapter
import dimi.com.countryapp.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.country_list_fragment.*
import androidx.core.content.ContextCompat.getSystemService
import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import androidx.appcompat.widget.SearchView
import android.content.Context.SEARCH_SERVICE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import dimi.com.countryapp.MainActivity

class CountryListFragment: Fragment() {

    lateinit var mainVm: MainViewModel
    lateinit var countriesAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CountryApp.appComponent.inject(this)
        mainVm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        CountryApp.appComponent.inject(mainVm)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.country_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
}