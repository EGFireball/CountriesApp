package dimi.com.countryapp.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dimi.com.countryapp.R
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.ui.fragment.CountryListFragmentDirections
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryListAdapter(private val activity: Activity?): RecyclerView.Adapter<CountryListAdapter.ViewHolder>(), Filterable {

    private lateinit var originalCountries: List<Country>
    private var items: List<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(activity).inflate(R.layout.country_list_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = items[position]
        holder.countryName.text = country.name
        holder.population.text = "${country.population} people"

        holder.itemView.setOnClickListener {
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryFragment(items[position])
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment).navigate(action) }
        }
    }

    fun updateData(countries: List<Country>, fromServer: Boolean) {
        if (fromServer) {
            this.originalCountries = countries
        }
        this.items = countries
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val itemsFiltered: List<Country>
                val charString = charSequence.toString()

                itemsFiltered = if (charString.length < 3) {
                    originalCountries
                } else {
                    originalCountries.filter { country -> country.name!!.toLowerCase().contains(charString.toLowerCase()) }
                }

                val filterResults = FilterResults()
                filterResults.values = itemsFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                updateData(filterResults.values as List<Country>, fromServer = false)
            }
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.countryName
        val population: TextView = itemView.countryPopulation
    }
}