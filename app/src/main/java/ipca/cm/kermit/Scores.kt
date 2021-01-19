package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Scores : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scores)

        val buttonaginback = findViewById<Button>(R.id.button_agin_back)
        buttonaginback.setOnClickListener{
            val intent = Intent(this@Scores, MainActivity::class.java)
            startActivity(intent)
        }
    }
}