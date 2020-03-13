package com.example.barmansimulator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
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
    String u;


    public DatabaseAPI(HallOfShameActivity hallOfShame){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInAnonymously();
        storageReference = FirebaseStorage.getInstance().getReference("BarmanLoser");
        this.hallOfShame = hallOfShame;
        System.out.println("haaaaaaaaaaaaaaaa"+hallOfShame.imgs.length);

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
        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Task<ListResult> test = storageReference.listAll();
        OnSuccessListener<ListResult> t;
        final File finalLocalFile = localFile;
        test.addOnSuccessListener(t = new OnSuccessListener<ListResult>() {
            public ArrayList arrayList = new ArrayList();
            @Override
            public void onSuccess(final ListResult listResult) {

                for (int i = 0; i<listResult.getItems().size(); i++) {
                    final int finalI = i;
                    final int finalI1 = i;
                    listResult.getItems().get(i).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            //System.out.println(bitmap.toString());
                            //hallOfShame.refresh(uri);
                            String chemin = listResult.getItems().get(finalI).getPath();
                            StorageReference ref = listResult.getItems().get(finalI1);
                            // ImageView in your Activity
                            ImageView imageView = hallOfShame.findViewById(R.id.imageView);

                            Glide.with(hallOfShame)
                                    .load(ref);
                                    //.into(imageView);

                        }
                    });
                    
                    
                    
                    
                }

                //System.out.println(listResult.getItems().get(0));
            }

        });


    }

}
