package ipca.cm.kermit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Options : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)


        val buttonbeckoptomenu = findViewById<Button>(R.id.button_beck_op_to_menu)
        buttonbeckoptomenu.setOnClickListener {
            val intent = Intent(this@Options, MainActivity::class.java)
            startActivity(intent)
        }
    }
}