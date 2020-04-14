package application.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    private Button button_Login, button_signup;
    EditText email, password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        password = (EditText) findViewById(R.id.editText_password);
        email= (EditText) findViewById(R.id.Email);
        button_Login = findViewById(R.id.button_login);

        button_signup = findViewById(R.id.button_signup);


        firebaseAuth = FirebaseAuth.getInstance();





        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate()) {

                   validate(email.getText().toString(), password.getText().toString());


               }


            }
        });



        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity2();
            }
        });
    }



    public void openactivity2() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



    private void validate(final String name, final String password) {
       firebaseAuth.signInWithEmailAndPassword(name, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Dashboard.class));
                }



                else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    ;
                }
            }
        });
    }

    private boolean validate()
    {
        Boolean result=false;


        String pass=password.getText().toString();
        String mail=email.getText().toString();


        if(mail.isEmpty()||pass.isEmpty()){

            Toast.makeText(this,"Please enter all the details correctly!",Toast.LENGTH_SHORT).show();

        }
        else
        {
            result=true;

        }



        return result;
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