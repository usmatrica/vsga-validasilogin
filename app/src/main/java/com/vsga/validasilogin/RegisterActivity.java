package com.vsga.validasilogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etEmail, etNamaLengkap, etAsalSekolah, etAlamat;
    Button btnSimpan;

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etNamaLengkap = findViewById(R.id.etNamaLengkap);
        etAsalSekolah = findViewById(R.id.etAsalSekolah);
        etAlamat = findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(v -> {
            if (isValidation()) {
                simpanFileData();
            } else {
                Toast.makeText(this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isValidation() {
        if (etUsername.getText().toString().trim().isEmpty() ||
                etPassword.getText().toString().trim().isEmpty() ||
                etEmail.getText().toString().trim().isEmpty() ||
                etNamaLengkap.getText().toString().trim().isEmpty() ||
                etAsalSekolah.getText().toString().trim().isEmpty() ||
                etAlamat.getText().toString().trim().isEmpty()
        ) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = etUsername.getText().toString().trim() + ";" +
                etPassword.getText().toString().trim() + ";" +
                etEmail.getText().toString().trim() + ";" +
                etNamaLengkap.getText().toString().trim() + ";" +
                etAsalSekolah.getText().toString().trim() + ";" +
                etAlamat.getText().toString().trim();
        File file = new File(getFilesDir(), etUsername.getText().toString().trim());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}