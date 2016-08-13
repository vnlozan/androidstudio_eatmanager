package vnlozan.eatmanagerproject.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vnlozan.eatmanagerproject.adapters.DishesAdapter;
import vnlozan.eatmanagerproject.DataBaseHelper;
import vnlozan.eatmanagerproject.activities.DishReview;
import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.dishInfo.DishFullInfo;

/**
 * Created by V on 29.06.2016.
 */
public class FragmentTypes extends Fragment {
    private Cursor cursor = null;                       //query using
    private DataBaseHelper DataBase;                    //database executing
    private AutoCompleteTextView inputDishesType;           //searching list
    private Context ctx;                                //context "this"
    private Button Find;                                //button find dishes
    private List<String> searchingElements;             //list of elements to search
    private String tableName;                           //tableName resMenu
    private ListView listViewDishes;                    //listView for showing dishes list
    private DishesAdapter dishesAdapter;            //Custom Adapter for fillin the list of dishes
    private ArrayAdapter<String> adapterSearchSpinner;  //Adapter for the Searching Spinner
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ctx=activity;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tableName="resMenu";
        Find=(Button)getView().findViewById(R.id.buttonFindDishesType);
        inputDishesType=(AutoCompleteTextView)getView().findViewById(R.id.ACTV_types);
        DataBase = new DataBaseHelper(ctx);
        try {
            DataBase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listViewDishes = (ListView)getView().findViewById(R.id.listViewDishesType);
        //----------forming searchListHelper using query
        searchingElements=formSearchList();
        adapterSearchSpinner = new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item,searchingElements);
        inputDishesType.setAdapter(adapterSearchSpinner);
        //----------dishes Adapter
        dishesAdapter=new DishesAdapter(ctx,R.layout.list_element_dishes);
        listViewDishes.setAdapter(dishesAdapter);
        listViewDishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DishFullInfo rm = dishesAdapter.getItem(position);
                Intent i = new Intent(ctx, DishReview.class);
                i.putExtra("dishInfo",rm);
                startActivity(i);
            }
        });
        //----------button method to find all dishes by their name
        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(dishesAdapter.getCount()>0){ dishesAdapter.clear();dishesAdapter.notifyDataSetChanged();}
                Toast.makeText(ctx, "Succesfully clicked", Toast.LENGTH_SHORT).show();
                String dishesType = inputDishesType.getText().toString();
                //----------opening db
                try
                {
                    DataBase.openDataBase();
                }
                catch (SQLException sqle)
                {
                    throw sqle;
                }
                finally
                {
                    //----------creating query
                    String selection = "dishType=?";
                    String [] selectionArgs = new String[] { dishesType };
                    cursor = DataBase.query(tableName,null,selection,selectionArgs, null, null, null);
                    //response reading
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str;
                            do {
                                dishesAdapter.add(new DishFullInfo(
                                        cursor.getString(cursor.getColumnIndex("dishName")),
                                        cursor.getString(cursor.getColumnIndex("dishType")),
                                        cursor.getString(cursor.getColumnIndex("restaurantName")),
                                        cursor.getString(cursor.getColumnIndex("dishPrice")),
                                        "photoURL",
                                        cursor.getString(cursor.getColumnIndex("dishCategory")),
                                        cursor.getString(cursor.getColumnIndex("dishDescription")),
                                        cursor.getString(cursor.getColumnIndex("dishCkngTime"))
                                ));
                                dishesAdapter.notifyDataSetChanged();
                            } while (cursor.moveToNext());
                        }
                        cursor.close();
                    } else
                        System.out.println("Cursor is null");
                    DataBase.close();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_types, container, false);
        return view;
    }
    private List<String>formSearchList()
    {
        List<String>data=new ArrayList<String>();
        Set<String> list=new HashSet<String>();
        //----------opening db
        try
        {
            DataBase.openDataBase();
        }
        catch (SQLException sqle)
        {
            throw sqle;
        }
        finally {
            //----------creating query
            cursor = DataBase.query("resMenu",null,null,null, null, null, null);
            //response reading
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String str;
                    do {
                        System.out.println(cursor.getString(cursor.getColumnIndex("dishName")));
                        list.add(cursor.getString(cursor.getColumnIndex("dishType")));
                    } while (cursor.moveToNext());
                }
                cursor.close();
            } else
                System.out.println("Cursor is null");
            System.out.println(list);
            data.addAll(list);
            DataBase.close();
        }
        return data;
    }
}
