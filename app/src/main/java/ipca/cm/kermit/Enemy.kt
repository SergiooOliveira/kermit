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

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pepe_sad)
        bitmap = Bitmap.createScaledBitmap(bitmap, 175, 175, true)
        maxX = width - bitmap.width
        minX = 0
        maxY = height / 4
        minY = -200
        val generator = Random()
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        collisionRect = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(height: Int){
        if (y > (height + 100f) && playing) {
            playing = false
        }else if(!playing) {
            respawn()
        }
        y += 10
        if (playing) {
            collisionRect.left = x
            collisionRect.top = y
            collisionRect.right = x + bitmap.width
            collisionRect.bottom = y + bitmap.height

        }else if (!playing) collisionRect.setEmpty() //makes so that the hitbox doesn't hit the player even if the enemy is dead
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