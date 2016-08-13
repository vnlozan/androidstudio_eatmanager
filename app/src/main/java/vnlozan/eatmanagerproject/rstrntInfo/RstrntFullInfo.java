package vnlozan.eatmanagerproject.rstrntInfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by V on 12.07.2016.
 */
public class RstrntFullInfo implements Parcelable {
    private String rstrntName;
    private String rstrntAdress;
    private String rstrntTelephone;
    private String rstrntPhotoURL;
    private String rstrntStory;
    private String rstrntCoordX;
    private String rstrntCoordY;

    public RstrntFullInfo(String rstrntName, String rstrntAdress, String rstrntTelephone,
                          String rstrntPhotoURL,String rstrntStory,String rstrntCoordX,String rstrntCoordY) {
        this.rstrntName = rstrntName;
        this.rstrntAdress = rstrntAdress;
        this.rstrntTelephone = rstrntTelephone;
        this.rstrntCoordX=rstrntCoordX;
        this.rstrntCoordY=rstrntCoordY;
    }

    public String getRstrntName() {
        return rstrntName;
    }
    public String getRstrntAdress() {
        return rstrntAdress;
    }
    public String getRstrntTelephone() {
        return rstrntTelephone;
    }
    public String getRstrntPhotoURL() {
        return rstrntPhotoURL;
    }
    public String getRstrntStory() {
        return rstrntStory;
    }
    public String getRstrntCoordX() {
        return rstrntCoordX;
    }
    public String getRstrntCoordY() {
        return rstrntCoordY;
    }

    protected RstrntFullInfo(Parcel in) {
        rstrntName = in.readString();
        rstrntAdress = in.readString();
        rstrntTelephone = in.readString();
        rstrntPhotoURL = in.readString();
        rstrntStory = in.readString();
        rstrntCoordY=in.readString();
        rstrntCoordX=in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rstrntName);
        dest.writeString(rstrntAdress);
        dest.writeString(rstrntTelephone);
        dest.writeString(rstrntPhotoURL);
        dest.writeString(rstrntStory);
        dest.writeString(rstrntCoordX);
        dest.writeString(rstrntCoordY);
    }
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RstrntFullInfo> CREATOR = new Parcelable.Creator<RstrntFullInfo>() {
        @Override
        public RstrntFullInfo createFromParcel(Parcel in) {
            return new RstrntFullInfo(in);
        }

        @Override
        public RstrntFullInfo[] newArray(int size) {
            return new RstrntFullInfo[size];
        }
    };
}