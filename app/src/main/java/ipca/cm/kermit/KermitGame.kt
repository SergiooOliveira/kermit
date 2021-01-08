package ipca.cm.kermit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView

class KermitGame : AppCompatActivity() {

    var x : Double? = 0.0
    var y : Double? = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kermit_game)

        val yourImage = findViewById<ImageView>(R.id.kermit)


        yourImage.setOnTouchListener{view , event->
            when (event.action){
                MotionEvent.ACTION_DOWN -> {
                    x = view.x.toDouble() - event.rawX
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    view.x = event.rawX - view.width/2
                    true
                }
                else -> {
                    true
                }
            }
        }
    }


}