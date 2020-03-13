package com.example.barmansimulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseAPI {

    HallOfShameActivity hallOfShame;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private ArrayList<Object> imagesUpload;
    String path;


    public DatabaseAPI(HallOfShameActivity hallOfShame){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInAnonymously();
        storageReference = FirebaseStorage.getInstance().getReference("BarmanLoser");
        hallOfShame = hallOfShame;
    }

    public void uploadImage(Uri uri){
        final String id = String.valueOf(System.currentTimeMillis());
        storageReference.child(id).putFile(uri);
    }

    public void getImages(String path) {
        /*FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference  photoRef = storage.getReference().child(path);
        final long ONE_MEGABYTE = 1024 * 1024;
        photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                System.out.println(bitmap.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/

        Task<ListResult> test = storageReference.listAll();
        OnSuccessListener<ListResult> t;
        test.addOnSuccessListener(t = new OnSuccessListener<ListResult>() {
            public ArrayList arrayList = new ArrayList();
            @Override
            public void onSuccess(ListResult listResult) {
                for (int i = 0; i<listResult.getItems().size(); i++) {
                    listResult.getItems().get(i).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            System.out.println("URI " + uri);

                            //arrayList.add(uri);
                            hallOfShame.refresh(uri);
                        }
                    });
                }

                //System.out.println(listResult.getItems().get(0));
            }

        });


    }

}
