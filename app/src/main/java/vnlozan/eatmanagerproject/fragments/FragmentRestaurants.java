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

import vnlozan.eatmanagerproject.adapters.RstrntAdapter;
import vnlozan.eatmanagerproject.DataBaseHelper;
import vnlozan.eatmanagerproject.activities.RstrntReview;
import vnlozan.eatmanagerproject.R;
import vnlozan.eatmanagerproject.rstrntInfo.RstrntFullInfo;

/**
 * Created by V on 29.06.2016.
 */
public class FragmentRestaurants extends Fragment {
    private Cursor cursor = null;                       //query using
    private DataBaseHelper DataBase;                    //database executing
    private AutoCompleteTextView inputRstrnt;           //searching list
    private Context ctx;                                //context "this"
    private Button Find;                                //button find restaurants
    private List<String> searchingElements;             //list of elements to search
    private String tableName;                           //tableName resMenu
    private ListView listViewRstrnts;                    //listView for showing restaurants list
    private RstrntAdapter rstrntAdapter;            //Custom Adapter for fillin the list of restaurants
    private ArrayAdapter<String> adapterSearchSpinner;  //Adapter for the Searching Spinner
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ctx=activity;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tableName="restaurants";
        Find=(Button)getView().findViewById(R.id.buttonFindRstrnt);
        inputRstrnt=(AutoCompleteTextView)getView().findViewById(R.id.ACTV_rstrnts);
        DataBase = new DataBaseHelper(ctx);
        try {
            DataBase.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listViewRstrnts = (ListView)getView().findViewById(R.id.listViewRstrnts);
        //----------forming searchListHelper using query
        searchingElements=formSearchList();
        adapterSearchSpinner = new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item,searchingElements);
        inputRstrnt.setAdapter(adapterSearchSpinner);
        //----------restaurants Adapter
        rstrntAdapter=new RstrntAdapter(ctx,R.layout.list_element_rstrnts);
        listViewRstrnts.setAdapter(rstrntAdapter);
        listViewRstrnts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RstrntFullInfo rm = rstrntAdapter.getItem(position);
                Intent i = new Intent(ctx, RstrntReview.class);
                i.putExtra("rstrntInfo",rm);
                startActivity(i);
            }
        });
        //----------button method to find all restaurants by their name
        Find.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rstrntAdapter.getCount()>0){ rstrntAdapter.clear();rstrntAdapter.notifyDataSetChanged();}
                Toast.makeText(ctx, "Succesfully clicked", Toast.LENGTH_SHORT).show();
                String rstrntName = inputRstrnt.getText().toString();
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
                    String selection = "rstrntName=?";
                    String [] selectionArgs = new String[] { rstrntName };
                    cursor = DataBase.query(tableName,null,selection,selectionArgs, null, null, null);
                    //response reading
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            String str;
                            do {
                                rstrntAdapter.add(new RstrntFullInfo(
                                        cursor.getString(cursor.getColumnIndex("rstrntName")),
                                        cursor.getString(cursor.getColumnIndex("rstrntAdress")),
                                        cursor.getString(cursor.getColumnIndex("rstrntTel")),
                                        "photoURL",
                                        "Story",
                                        cursor.getString(cursor.getColumnIndex("rstrntCoordX")),
                                        cursor.getString(cursor.getColumnIndex("rstrntCoordY"))
                                ));
                                rstrntAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment_layout_rstrnts, container, false);
        return view;
    }
    private List<String> formSearchList()
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
            cursor = DataBase.query("restaurants",null,null,null, null, null, null);
            //response reading
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String str;
                    do {
                        System.out.println(cursor.getString(cursor.getColumnIndex("rstrntName")));
                        list.add(cursor.getString(cursor.getColumnIndex("rstrntName")));
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