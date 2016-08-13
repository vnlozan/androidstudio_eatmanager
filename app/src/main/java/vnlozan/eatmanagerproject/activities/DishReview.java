package vnlozan.eatmanagerproject.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vnlozan.eatmanagerproject.DataBaseHelper;
import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntCoordinates;
import vnlozan.eatmanagerproject.dishInfo.DishFullInfo;

public class DishReview extends AppCompatActivity {
    private DishFullInfo dishInfo;
    private Cursor cursor = null;
    private TextView dishName;
    private TextView dishType;
    private TextView dishDescription;
    private TextView rstrntName;
    private TextView dishPrice;
    private Button buttonGetLocation;
    private DataBaseHelper DataBase;
    private String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dish_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dishInfo=getIntent().getExtras().getParcelable("dishInfo");
        //-----------find TextView elements
        dishName=(TextView)findViewById(R.id.dishReviewDishName);
        dishType=(TextView)findViewById(R.id.dishReviewDishType);
        dishDescription=(TextView)findViewById(R.id.dishReviewDishDescription);
        dishPrice=(TextView)findViewById(R.id.dishReviewDishPrice);
        rstrntName=(TextView)findViewById(R.id.dishReviewRstrntName);
        buttonGetLocation=(Button)findViewById(R.id.buttonGetLocation);
        //-----------set values of TextViews
        dishName.setText("Dish Name: "+dishInfo.getDishName());
        dishType.setText("Dish Type: "+dishInfo.getDishType());
        dishDescription.setText("Dish Description: "+dishInfo.getDishDescription());
        rstrntName.setText("Restaurant Name: "+dishInfo.getRestaurantName());
        dishPrice.setText("Dish Price: "+dishInfo.getDishPrice());
        tableName="restaurants";
        //-----------button Click Listener
        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = "rstrntName=?";
                System.out.println(dishInfo.getRestaurantName());
                String [] selectionArgs = new String[] { dishInfo.getRestaurantName() };
                List<RstrntCoordinates> list=new ArrayList<RstrntCoordinates>();
                DataBase = new DataBaseHelper(getBaseContext());
                try {
                    DataBase.createDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try
                {
                    DataBase.openDataBase();
                }
                catch (SQLException sqle)
                {
                    throw sqle;
                }
                finally {
                    cursor = DataBase.query("restaurants",null,selection,selectionArgs, null, null, null);
                    //response reading
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str;
                            do {
                                System.out.println(cursor.getString(cursor.getColumnIndex("rstrntCoordX")));
                                System.out.println(cursor.getString(cursor.getColumnIndex("rstrntCoordY")));
                                list.add(new RstrntCoordinates(cursor.getString(
                                        cursor.getColumnIndex("rstrntCoordX")),
                                        cursor.getString(cursor.getColumnIndex("rstrntCoordY")),
                                        rstrntName.getText().toString()));
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                    } else
                        System.out.println("Cursor is null");
                    Intent intent=new Intent(getBaseContext(),MapsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("ARRAYLIST", (Serializable) list);
                    intent.putExtra("LIST", b);
                    startActivity(intent);
                    DataBase.close();
                }
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();                                       // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
