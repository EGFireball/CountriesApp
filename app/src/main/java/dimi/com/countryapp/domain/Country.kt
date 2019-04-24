package dimi.com.countryapp.domain

import android.os.Parcel
import android.os.Parcelable
import dimi.com.countryapp.data.dto.CountryDto

class Country() : Parcelable {

    var name: String? = null
    var capital: String? = null
    var population: Int? = null
    var area: Double? = null
    var currencies: List<String>? = null
    var timezones: List<String>? = null
    var languages: List<String>? = null
    var numericCode: String? = null
    var flagImageUrl: String? = null

    constructor(parcel: Parcel) : this() {
        this.name = parcel.readString()
        this.capital = parcel.readString()
        this.population = parcel.readInt()
        this.area = parcel.readDouble()
        this.currencies = parcel.createStringArrayList()
        this.timezones = parcel.createStringArrayList()
        this.languages = parcel.createStringArrayList()
        this.numericCode = parcel.readString()
        this.flagImageUrl = parcel.readString()
    }

    constructor(dto: CountryDto): this() {
        this.name = dto.name
        this.capital = dto.capital
        this.population = dto.population
        this.area = dto.area
        this.currencies = dto.currencies.map { currencyDto -> currencyDto.name }.toList()
        this.timezones = dto.timezones
        this.languages = dto.languages.map { languageDto -> languageDto.name }
        this.numericCode = dto.numericCode
        this.flagImageUrl = dto.flag
    }

    override fun writeToParcel(dest: Parcel?, flag: Int) {
        dest?.writeString(name)
        dest?.writeString(capital)
        population?.let { dest?.writeInt(it) }
        area?.let { dest?.writeDouble(it) }
        dest?.writeStringList(currencies)
        dest?.writeStringList(timezones)
        dest?.writeStringList(languages)
        dest?.writeString(numericCode)
        dest?.writeString(flagImageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(parcel: Parcel): Country {
            return Country(parcel)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}