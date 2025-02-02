package com.smait.quyengop.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.smait.quyengop.Controller.Danhmuc.CustomExpandableListAdapter;
import com.smait.quyengop.Controller.Danhmuc.ExpandableListDataPump;
import com.smait.quyengop.Controller.Danhmuc.NameProduct;
import com.smait.quyengop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterPostDonation extends AppCompatActivity {


    String tokenMain;
    Button btnNext;
    String address = "";
    int count = 0;
    String TypeAuthor;
    CheckedTextView checkedTextView;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    ArrayList<NameProduct> nameProductArrayList;
    ArrayList<String> arrayListName = new ArrayList<>();
    ArrayList<String> arrayListCatogory = new ArrayList<>();

    SharedPreferences preferences;
    String sPositionItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_post_donation);

        preferences = getSharedPreferences("dataPositionItemChecked", MODE_PRIVATE);

        //        toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_anouncement);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_listivew_danhmuc);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        nameProductArrayList = new ArrayList<NameProduct>();
//
//        if (preferences != null) {
//            sPositionItem = preferences.getString("postionItem", "");
//            if (!sPositionItem.equals(",0")) {
//                Log.d("String position Item", sPositionItem + " ôi nô, yêu bông");
//                String[] listPosition = sPositionItem.split(",");
//                for (int i = 1; i < listPosition.length; i++) {
//                    String sItem = listPosition[i];
//                    Log.d("Vị trí item", sItem);
//                    int item = Integer.parseInt(sItem);
//                    View view = expandableListView.getChildAt(item);
//                    CheckedTextView ck = view.findViewById(R.id.checkList);
//                    ck.setChecked(true);
//                }
//            }
//        }

        CheckedTextView checkedTextView1 = (CheckedTextView) expandableListView.getChildAt(2);


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                checkedTextView = v.findViewById(R.id.checkList);


                if (checkedTextView.isChecked()) {
                    checkedTextView.setChecked(false);
                    arrayListName.remove(count-1);
                    arrayListCatogory.remove(count-1);
                    count = count-1;
                }
                else {
                    checkedTextView.setChecked(true);
                    count = count+1;
                    NameProduct nameProduct = new NameProduct(expandableListTitle.get(groupPosition), expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                    nameProductArrayList.add(nameProduct);
                    arrayListName.add(nameProduct.getNameProduct());
                    arrayListCatogory.add(nameProduct.getCategory());
                    sPositionItem = sPositionItem + "," + childPosition;



                }

                return true;
            }
        });




//        *************************Next button

        btnNext = (Button)findViewById(R.id.danhmuc_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count != 0) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("postionItem", sPositionItem);
                    editor.commit();
                    //gửi data sang activity Detail*************************************************************
                    Intent intent1 = new Intent(getApplicationContext(), PostDonation.class);
                    intent1.putExtra("ListName", arrayListName);
                    intent1.putExtra("ListCatogary", arrayListCatogory);
                    intent1.putExtra("Filter", "loc");
                    startActivity(intent1);
                    finish();

//                        *************************************************************
                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng chọn ít nhất 1 mục", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(FilterPostDonation.this, PostDonation.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}