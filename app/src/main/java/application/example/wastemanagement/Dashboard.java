package application.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private MenuItem Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        firebaseAuth =FirebaseAuth.getInstance();


        Logout=findViewById(R.id.Logout);
    }

    private  void Logout()
    {
      firebaseAuth.signOut();
      finish();
      startActivity(new Intent(Dashboard.this,MainActivity.class));
      Toast.makeText(Dashboard.this,"You have been Successfully logged out",Toast.LENGTH_SHORT).show();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawermenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();



        if(id == R.id.pickup_location) {
            Intent intent = new Intent(Dashboard.this, Pickup_Location.class);
            startActivity(intent);

            return false;
        }

        else
        if(id == R.id.Logout) {
            Logout();
            return false;
        }

        return  super.onOptionsItemSelected(item);
    }

    public void onBackPressed()
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


}
