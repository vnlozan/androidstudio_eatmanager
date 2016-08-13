package vnlozan.eatmanagerproject.dishInfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class describes full dishes' information
 */
public class DishFullInfo implements Parcelable {
    private String dishCategory;
    private String dishDescription;
    private String ckngTime;
    private String dishName;
    private String dishType;
    private String restaurantName;
    private String dishPrice;
    private String dishPhotoURL;
    public DishFullInfo(String dishName, String dishType, String restaurantName, String dishPrice,
                        String dishPhotoURL, String dishCategory, String dishDescription, String ckngTime) {
        this.dishName = dishName;
        this.dishType = dishType;
        this.restaurantName = restaurantName;
        this.dishPrice = dishPrice;
        this.dishPhotoURL=dishPhotoURL;
        this.dishCategory = dishCategory;
        this.dishDescription = dishDescription;
        this.ckngTime = ckngTime;
    }
    public String getDishName() {
        return dishName;
    }
    public String getDishType() {
        return dishType;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public String getDishPrice() {
        return dishPrice;
    }
    public String getDishPhotoURL() {
        return dishPhotoURL;
    }
    public String getDishCategory() {
        return dishCategory;
    }
    public String getDishDescription() {
        return dishDescription;
    }
    public String getCkngTime() {
        return ckngTime;
    }
    protected DishFullInfo(Parcel in) {
        dishCategory = in.readString();
        dishDescription = in.readString();
        ckngTime = in.readString();
        dishName = in.readString();
        dishType = in.readString();
        restaurantName = in.readString();
        dishPrice = in.readString();
        dishPhotoURL = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dishCategory);
        dest.writeString(dishDescription);
        dest.writeString(ckngTime);
        dest.writeString(dishName);
        dest.writeString(dishType);
        dest.writeString(restaurantName);
        dest.writeString(dishPrice);
        dest.writeString(dishPhotoURL);
    }
    @SuppressWarnings("unused")
    public static final Creator<DishFullInfo> CREATOR = new Creator<DishFullInfo>() {
        @Override
        public DishFullInfo createFromParcel(Parcel in) {
            return new DishFullInfo(in);
        }

        @Override
        public DishFullInfo[] newArray(int size) {
            return new DishFullInfo[size];
        }
    };
}