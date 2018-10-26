package rikkeisoft.nguyenducdung.com.contactrecycleview.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Collections;

import rikkeisoft.nguyenducdung.com.contactrecycleview.custom.adapter.ContactAdapter;
import rikkeisoft.nguyenducdung.com.contactrecycleview.R;
import rikkeisoft.nguyenducdung.com.contactrecycleview.model.ItemContact;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemContact> itemContacts;
    private Switch stChange;
    private ImageButton btnAddContact;
    private Button btnAddDialogClose;
    private Button btnAddDialogSave;
    private Button btnShowDialogDelete;
    private Button btnShowDialogSave;
    private EditText etAddPhone;
    private EditText etAddName;
    private EditText etShowName;
    private EditText etShowPhone;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();
        initViewLinear();
        stChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    initViewGrid();
                } else {
                    initViewLinear();
                }
            }
        });
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });

    }

    private void init() {
        stChange = findViewById(R.id.st_changed);
        btnAddContact = findViewById(R.id.btn_add_contact);

    }

    private void initViewLinear() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_contact);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        contactAdapter = new ContactAdapter(itemContacts, getApplicationContext(), new ContactAdapter.ItemClick() {
            @Override
            public void onClickItem(int i) {
//                Toast.makeText(getApplicationContext(), i+"", Toast.LENGTH_SHORT).show();
                showContact(i);
            }
        });
        recyclerView.setAdapter(contactAdapter);
    }

    private void initViewGrid() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_contact);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        contactAdapter = new ContactAdapter(itemContacts, getApplicationContext(), new ContactAdapter.ItemClick() {
            @Override
            public void onClickItem(int i) {
                showContact(i);
            }
        });
        recyclerView.setAdapter(contactAdapter);
    }

    private void initData() {
        itemContacts = new ArrayList<>();
        itemContacts.add(new ItemContact("01673497220", "Nguyễn Đức Dũng"));
        itemContacts.add(new ItemContact("0944281895", "Nguyễn Đức "));
        itemContacts.add(new ItemContact("0918273645", "Nguyễn Thanh"));
        itemContacts.add(new ItemContact("0373198202", "Nguyên Thị"));
        itemContacts.add(new ItemContact("12239482394", "Hot girl"));

        itemContacts.add(new ItemContact("109402049", "Abc"));
        itemContacts.add(new ItemContact("028493843", "Nguyễn Đức Dũng6"));
        itemContacts.add(new ItemContact("341241432", "Nguyễn Đức Dũng7"));
        itemContacts.add(new ItemContact("412324213412", "Nguyễn Đức Dũng8"));
        itemContacts.add(new ItemContact("41233434344", "Nguyễn Đức Dũng9"));

        itemContacts.add(new ItemContact("3412434324", "Nguyễn Đức Dũng10"));
        itemContacts.add(new ItemContact("42134123434", "Nguyễn Đức Dũng11"));
        itemContacts.add(new ItemContact("12343432414", "Nguyễn Đức Dũng12"));
        itemContacts.add(new ItemContact("4341412343", "Nguyễn Đức Dũng13"));
        itemContacts.add(new ItemContact("41234344444", "Nguyễn Đức Dũng14"));

    }

    private void addDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Thêm mới danh bạ");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_contact);
        btnAddDialogClose = dialog.findViewById(R.id.btn_add_dialog_close);
        btnAddDialogSave = dialog.findViewById(R.id.btn_add_dialog_save);
        etAddName = dialog.findViewById(R.id.et_add_name);
        etAddPhone = dialog.findViewById(R.id.et_add_phone);
        btnAddDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnAddDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etAddName.getText().toString().trim();
                String phone = etAddPhone.getText().toString().trim();
                if (name != "" && phone != "") {
                    itemContacts.add(new ItemContact(phone, name));
                    contactAdapter.dataAdd(itemContacts.size()-1);
                    Collections.reverse(itemContacts);
                    dialog.cancel();
                } else {
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    public void showContact(final int i) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Hiển thị danh bạ");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_show_contact);
        btnShowDialogSave = dialog.findViewById(R.id.btn_show_dialog_save);
        btnShowDialogDelete = dialog.findViewById(R.id.btn_show_dialog_delete);
        etShowName = dialog.findViewById(R.id.et_show_name);
        String name1 = itemContacts.get(i).getNameContact();
        etShowName.setText(name1);
        etShowPhone = dialog.findViewById(R.id.et_show_phone);
        String phone1 = itemContacts.get(i).getPhoneContact();
        etShowPhone.setText(phone1);
        btnShowDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etShowName.getText().toString().trim();
                String phone = etShowPhone.getText().toString().trim();
                if (name != itemContacts.get(i).getNameContact() && phone != itemContacts.get(i).getPhoneContact()) {
                    itemContacts.get(i).setNameContact(name);
                    itemContacts.get(i).setPhoneContact(phone);
                    contactAdapter.dataChange(i);
                    Collections.reverse(itemContacts);
                    dialog.cancel();
                } else {
                    dialog.cancel();
                }
            }
        });
        btnShowDialogDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemContacts.remove(i);
                contactAdapter.dataRemove(i);
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
