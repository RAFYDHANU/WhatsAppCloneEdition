package com.example.whatsappclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import com.example.whatsappclone.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseAuthListener = FirebaseAuth.AuthStateListener {
//      mengecek user yang sedang aktif,jika ada,proses akan langsung intent ke halaman utama
        val user = firebaseAuth.currentUser?.uid
        if (user != null) {
            val intent = Intent(this,MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)// menghilangkan action bar
        setContentView(R.layout.activity_login)

        setTextChangedListener(edt_email,til_email)
        setTextChangedListener(edt_password,til_password)
        progress_layout.setOnTouchListener{ v, event -> true}

        btn_login.setOnClickListener {
            onLogin()
        }

        txt_signup.setOnClickListener {
            onSignUp()
        }
    }

    private fun setTextChangedListener(edt:EditText, til: TextInputLayout) {
        edt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                til.isErrorEnabled = false
            }
        })
    }

    private fun onLogin() {
        var proceed = true
        if (edt_email.text.isNullOrEmpty()) {     //check jika EditText kosong
            til_email.error = "Required Password" //TextInputLayout(til)menampilkan pesan
            til_email.isErrorEnabled =
                true       //mengubah state til yang sebelumnya tidak menampilkan error sekarang menampilkan
            proceed = false
        }

        if (edt_password.text.isNullOrEmpty()) {
            til_password.error = "Required Password"
            til_password.isErrorEnabled = true
            proceed = false
        }

        if (proceed) {
            progress_layout.visibility = View.VISIBLE  //menampilkan progress bar
            //untuk menunjukkan bahwa ada proses yang sedang dilakukan
            firebaseAuth.signInWithEmailAndPassword(
                edt_email.text.toString(),
                edt_password.text.toString()
            ) //mengubah data dalam editText jadi String

                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        progress_layout.visibility = View.GONE
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Error: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                .addOnFailureListener { e ->
                    progress_layout.visibility = View.GONE
                    e.printStackTrace()
                }
        }
    }

    override fun onStart() {
        super.onStart() //method yang pertama kali dijalankan sebelum method lainnya
        firebaseAuth.addAuthStateListener(firebaseAuthListener)
    }

    override fun onStop() {
        super.onStop() //dijalnkan jika proses dalam activty selesai dihentikan system
        firebaseAuth.removeAuthStateListener(firebaseAuthListener)
    }

    private fun onSignUp() {
        startActivity(Intent(this, SignUpActivity::class.java))
        finish()

    }
}
