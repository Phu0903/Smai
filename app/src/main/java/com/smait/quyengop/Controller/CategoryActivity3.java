package com.smait.quyengop.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.smait.quyengop.Controller.Danhmuc.CustomExpandableNoCheckBoxListAdapter;
import com.smait.quyengop.Controller.Danhmuc.ExpandableListDataPump;
import com.smait.quyengop.Controller.Danhmuc.NameProduct;
import com.smait.quyengop.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CategoryActivity3 extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category3);

//        toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_anouncement);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_listivew_danhmuc);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableNoCheckBoxListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        nameProductArrayList = new ArrayList<NameProduct>();

        // Nhận địa chỉ*****************************************************************************
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            address = bundle.getString("address");
            Log.d("Address catogory 3", address);
        }
        if (bundle.getString("TypeAuthor") != null) {
            TypeAuthor = bundle.getString("TypeAuthor");

            Log.d("typeAuthor cato", TypeAuthor);
        }
        String token = bundle.getString("token");
        tokenMain = token;
        Log.d("Token catogo", token);
//*************************************************************************************************
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                checkedTextView = v.findViewById(R.id.checkList);
                checkedTextView.setEnabled(false);

                NameProduct nameProduct = new NameProduct(expandableListTitle.get(groupPosition), expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition));
                nameProductArrayList.add(nameProduct);
                arrayListName.add(nameProduct.getNameProduct());
                arrayListCatogory.add(nameProduct.getCategory());
                Intent intent1 = new Intent(getApplicationContext(), Detail2.class);
                intent1.putExtra("ListName", arrayListName);
                intent1.putExtra("ListCatogary", arrayListCatogory);
                intent1.putExtra("TypeAuthor", TypeAuthor);
                intent1.putExtra("address", address);
                intent1.putExtra("token", token);
                startActivity(intent1);

                return true;
            }
        });








//        *************************Next button

//        btnNext = (Button)findViewById(R.id.danhmuc_next);
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (count != 0) {
//
//
//                    //gửi data sang activity Detail*************************************************************
//                    Intent intent1 = new Intent(getApplicationContext(), Detail2.class);
//                    intent1.putExtra("ListName", arrayListName);
//                    intent1.putExtra("ListCatogary", arrayListCatogory);
//                    intent1.putExtra("TypeAuthor", TypeAuthor);
//                    intent1.putExtra("address", address);
//                    intent1.putExtra("token", token);
//                    startActivity(intent1);
////                        *************************************************************
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Vui lòng chọn ít nhất 1 mục", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar_danhmuc, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.button_cancel) {
            ConfirmCancel();
        }
        else {
            finish();
        }
        return true;
    }


    private void ConfirmCancel() {
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("Thông báo!");
        alerDialog.setMessage("Bạn có chắc muốn hủy không?");

        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("Token", tokenMain);
                i.putExtra("message", "OK");
                startActivity(i);
                finish();
            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alerDialog.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(this.getResources().getColor(R.color.teal_700));
    }

}