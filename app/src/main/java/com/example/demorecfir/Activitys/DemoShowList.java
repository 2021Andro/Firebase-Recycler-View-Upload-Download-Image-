package com.example.demorecfir.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.demorecfir.Classes.Demo;
import com.example.demorecfir.MyAdapter.MyRecAdapter;
import com.example.demorecfir.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DemoShowList extends AppCompatActivity {

    private RecyclerView rvList;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Demo> demoList;
    private MyRecAdapter myRecAdapter;

    private FirebaseFirestore myStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_show_list);

        myStore = FirebaseFirestore.getInstance();

        rvList = findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);

        demoList = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);

        myRecAdapter = new MyRecAdapter(this);

        rvList.setLayoutManager(layoutManager);

        rvList.setAdapter(myRecAdapter);


        myStore.collection("Users")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments())
                {

                    Demo demo = snapshot.toObject(Demo.class);

                    demoList.add(demo);

                    myRecAdapter.setDemoList(demoList);

                }
            }
        });











    }
}