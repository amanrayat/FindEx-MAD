package com.example.findex;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import utils.FoundItem;

public class ItemEntry extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button submit;
    private EditText title;
    private EditText description;
    private EditText location;
    private Uri url;
    private EditText category;
    private ImageButton imageButton;
    private Button button;
    private Uri imageUri;
    private StorageReference storageRootReference;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_entry);

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
        location = findViewById(R.id.itemLocation);
        category = findViewById(R.id.itemCategory);
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
                writeNewItem(title.getText().toString(), description.getText().toString()
                        , location.getText().toString(), category.getText().toString(),imageUri.toString());
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
    private void writeNewItem(String title, String description, String location, String category, String url) {
        Item item = new Item(title, description,location,category,url);
        mDatabase.push().setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Intent intent = new Intent(ItemEntry.this, FoundItem.class);
                startActivity(intent);
            }
        });
    }
}
