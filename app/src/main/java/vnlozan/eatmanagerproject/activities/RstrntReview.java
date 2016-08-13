package vnlozan.eatmanagerproject.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vnlozan.eatmanagerproject.DataBaseHelper;
import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntCoordinates;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntFullInfo;

/**
 * Created by V on 12.07.2016.
 */
public class RstrntReview extends AppCompatActivity {
    private RstrntFullInfo rstrntInfo;
    private Cursor cursor = null;
    private TextView rstrntName;
    private TextView rstrntAdress;
    private TextView rstrntTelephone;
    private Button buttonGetLocation;
    private DataBaseHelper DataBase;
    private String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rstrnt_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rstrntInfo = getIntent().getExtras().getParcelable("rstrntInfo");
        //-----------find TextView elements
        rstrntName = (TextView) findViewById(R.id.rstrntReviewName);
        rstrntAdress = (TextView) findViewById(R.id.rstrntReviewAdress);
        rstrntTelephone = (TextView) findViewById(R.id.rstrntReviewTelephone);
        buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation2);
        //-----------set values of TextViews
        rstrntName.setText("Restaurant Name: " + rstrntInfo.getRstrntName());
        rstrntAdress.setText("Restaurant Adress: " + rstrntInfo.getRstrntAdress());
        rstrntTelephone.setText("Restaurant Telephone: " + rstrntInfo.getRstrntTelephone());
        tableName = "restaurants";
        //-----------button Click Listener
        buttonGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RstrntCoordinates> list=new ArrayList<RstrntCoordinates>();
                System.out.println(rstrntInfo.getRstrntCoordX());
                System.out.println(rstrntInfo.getRstrntCoordY());
                list.add(new RstrntCoordinates(rstrntInfo.getRstrntCoordX(),
                        rstrntInfo.getRstrntCoordY(),
                        rstrntName.getText().toString()));
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("ARRAYLIST", (Serializable) list);
                intent.putExtra("LIST", b);
                startActivity(intent);
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
