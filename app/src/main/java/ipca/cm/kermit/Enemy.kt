package ipca.cm.kermit

import android.widget.Toast
import java.util.*

class Enemy {
    var x = 0
    var y = 0

    var maxY = 0
    var minY = 0
    var maxX = 0
    var minX = 0

    private var timer = 0
    var playing = true

    constructor(width: Int, height: Int) {
        maxX = width
        minX = 0
        maxY = height / 4
        minY = 0

        val generator =  Random()
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)
    }

    fun update(height: Int){
        if (y > (height + 100f) && playing) {
            val generator =  Random()
            x = generator.nextInt(maxX)
            y = generator.nextInt(maxY)
            playing = false
        }else if(playing){
            respawn()
        }else{
            y += 5
        }
    }

    //respawn delay
    private fun respawn(){
        timer++
        if (timer >= 50)
        {
            playing = true
            timer = 0
        }
    }


}