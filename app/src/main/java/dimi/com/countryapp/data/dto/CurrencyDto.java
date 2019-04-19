package dimi.com.countryapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyDto implements Parcelable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    public final static Parcelable.Creator<CurrencyDto> CREATOR = new Creator<CurrencyDto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CurrencyDto createFromParcel(Parcel in) {
            return new CurrencyDto(in);
        }

        public CurrencyDto[] newArray(int size) {
            return (new CurrencyDto[size]);
        }

    };

    protected CurrencyDto(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.symbol = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrencyDto() {
    }

    /**
     *
     * @param symbol
     * @param name
     * @param code
     */
    public CurrencyDto(String code, String name, String symbol) {
        super();
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(name);
        dest.writeValue(symbol);
    }

    public int describeContents() {
        return 0;
    }

}