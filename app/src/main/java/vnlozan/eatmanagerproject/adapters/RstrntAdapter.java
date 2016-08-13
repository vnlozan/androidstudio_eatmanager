package vnlozan.eatmanagerproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntFullInfo;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntShortInfo;

/**
 * Created by V on 12.07.2016.
 */
public class RstrntAdapter extends ArrayAdapter<RstrntFullInfo> {
    private final Context context;
    private final int resource;
    public RstrntAdapter(Context context, int resource) {
        super(context,resource);
        this.context = context;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RstrntFullInfo fullRstrntInfo = getItem(position);
        RstrntShortInfo shortRstrntInfo = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            //name,type,rest,price
            shortRstrntInfo = new RstrntShortInfo((TextView) convertView.findViewById(R.id.textViewRestaurantName),
                    (TextView) convertView.findViewById(R.id.textViewRestaurantTelephone),
                    (TextView) convertView.findViewById(R.id.textViewRstrntAdress));
            convertView.setTag(shortRstrntInfo);
        } else {
            shortRstrntInfo = (RstrntShortInfo) convertView.getTag();
        }
        shortRstrntInfo.setRstrntName("Dish Name: "+fullRstrntInfo.getRstrntName());
        shortRstrntInfo.setRstrntAdress("Dish Type: "+fullRstrntInfo.getRstrntAdress());
        shortRstrntInfo.setRstrntTelephone("Restaurant Name: "+fullRstrntInfo.getRstrntTelephone());
        return convertView;
    }
}
