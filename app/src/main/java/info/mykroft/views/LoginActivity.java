package info.mykroft.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

import info.mykroft.R;
import info.mykroft.utils.Constants;

public class LoginActivity extends AppCompatActivity {
    private MaterialEditText email;
    private MaterialEditText password;
    private Button logIn;
    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setParentNavigation();
        email = (MaterialEditText) findViewById(R.id.email);
        password = (MaterialEditText) findViewById(R.id.password);
        logIn = (Button) findViewById(R.id.log_in);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(email.getText().toString().trim().toLowerCase(), password.getText().toString().trim());
            }
        });
    }

    private void loginUser(final String username, final String password) {
        if (isNetworkAvailable()) {
            showMaterialDialog();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(Constants.PROBONO);
            DatabaseReference users = myRef.child(Constants.USERS);
            users.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dismissMaterialDialog();
                    DataSnapshot snapshot = dataSnapshot.child(username);
                    HashMap<String, String> map = (HashMap<String, String>) snapshot.getValue();
                    if (map != null) {
                        String value = map.get("password");
                        if (value != null && value.contentEquals(password)) {
                            // login successful
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            saveUserPref(username);
                            getActivity().startActivity(intent);
                            getActivity().finish();

                        } else {
                            //login failed
                            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Invalid username or Password", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    } else {
                        //login failed
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Invalid username or Password", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    dismissMaterialDialog();
                    Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Network is not available", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    private void saveUserPref(String username) {
        SharedPreferences sharedPreferences =  this.getSharedPreferences(Constants.PREF,MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putString(Constants.USERNAME, username);
        editor.commit();
    }

    private LoginActivity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissMaterialDialog();
    }

    private void dismissMaterialDialog() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
    }

    private void showMaterialDialog() {
        materialDialog = new MaterialDialog.Builder(this)
                .content("Please wait...")
                .progress(true, 0)
                .autoDismiss(false)
                .cancelable(false)
                .backgroundColorRes(R.color.md_white_1000)
                .contentColor(this.getResources().getColor(R.color.text_color))
                .widgetColor(this.getResources().getColor(R.color.progress_bar_color))
                .show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private void setParentNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
