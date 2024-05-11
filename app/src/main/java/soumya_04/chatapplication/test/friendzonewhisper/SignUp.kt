package soumya_04.chatapplication.test.friendzonewhisper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private  lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.usrname)
        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.password)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener{
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name, email, password)
        }

    }

    private  fun signUp(name:String ,email: String, password: String){
        // logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // home activity
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // error
                    Toast.makeText(this@SignUp, "Unexpected error occurred you can't Whisper with your Friend. Try again later", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }

}