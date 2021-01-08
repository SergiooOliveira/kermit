package ipca.cm.kermit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart = findViewById<Button>(R.id.button_Start)
        buttonStart.setOnClickListener{
            val intent = Intent(this@MainActivity, Game::class.java)
            startActivity(intent)
        }
    }



}