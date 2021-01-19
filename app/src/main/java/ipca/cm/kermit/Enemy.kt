package ipca.cm.kermit


import android.graphics.Rect
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

    //bitmap image
    var bitmap : Bitmap

    //collision rectangle
    var collisionRect : Rect

    constructor(width: Int, height: Int, context: Context) {
        maxX = width
        minX = 0
        maxY = height / 4
        minY = 0

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pepe_sad)
        bitmap = Bitmap.createScaledBitmap(bitmap, 175, 175, true)
        val generator =  Random()
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        collisionRect = Rect(x,y,bitmap.width,bitmap.height)
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