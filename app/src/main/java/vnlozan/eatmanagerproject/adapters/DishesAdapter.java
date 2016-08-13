package vnlozan.eatmanagerproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.dishInfo.DishFullInfo;
import vnlozan.eatmanagerproject.dishInfo.DishShortInfo;

/**
 * Created by V on 09.07.2016.
 */
public class DishesAdapter extends ArrayAdapter<DishFullInfo> {
    private final Context context;
    private final int resource;
    public DishesAdapter(Context context, int resource) {
        super(context,resource);
        this.context = context;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DishFullInfo fullDishInformation = getItem(position);
        DishShortInfo shortDishInformation = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            //name,type,rest,price
            shortDishInformation = new DishShortInfo((TextView) convertView.findViewById(R.id.textViewDishesName),
                    (TextView) convertView.findViewById(R.id.textViewDishesType),
                    (TextView) convertView.findViewById(R.id.textViewRstrntName),
                    (TextView) convertView.findViewById(R.id.textViewPrice));
            convertView.setTag(shortDishInformation);
        } else {
            shortDishInformation = (DishShortInfo) convertView.getTag();
        }
        shortDishInformation.setDishName("Dish Name: "+fullDishInformation.getDishName());
        shortDishInformation.setDishType("Dish Type: "+fullDishInformation.getDishType());
        shortDishInformation.setRestaurantName("Restaurant Name: "+fullDishInformation.getRestaurantName());
        shortDishInformation.setDishPrice("Dish Price: "+fullDishInformation.getDishPrice());
        return convertView;
    }
}
