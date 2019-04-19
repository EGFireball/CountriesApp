package dimi.com.countryapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryDto implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topLevelDomain")
    @Expose
    private List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
    @SerializedName("callingCodes")
    @Expose
    private List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("altSpellings")
    @Expose
    private List<String> altSpellings = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;
    @SerializedName("demonym")
    @Expose
    private String demonym;
    @SerializedName("area")
    @Expose
    private Double area;
    @SerializedName("gini")
    @Expose
    private Double gini;
    @SerializedName("timezones")
    @Expose
    private List<String> timezones = null;
    @SerializedName("borders")
    @Expose
    private List<String> borders = null;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
    @SerializedName("currencies")
    @Expose
    private List<CurrencyDto> currencies = null;
    @SerializedName("languages")
    @Expose
    private List<LanguageDto> languages = null;
    @SerializedName("translations")
    @Expose
    private TranslationsDto translations;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("regionalBlocs")
    @Expose
    private List<RegionalBlocDto> regionalBlocs = null;
    @SerializedName("cioc")
    @Expose
    private String cioc;
    public final static Parcelable.Creator<CountryDto> CREATOR = new Creator<CountryDto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CountryDto createFromParcel(Parcel in) {
            return new CountryDto(in);
        }

        public CountryDto[] newArray(int size) {
            return (new CountryDto[size]);
        }

    };

    protected CountryDto(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.topLevelDomain, (java.lang.String.class.getClassLoader()));
        this.alpha2Code = ((String) in.readValue((String.class.getClassLoader())));
        this.alpha3Code = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.callingCodes, (java.lang.String.class.getClassLoader()));
        this.capital = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.altSpellings, (java.lang.String.class.getClassLoader()));
        this.region = ((String) in.readValue((String.class.getClassLoader())));
        this.subregion = ((String) in.readValue((String.class.getClassLoader())));
        this.population = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.latlng, (java.lang.Double.class.getClassLoader()));
        this.demonym = ((String) in.readValue((String.class.getClassLoader())));
        this.area = ((Double) in.readValue((Double.class.getClassLoader())));
        this.gini = ((Double) in.readValue((Double.class.getClassLoader())));
        in.readList(this.timezones, (java.lang.String.class.getClassLoader()));
        in.readList(this.borders, (java.lang.String.class.getClassLoader()));
        this.nativeName = ((String) in.readValue((String.class.getClassLoader())));
        this.numericCode = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.currencies, (CurrencyDto.class.getClassLoader()));
        in.readList(this.languages, (LanguageDto.class.getClassLoader()));
        this.translations = ((TranslationsDto) in.readValue((TranslationsDto.class.getClassLoader())));
        this.flag = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.regionalBlocs, (RegionalBlocDto.class.getClassLoader()));
        this.cioc = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CountryDto() {
    }

    /**
     *
     * @param cioc
     * @param region
     * @param currencies
     * @param numericCode
     * @param callingCodes
     * @param alpha3Code
     * @param nativeName
     * @param topLevelDomain
     * @param alpha2Code
     * @param capital
     * @param altSpellings
     * @param languages
     * @param subregion
     * @param timezones
     * @param flag
     * @param area
     * @param name
     * @param regionalBlocs
     * @param translations
     * @param latlng
     * @param demonym
     * @param gini
     * @param borders
     * @param population
     */
    public CountryDto(String name,
                      List<String> topLevelDomain,
                      String alpha2Code,
                      String alpha3Code,
                      List<String> callingCodes,
                      String capital,
                      List<String> altSpellings,
                      String region,
                      String subregion,
                      Integer population,
                      List<Double> latlng,
                      String demonym,
                      Double area,
                      Double gini,
                      List<String> timezones,
                      List<String> borders,
                      String nativeName,
                      String numericCode,
                      List<CurrencyDto> currencies,
                      List<LanguageDto> languages,
                      TranslationsDto translations,
                      String flag,
                      List<RegionalBlocDto> regionalBlocs,
                      String cioc) {
        super();
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translations = translations;
        this.flag = flag;
        this.regionalBlocs = regionalBlocs;
        this.cioc = cioc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }

    public List<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDto> languages) {
        this.languages = languages;
    }

    public TranslationsDto getTranslations() {
        return translations;
    }

    public void setTranslations(TranslationsDto translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBlocDto> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBlocDto> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeList(topLevelDomain);
        dest.writeValue(alpha2Code);
        dest.writeValue(alpha3Code);
        dest.writeList(callingCodes);
        dest.writeValue(capital);
        dest.writeList(altSpellings);
        dest.writeValue(region);
        dest.writeValue(subregion);
        dest.writeValue(population);
        dest.writeList(latlng);
        dest.writeValue(demonym);
        dest.writeValue(area);
        dest.writeValue(gini);
        dest.writeList(timezones);
        dest.writeList(borders);
        dest.writeValue(nativeName);
        dest.writeValue(numericCode);
        dest.writeList(currencies);
        dest.writeList(languages);
        dest.writeValue(translations);
        dest.writeValue(flag);
        dest.writeList(regionalBlocs);
        dest.writeValue(cioc);
    }

    public int describeContents() {
        return 0;
    }

}