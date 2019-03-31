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

import org.w3c.dom.Text;

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
    private EditText confirmPassword;
    private EditText name;
    private EditText email;
    private EditText phone;
    private TextView warning;
    private TextView userWarning;
    private TextView passwordWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //LoginData.updateUserInfo();

        Button submitButton = findViewById(R.id.submitButton);
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        confirmPassword = findViewById(R.id.confirmPasswordText);
        warning = findViewById(R.id.warning1);
        userWarning = findViewById(R.id.warning);
        passwordWarning = findViewById(R.id.warning2);
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
        userWarning.setVisibility(View.GONE);
        passwordWarning.setVisibility(View.GONE);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> users = LoginData.getUserInfo();
                //ArrayList<String> passwords = LoginData.getPasswords();

                Editable u = username.getText();
                Editable p = password.getText();

                if (name.getText().toString().equals("") || username.getText().toString().equals("")
                        || password.getText().toString().equals("")
                        || confirmPassword.getText().toString().equals("")
                        || email.getText().toString().equals("")
                        || phone.getText().toString().equals("")) {
                    warning.setVisibility(View.VISIBLE);
                    userWarning.setVisibility(View.GONE);
                    passwordWarning.setVisibility(View.GONE);

                }/* else if (LoginData.findUser(u.toString())) {
                    //username is taken
                    warning.setVisibility(View.GONE);
                    userWarning.setVisibility(View.VISIBLE);
                    passwordWarning.setVisibility(View.GONE);
                }*/ else if (!confirmPassword.getText().toString().equals(p.toString())) {
                    warning.setVisibility(View.GONE);
                    userWarning.setVisibility(View.GONE);
                    passwordWarning.setVisibility(View.VISIBLE);
                } else {
                    //I changed the last parameter in the addUser call.
                    // We can expect all registering members to be USERS, right?
                    LoginData.addUser(name.getText().toString(),u.toString(), p.toString(),
                            UserType.USER,phone.getText().toString(), email.getText().toString());
                    /*Intent intent = new Intent(a, LoginActivity.class);
                    startActivity(intent);*/
                }

                User newUser = new User(name.getText().toString(), u.toString(), p.toString(),
                        UserType.USER, phone.getText().toString(), email.getText().toString(), 0, "");
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
