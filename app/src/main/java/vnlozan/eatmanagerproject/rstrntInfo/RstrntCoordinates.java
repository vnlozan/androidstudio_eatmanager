package vnlozan.eatmanagerproject.rstrntInfo;

import java.io.Serializable;

/**
 * Created by V on 11.07.2016.
 */
public class RstrntCoordinates implements Serializable {
    private float coordX;
    private float coordY;
    private String name;

    public RstrntCoordinates(String coordX, String coordY,String name) {
        this.coordX = Float.parseFloat(coordX);
        this.coordY = Float.parseFloat(coordY);
        this.name=name;
    }
    public float getCoordX() {
        return coordX;
    }
    public float getCoordY() {
        return coordY;
    }
    public String getName() {
        return name;
    }
}
