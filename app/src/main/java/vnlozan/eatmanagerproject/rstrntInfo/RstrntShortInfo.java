package vnlozan.eatmanagerproject.rstrntInfo;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by V on 12.07.2016.
 */
public class RstrntShortInfo {
    private TextView rstrntName;
    private TextView rstrntAdress;
    private TextView rstrntTelephone;
    private ImageView rstrntPhoto;

    public RstrntShortInfo(TextView rstrntName, TextView rstrntAdress, TextView rstrntTelephone) {
        this.rstrntName = rstrntName;
        this.rstrntAdress = rstrntAdress;
        this.rstrntTelephone = rstrntTelephone;
    }
    public void setRstrntName(String rstrntName) {
        this.rstrntName.setText(rstrntName);
    }
    public void setRstrntAdress(String rstrntAdress) {
        this.rstrntAdress.setText(rstrntAdress);
    }
    public void setRstrntTelephone(String rstrntTelephone) {
        this.rstrntTelephone.setText(rstrntTelephone);
    }
    public void setRstrntPhoto(ImageView rstrntPhoto) {
        this.rstrntPhoto = rstrntPhoto;
    }
}
