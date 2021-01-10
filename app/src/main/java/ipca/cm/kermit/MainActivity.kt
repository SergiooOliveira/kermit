package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

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