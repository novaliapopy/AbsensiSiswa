package id.sch.smktelkom_mlg.project.xiirpl303132333.absensisiswa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_profil extends AppCompatActivity implements View.OnClickListener {
    ImageButton androidImageButton;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLoginRef = mRootRef.child("login");
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ImageButton androidImageButton = (ImageButton) findViewById(R.id.imageButtonAbsen);
        androidImageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //getting current user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Toast.makeText(activity_profil.this, user.getEmail() + " sudah absen terimakasih ^^", Toast.LENGTH_SHORT).show();
                mLoginRef.push().setValue(user.getEmail() + " sudah absen terimakasih ^^");
            }

        });


        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, activity_login.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Selamat datang " + user.getEmail());

        //adding listener to button
        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, activity_login.class));
        }
    }
}
