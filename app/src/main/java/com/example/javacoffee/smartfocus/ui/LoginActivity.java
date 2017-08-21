package com.example.javacoffee.smartfocus.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javacoffee.smartfocus.MainActivity;
import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.entity.MyUser;
import com.example.javacoffee.smartfocus.utils.L;
import com.example.javacoffee.smartfocus.utils.StaticClass;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText accountText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void login() {
        L.d("Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String accountId = accountText.getText().toString().trim();
        final String password = passwordText.getText().toString().trim();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        MyUser user = new MyUser();
                        user.setUsername(accountId);
                        user.setPassword(password);
                        user.login(new SaveListener<MyUser>() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if (e == null){
                                    onLoginSuccess();
                                }else {
                                    L.e(e.toString());
                                    onLoginFailed();
                                }
                            }
                        });
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    private void initView() {
        accountText = (EditText) findViewById(R.id.input_email);
        passwordText = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        signLink = (TextView) findViewById(R.id.link_signup);
        loginButton.setOnClickListener(this);
        signLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            case R.id.link_signup:
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                break;

        }
    }


    //判断用户输入是否合法
    public boolean validate() {
        boolean valid = true;

        String accountId = accountText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (accountId.isEmpty()) {
            accountText.setError("enter a valid account ID");
            valid = false;
        } else {
            accountText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }


}
