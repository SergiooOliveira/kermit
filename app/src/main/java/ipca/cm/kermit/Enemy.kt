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
            playing = false
        }else if(!playing) {
            respawn()
        }
        y += 5
    }

    //respawn delay
    private fun respawn(){
        timer++
        if (timer >= 50)
        {
            val generator =  Random()
            x = generator.nextInt(maxX - 100)
            y = generator.nextInt(maxY)
            playing = true
            timer = 0
        }
    }


}