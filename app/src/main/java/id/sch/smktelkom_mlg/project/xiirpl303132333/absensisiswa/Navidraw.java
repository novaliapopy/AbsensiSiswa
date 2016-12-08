package id.sch.smktelkom_mlg.project.xiirpl303132333.absensisiswa;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Navidraw extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment = null;
    ImageButton androidImageButton;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mLoginRef = mRootRef.child("login").child("siswa");
    private Button buttonLogout;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //view objects
    private TextView textViewUserEmail;
    private TextView textViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navidraw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        ImageButton androidImageButton = (ImageButton) findViewById(R.id.imageButtonAbsen);
        androidImageButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //getting current user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                Toast.makeText(Navidraw.this, user.getEmail() + " sudah absen terimakasih.", Toast.LENGTH_SHORT).show();
                mLoginRef.push().setValue(user.getEmail() + " sudah absen terimakasih.");
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
        //buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Selamat datang " + user.getEmail() + " silahkan klik tombol fingerprint untuk absensi ^^");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        if (savedInstanceState == null)
        {
            fragment = new Fragment();
            callFragment(fragment);
        }
        buttonLogout.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            //logging out the user
            firebaseAuth.signOut();

            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, activity_login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragment);
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
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
