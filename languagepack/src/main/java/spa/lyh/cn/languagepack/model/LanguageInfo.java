package spa.lyh.cn.languagepack.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LanguageInfo implements Parcelable {
    public String name;
    public String language;
    public String country;
    public String content;

    public LanguageInfo(){}

    protected LanguageInfo(Parcel in) {
        name = in.readString();
        language = in.readString();
        country = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(language);
        dest.writeString(country);
        dest.writeString(content);
    }

    public static final Creator<LanguageInfo> CREATOR = new Creator<LanguageInfo>() {
        @Override
        public LanguageInfo createFromParcel(Parcel in) {
            return new LanguageInfo(in);
        }

        @Override
        public LanguageInfo[] newArray(int size) {
            return new LanguageInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
