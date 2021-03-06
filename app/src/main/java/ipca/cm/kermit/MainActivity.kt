package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MainActivity : Activity() {

    lateinit var storage : FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("allphotos")
        storage = Firebase.storage
        var storageRef = storage.reference

        val buttonStart = findViewById<Button>(R.id.button_Start)
        buttonStart.setOnClickListener{
            val intent = Intent(this@MainActivity, Game::class.java)
            startActivity(intent)
        }

        val buttonMenu = findViewById<Button>(R.id.button_Menu)
        buttonMenu.setOnClickListener{
            val intent = Intent(this@MainActivity, Options::class.java)
            startActivity(intent)
        }

        val buttonscores = findViewById<Button>(R.id.button_scores)
        buttonscores.setOnClickListener{
            val intent = Intent(this@MainActivity, Scores::class.java)
            startActivity(intent)
        }

        val buttonlogin = findViewById<Button>(R.id.button_login)
        buttonlogin.setOnClickListener{
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        val buttonregister = findViewById<Button>(R.id.button_register)
        buttonregister.setOnClickListener{
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}