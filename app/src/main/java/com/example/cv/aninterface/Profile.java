package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//public class Profile extends RecyclerView.Adapter<Profile.UsertaskViewHolder> {
//
//    private Context mCtx;
//    private List<dbUserInformation> Usertasklist;
//
//
//    public Profile (Context mCtx, List<dbReminder> remtasklist) {
//        this.mCtx = mCtx;
//        this.Usertasklist = Usertasklist;
//    }
//
//    @NonNull
//    @Override
//    public Profile.UsertaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UsertaskViewHolder usertaskViewHolder, int position) {
//
//        dbUserInformation profile = Usertasklist.get(position);
//
//
//        usertaskViewHolder.textViewfName.setText(profile.getfname());
//        usertaskViewHolder.textViewlName.setText(profile.getlname());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return Usertasklist.size();
//    }
//
//    class UsertaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        TextView textViewfName, textViewlName;
//
//
//        public UsertaskViewHolder(View itemView) {
//            super(itemView);
//
//            textViewfName = itemView.findViewById(R.id.view_fname);
//            textViewlName = itemView.findViewById(R.id.view_lname);
//
//
//            itemView.setOnClickListener(this);
//
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
//}

public class Profile extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private TextView view_fname;
    private TextView view_lname;
    private TextView view_email;
    private TextView view_phone;
    private TextView view_username;
    private TextView view_bdate;
    private TextView edit_profile;
    private String mail, name1, name2, phonenum, username, bdate;
    private List<dbUserInformation> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String currentusermail = firebaseAuth.getCurrentUser().getEmail();

        view_fname = findViewById(R.id.view_fname);
        view_lname = findViewById(R.id.view_lname);
        view_email = findViewById(R.id.view_email);
        view_phone = findViewById(R.id.view_phone);
        view_username = findViewById(R.id.textViewUserName);
        view_bdate = findViewById(R.id.view_bdate);

        edit_profile = findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        profileList = new ArrayList<>();



        db = FirebaseFirestore.getInstance();
        db.collection("Profile").whereEqualTo("email", currentusermail).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                dbUserInformation p = d.toObject(dbUserInformation.class);
                                p.setId(d.getId());
                                profileList.add(p);
                                mail = p.getemail();
                                name1 = p.getfname();
                                name2 = p.getlname();
                                phonenum = p.getphone();
                                username = p.getUsername();
                                bdate = p.getBdate();
                            }
                            view_fname.setText(name1);
                            view_email.setText(mail);
                            view_lname.setText(name2);
                            view_phone.setText(phonenum);
                            view_username.setText(username);
                            view_bdate.setText(bdate);
                        }
                    }
                });


    }


}