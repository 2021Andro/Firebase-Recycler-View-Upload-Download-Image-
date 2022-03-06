package com.example.demorecfir.Activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demorecfir.Classes.Demo;
import com.example.demorecfir.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private EditText etPersonName;
    private String personName;

    private Button btnAddPerson, btnShowDemoList;

    private ImageView ivProfile;
    private Uri imageUri;
    private static final int Image_Request_Code = 1;

    private DatabaseReference myRef;
    private FirebaseFirestore myStore;
    private FirebaseStorage myStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfile = findViewById(R.id.ivProfile);
        etPersonName = findViewById(R.id.etPersonName);
        btnAddPerson = findViewById(R.id.btnAddDemo);
        btnShowDemoList = findViewById(R.id.btnShowDemoList);

        myRef = FirebaseDatabase.getInstance().getReference();
        myStorage = FirebaseStorage.getInstance();
        myStore = FirebaseFirestore.getInstance();




    }

    @Override
    protected void onResume() {
        super.onResume();

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "image"), Image_Request_Code);
            }
        });

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                personName = etPersonName.getText().toString().trim();

                if (personName.isEmpty())
                {
                    etPersonName.setError("Enter Image Name");
                    return;
                }else if (ivProfile == null)
                {
                    Toast.makeText(MainActivity.this, "Select image", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    uploadDemoClass();

                }
            }
        });

        btnShowDemoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DemoShowList.class));
            }
        });

    }

    private void uploadDemoClass() {


        StorageReference imagePath = myStorage.getReference("Profile Image/" + Time.SECOND);

        imagePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Demo demo = new Demo(uri.toString(), personName);

                        myStore.collection("Users").add(demo);

                        etPersonName.setText(null);
                        ivProfile.setImageResource(R.drawable.ic_launcher_background);

                    }
                });

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code)
        {

            if (resultCode == RESULT_OK)
            {
                imageUri = data.getData();

                ivProfile.setImageURI(imageUri);
            }
            if (resultCode == RESULT_CANCELED)
            {
                ivProfile.setImageResource(R.drawable.ic_launcher_background);
            }


        }
    }
}