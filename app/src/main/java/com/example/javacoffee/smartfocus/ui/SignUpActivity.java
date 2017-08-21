package com.example.javacoffee.smartfocus.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.entity.MyUser;
import com.example.javacoffee.smartfocus.utils.L;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText et_accountId,et_email,et_password;
    public Button btn_signup;
    public TextView tv_linkLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        et_accountId = (EditText) findViewById(R.id.input_name);
        et_email = (EditText) findViewById(R.id.input_email);
        et_password = (EditText) findViewById(R.id.input_password);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        tv_linkLogin = (TextView) findViewById(R.id.link_login);
        btn_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.link_login:
                finish();
                break;
            case R.id.btn_signup:
                signup();
                break;
            default:
                break;
        }
    }

    public void onSignupSuccess() {
        btn_signup.setEnabled(true);
        Toast.makeText(getBaseContext(), "Signup success", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        btn_signup.setEnabled(true);
    }

    public void signup() {
        boolean valid = true;

        btn_signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String accountId = et_accountId.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (accountId.isEmpty() || accountId.length() < 3) {
            et_accountId.setError("at least 3 characters");
            valid = false;
        } else {
            et_accountId.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("enter a valid email address");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            et_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            et_password.setError(null);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();

        if (!valid) {
            L.e("invalid input");
            onSignupFailed();
        }else {
            MyUser myuser = new MyUser();
            myuser.setUsername(accountId);
            myuser.setEmail(email);
            myuser.setPassword(password);
            myuser.signUp(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser myUser, BmobException e) {
                    if (e != null) {
                        onSignupFailed();
                        L.e(e.toString());
                    }else {
                        onSignupSuccess();
                    }
                }
            });
        }
    }

}
