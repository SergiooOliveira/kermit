package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val TAG = "RegisterActivity"
private lateinit var auth: FirebaseAuth

class RegisterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val register = findViewById<Button>(R.id.register)
        val loading = findViewById<ProgressBar>(R.id.loading)

        auth = Firebase.auth

        register.setOnClickListener {
            auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
        val buttonback = findViewById<Button>(R.id.menu)
        buttonback.setOnClickListener{
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}