package com.team8303.smartbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team8303.model.LoginData;
import com.team8303.model.User;
import com.team8303.model.UserType;

import java.util.ArrayList;

//import android.widget.ArrayAdapter;
//import android.widget.Spinner;


/**
 * allows a user to register
 * creates that user
 *
 * */
public class RegisterActivity extends AppCompatActivity {

    private final Activity a = this;
    private EditText username;
    private EditText password;
    private EditText name;
    private EditText email;
    private EditText phone;
    private TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //LoginData.updateUserInfo();

        Button submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        warning = findViewById(R.id.warning1);
        name = findViewById(R.id.nameText);
        email = findViewById(R.id.emailText);
        phone = findViewById(R.id.phoneText);
        //Spinner userTypeSpinner = findViewById(R.id.spinner);
        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        /*ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, UserType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);*/

        warning.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> users = LoginData.getUserInfo();
                //ArrayList<String> passwords = LoginData.getPasswords();

                Editable u = username.getText();
                Editable p = password.getText();

                if (LoginData.findUser(u.toString())) {
                    //username is taken
                    warning.setVisibility(View.VISIBLE);
                } else {
                    //I changed the last parameter in the addUser call.
                    // We can expect all registering members to be USERS, right?
                    LoginData.addUser(u.toString(), p.toString(),
                            UserType.USER,0,"");
                    /*Intent intent = new Intent(a, LoginActivity.class);
                    startActivity(intent);*/
                }

                User newUser = new User(u.toString(), p.toString(),
                        UserType.USER, 0, "");
                users.add(newUser);
                //passwords.add(password.getText().toString());
                LoginData.setUserInfo(users);
                //UserDatabase db = WelcomeActivity.getDb();
                //db.userDao().insertUsers(newUser);
                //WelcomeActivity.setDb(db);

                /*Intent intent = new Intent(a, LoginActivity.class);
                startActivity(intent);*/

            }
        });
    }
}
