package com.example.rabia.recyclerview;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements Adapter.ChnageStatusListener, View.OnClickListener {
    private RecyclerView recyclerView=null;
    private Adapter adapter=null;
    private ArrayList<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildResources();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter=new Adapter(models,MainActivity.this,this);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.delete).setOnClickListener(MainActivity.this);
    }

    private void buildResources() {
        Resources resources=getResources();
        if(models==null&& resources!=null){
            String[] text=resources.getStringArray(R.array.text);
            TypedArray image=resources.obtainTypedArray(R.array.image);
            models=new ArrayList<>();
            for(int i=0;i<text.length;i++){
                Model model=new Model();
                model.setText(text[i]);
                model.setImage(image.getResourceId(i,R.mipmap.ic_launcher));
                model.setSelect(false);
                models.add(model);
            }
        }
    }

    @Override
    public void onItemChangeListener(int position, Model model) {
        try{
            models.set(position,model);

        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View v) {
        updateList();
    }

    private void updateList() {
        ArrayList<Model> temp=new ArrayList<>();
        try{
            for(int i=0;i<models.size();i++){
                if(!models.get(i).isSelect()){
                    temp.add(models.get(i));
                }
            }

        }catch (Exception e){

        }
        models=temp;
        if(models.size()==0){
            recyclerView.setVisibility(View.GONE);
        }
        adapter.setModel(models);
        adapter.notifyDataSetChanged();
    }
}
