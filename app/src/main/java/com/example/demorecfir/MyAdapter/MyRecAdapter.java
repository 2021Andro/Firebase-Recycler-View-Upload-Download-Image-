package com.example.demorecfir.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demorecfir.Activitys.DemoShowList;
import com.example.demorecfir.Classes.Demo;
import com.example.demorecfir.R;

import java.util.ArrayList;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyRecViewHolder>{

    private Context context;
    private ArrayList<Demo> demoList = new ArrayList<>();

    public MyRecAdapter(Context context) {
        this.context = context;
    }

    public void setDemoList(ArrayList<Demo> demoList) {

        if (demoList != null){

            this.demoList = demoList;
            notifyDataSetChanged();

        }

    }

    @NonNull
    @Override
    public MyRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ral,parent, false);
        return new MyRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecViewHolder holder, int position) {

        Demo demo = demoList.get(position);

        holder.tvName.setText(demo.getName());

        Glide.with(context).asBitmap().load(demo.getImage()).into(holder.ivProfile);



    }

    @Override
    public int getItemCount() {
        return demoList.size();
    }

    public class MyRecViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private ImageView ivProfile;

        public MyRecViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName_L);
            ivProfile = itemView.findViewById(R.id.ivProfile_L);
        }
    }
}
