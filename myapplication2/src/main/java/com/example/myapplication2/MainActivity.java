package com.example.myapplication2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button  create;
    Button  again;
    Button  select;
    Button  insert;
    Button  delete;
    Button  updata;
    MySqliteDatabase    mySqliteDatabase    =   new MySqliteDatabase(this, "book1", null, 2);
    SQLiteDatabase  sqLiteDatabase ;
    Button  litepal;
    Button  add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create  =   findViewById(R.id.Create);
        again   =   findViewById(R.id.agein);
        select  =   findViewById(R.id.Select);
        insert  =   findViewById(R.id.Insert);
        delete  =   findViewById(R.id.Delete);
        updata  =   findViewById(R.id.update);
        litepal =   findViewById(R.id.litepal);
        add     =   findViewById(R.id.addlite);
        add.setOnClickListener(this);
        litepal.setOnClickListener(this);
        create.setOnClickListener(this);
        again.setOnClickListener(this);
        select.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        updata.setOnClickListener(this);
        sqLiteDatabase  =   mySqliteDatabase.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Create:
                Toast.makeText(this, "create",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Insert:
                sqLiteDatabase.execSQL("insert into Book1 (author, price) values('zheng' ,'20');");
                break;
            case R.id.update:
                sqLiteDatabase.execSQL("update Book1 set price = '11' where author  =   'zheng'; ");
                break;
            case R.id.Delete:
                sqLiteDatabase.execSQL("delete from Book1 where price='20';");
                break;
            case R.id.Select:
                Cursor  cursor= null;

                cursor   =        sqLiteDatabase.rawQuery("select * from Book1",null);
                if (cursor  !=null) {
                    if (cursor.moveToFirst()) {
                        do {
                            String s = cursor.getString(cursor.getColumnIndex("author"));
                            int i = cursor.getInt(cursor.getColumnIndex("id"));
                            Double f = cursor.getDouble(cursor.getColumnIndex("price"));
                            String builder = s + Integer.toString(i) + Double.toString(f);
                            Toast.makeText(MainActivity.this, builder, Toast.LENGTH_SHORT).show();
                        } while (cursor.moveToNext());
                    }
                }
                break;
            case R.id.addlite:
                LitePalTest litePalTest =   new LitePalTest();
                litePalTest.setName("zheng");
                litePalTest.setPrice(2.03);
                litePalTest.save();
                break;
            case R.id.litepal:
                LitePalTest test    =   new LitePalTest();
//                    test.setToDefault("name");//改
//                    test.updateAll("name='zheng'");
//                DataSupport.deleteAll(LitePalTest.class, "price='2.3'");//删
//               List<LitePalTest>   list    =   DataSupport.where("name='zheng'").select("price").order("price").limit(2).offset(1).find(LitePalTest.class);
                Cursor  cursor1 =   DataSupport.findBySQL("select * from litepaltest");

//               for (LitePalTest litePalTest1    :   list){
//                   String s =   litePalTest1.getName()+litePalTest1.getPrice().toString();
//                   Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
//            }
                if (cursor1.moveToFirst()){
                    do {
                        String  s   =   cursor1.getString(cursor1.getColumnIndex("name"))+
                                Double.toString(cursor1.getDouble(cursor1.getColumnIndex("price")));
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }while (cursor1.moveToNext());
                }




                break;
                default:
                    break;
        }
    }
}
