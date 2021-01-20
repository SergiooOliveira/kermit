package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class Scores : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val menu = findViewById<Button>(R.id.button_menu)
        menu.setOnClickListener{
            val intent = Intent(this@Scores, MainActivity::class.java)
            startActivity(intent)
        }
    }
}