package vnlozan.eatmanagerproject.dishInfo;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class describes short dishes' information
 * It means it shows one certain dish element in the listView
 */
public class DishShortInfo {
    private TextView dishName;
    private TextView dishType;
    private TextView restaurantName;
    private TextView dishPrice;
    private ImageView dishPhoto;                                                        //Bitmap using
    public DishShortInfo(TextView dishName, TextView dishType, TextView restaurantName,
                         TextView dishPrice) {
        this.dishName = dishName;
        this.dishType = dishType;
        this.restaurantName = restaurantName;
        this.dishPrice = dishPrice;
    }
    public void setDishName(String dishName) {
        this.dishName.setText(dishName);
    }
    public void setDishType(String dishType) {
        this.dishType.setText(dishType);
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName.setText(restaurantName);
    }
    public void setDishPrice(String dishPrice) {
        this.dishPrice.setText(dishPrice);
    }
    public void setDishPhoto(ImageView dishPhoto) {
        this.dishPhoto = dishPhoto;
    }
}
