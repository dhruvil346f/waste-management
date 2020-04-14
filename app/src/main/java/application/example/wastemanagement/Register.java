package application.example.wastemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class Register extends AppCompatActivity {

    private EditText Username,Password,Email,Address,Phone_no;
    private FirebaseAuth firebaseAuth;
    private Button button_register;

    String name,email,password,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();


        firebaseAuth=FirebaseAuth.getInstance();

        button_register=findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    String name=Username.getText().toString();
                    String password=Password.getText().toString();
                    String email=Email.getText().toString();
                    String address=Address.getText().toString();
                    String phone=Phone_no.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())

                            {
                                sendUserData();
                                Toast.makeText(Register.this, "Registration successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(Register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    private  void setupUIViews(){
        Username=findViewById(R.id.Username);
        Password=findViewById(R.id.Password);
        Email=findViewById(R.id.Email);
        Address=findViewById(R.id.Address);
        Phone_no=findViewById(R.id.Phone_no_);
    }

    private boolean validate()
    {
        Boolean result=false;

         name=Username.getText().toString();
         password=Password.getText().toString();
         email=Email.getText().toString();
         address=Address.getText().toString();
         phone=Phone_no.getText().toString();


        if(name.isEmpty()||password.isEmpty()||email.isEmpty()||address.isEmpty()||phone.isEmpty()){

            Toast.makeText(this,"Please enter all the details correctly!",Toast.LENGTH_SHORT).show();

        }

        else if (password.length()<5)
        {
            Toast.makeText(this,"password contains more than 4 letters",Toast.LENGTH_SHORT).show();
        }

        else if (phone.contains("*")||phone.contains("#"))
        {
            Toast.makeText(this,"Enter correct phone number",Toast.LENGTH_SHORT).show();
        }

        else
        {
            result=true;

        }



    return result;
    }

    private void sendUserData()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile=new UserProfile(name,password,email,address,phone);
        myRef.setValue(userProfile);
    }
}
