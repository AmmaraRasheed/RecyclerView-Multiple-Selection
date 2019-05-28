package com.example.rabia.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    public interface ChnageStatusListener{
        void onItemChangeListener(int position,Model model);
    }

     ArrayList<Model> models;
     Context mContext;
     ChnageStatusListener chnageStatusListener;

     public void setModel(ArrayList<Model> models){
         this.models=models;
     }

    public Adapter(ArrayList<Model> models, Context mContext, ChnageStatusListener chnageStatusListener) {
        this.models = models;
        this.mContext = mContext;
        this.chnageStatusListener = chnageStatusListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {



        Model model=models.get(i);
        if(model!=null){
            viewHolder.text.setText(model.getText());
            viewHolder.position=i;
            viewHolder.image.setImageResource(model.getImage());
            if(model.isSelect()){
                viewHolder.view.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
            }
            else{
                viewHolder.view.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
            }
        }
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model model1=models.get(i);
                if(model1.isSelect()){
                    model1.setSelect(false);
                }else{
                    model1.setSelect(true);
                }
                models.set(viewHolder.position,model1);
                if(chnageStatusListener!=null){
                    chnageStatusListener.onItemChangeListener(viewHolder.position,model1);
                }
                notifyItemChanged(viewHolder.position);

            }
        });



    }

    @Override
    public int getItemCount() {
        if(models!=null){
            return models.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;
        public View view;
        public int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            image=itemView.findViewById(R.id.image);
            text=itemView.findViewById(R.id.text);
        }
    }
}
