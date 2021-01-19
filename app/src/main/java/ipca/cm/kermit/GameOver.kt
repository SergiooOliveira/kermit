package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GameOver : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)


        val buttonagin = findViewById<Button>(R.id.button_agin)
        buttonagin.setOnClickListener {
            val intent = Intent(this@GameOver,MainActivity::class.java)
            startActivity(intent)
        }

    }
}