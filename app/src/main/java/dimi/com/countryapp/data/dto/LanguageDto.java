package dimi.com.countryapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageDto implements Parcelable
{

    @SerializedName("iso639_1")
    @Expose
    private String iso6391;
    @SerializedName("iso639_2")
    @Expose
    private String iso6392;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    public final static Parcelable.Creator<LanguageDto> CREATOR = new Creator<LanguageDto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LanguageDto createFromParcel(Parcel in) {
            return new LanguageDto(in);
        }

        public LanguageDto[] newArray(int size) {
            return (new LanguageDto[size]);
        }

    };

    protected LanguageDto(Parcel in) {
        this.iso6391 = ((String) in.readValue((String.class.getClassLoader())));
        this.iso6392 = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nativeName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public LanguageDto() {
    }

    /**
     *
     * @param iso6391
     * @param iso6392
     * @param name
     * @param nativeName
     */
    public LanguageDto(String iso6391, String iso6392, String name, String nativeName) {
        super();
        this.iso6391 = iso6391;
        this.iso6392 = iso6392;
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6392() {
        return iso6392;
    }

    public void setIso6392(String iso6392) {
        this.iso6392 = iso6392;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iso6391);
        dest.writeValue(iso6392);
        dest.writeValue(name);
        dest.writeValue(nativeName);
    }

    public int describeContents() {
        return 0;
    }

}