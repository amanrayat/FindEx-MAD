package com.example.findex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import utils.FoundItem;

public class ItemEntry extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button submit;
    private EditText title;
    private EditText description;
    private Spinner myCategorySpinner;
    private Spinner myLocationSpinner;
    private ImageButton imageButton;
    private Button button;
    private Uri imageUri;
    private StorageReference storageRootReference;
    private final int REQUEST_CODE = 1;
    private LocationEnum locationEnum;
    private CategoryEnum categoryEnum;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.item_entry);

        /*
        Creation of location spinner
         */
        myLocationSpinner = (Spinner) findViewById(R.id.locationSpinner);

        ArrayAdapter<String> mySpinnerAdapter = new ArrayAdapter<String>(ItemEntry.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.locationList));
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myLocationSpinner.setAdapter(mySpinnerAdapter);

        myLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locationEnum = LocationEnum.values()[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        myCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<String> mySpinnerAdapter2 = new ArrayAdapter<String>(ItemEntry.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categoryList));
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myCategorySpinner.setAdapter(mySpinnerAdapter2);

        myCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryEnum = CategoryEnum.values()[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*
        Initializing Firebase storage and Database;
         */
        storageRootReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        /*
        Getting reference for UI components
         */
        submit = findViewById(R.id.buttonFound);
        title = findViewById(R.id.itemTitle);
        description = findViewById(R.id.description);
        imageButton = findViewById(R.id.imagebutton);
        button = findViewById(R.id.uploadImage);

        /*
        Image Upload Click Listener
         */
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent impIntent = new Intent(Intent.ACTION_GET_CONTENT);
                impIntent.setType("image/*");
                startActivityForResult(impIntent,REQUEST_CODE);
            }
        });

        /*
        Upload image to FireBase storage click listener
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToStorage();
            }
        });

        /*
        Adding item to database click listener
         */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewItem(title.getText().toString(), description.getText().toString(),
                        locationEnum, categoryEnum,imageUri.toString());
            }
        });
    }

    /*
    Returning from the intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            imageUri = data.getData();
            imageButton.setImageURI(imageUri);
        }
    }

    /*
    Function to upload the image to Firebase Storage.
     */
    private void uploadImageToStorage(){
        final StorageReference filepath = storageRootReference.child("MyImage").child(imageUri.getLastPathSegment());
        filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imageUri = uri;
                        Toast.makeText(getApplicationContext(),"Photo Upload Successful", Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });

    }

    /*
    Function to append data to the database.
     */
    private void writeNewItem(String title, String description, LocationEnum location, CategoryEnum category, String url) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        FoundItem item1 = new FoundItem(new Random().nextLong(), url, title, description,category,location, new Date(),currentUser.getEmail() );
        mDatabase.push().setValue(item1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(ItemEntry.this, FoundItemList.class);
                startActivity(intent);
            }
        });
    }
}
