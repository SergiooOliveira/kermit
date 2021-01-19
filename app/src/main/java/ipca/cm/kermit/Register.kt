package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        val buttonback = findViewById<Button>(R.id.button_back)
        buttonback.setOnClickListener{
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
        }


    }
}