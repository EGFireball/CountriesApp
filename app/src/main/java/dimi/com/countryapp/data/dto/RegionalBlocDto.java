package dimi.com.countryapp.data.dto;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionalBlocDto implements Parcelable
{

    @SerializedName("acronym")
    @Expose
    private String acronym;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otherAcronyms")
    @Expose
    private List<Object> otherAcronyms = null;
    @SerializedName("otherNames")
    @Expose
    private List<Object> otherNames = null;
    public final static Parcelable.Creator<RegionalBlocDto> CREATOR = new Creator<RegionalBlocDto>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RegionalBlocDto createFromParcel(Parcel in) {
            return new RegionalBlocDto(in);
        }

        public RegionalBlocDto[] newArray(int size) {
            return (new RegionalBlocDto[size]);
        }

    }
            ;

    protected RegionalBlocDto(Parcel in) {
        this.acronym = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.otherAcronyms, (java.lang.Object.class.getClassLoader()));
        in.readList(this.otherNames, (java.lang.Object.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public RegionalBlocDto() {
    }

    /**
     *
     * @param otherAcronyms
     * @param acronym
     * @param name
     * @param otherNames
     */
    public RegionalBlocDto(String acronym, String name, List<Object> otherAcronyms, List<Object> otherNames) {
        super();
        this.acronym = acronym;
        this.name = name;
        this.otherAcronyms = otherAcronyms;
        this.otherNames = otherNames;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<Object> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<Object> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<Object> otherNames) {
        this.otherNames = otherNames;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(acronym);
        dest.writeValue(name);
        dest.writeList(otherAcronyms);
        dest.writeList(otherNames);
    }

    public int describeContents() {
        return 0;
    }
}
