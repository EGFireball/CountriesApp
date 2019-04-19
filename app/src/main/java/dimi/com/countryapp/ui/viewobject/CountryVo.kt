package dimi.com.countryapp.ui.viewobject

import android.os.Parcel
import android.os.Parcelable
import dimi.com.countryapp.domain.Country

class CountryVo() : Parcelable {

    var countryName: String? = ""
    var population: Int? = 0
    var capital: String? = ""
    var area: Double? = 0.0

    constructor(parcel: Parcel) : this() {
        this.countryName = parcel.readString()
        this.population = parcel.readInt()
        this.capital = parcel.readString()
        this.area = parcel.readDouble()
    }

    constructor(country: Country) : this() {
        this.countryName = country.name
        this.population = country.population
        this.capital = country.capital
        this.area = country.area
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(countryName)
        population?.let { dest?.writeInt(it) }
        dest?.writeString(capital)
        area?.let { dest?.writeDouble(it) }
    }

    companion object CREATOR : Parcelable.Creator<CountryVo> {
        override fun createFromParcel(parcel: Parcel): CountryVo {
            return CountryVo(parcel)
        }

        override fun newArray(size: Int): Array<CountryVo?> {
            return arrayOfNulls(size)
        }
    }

}