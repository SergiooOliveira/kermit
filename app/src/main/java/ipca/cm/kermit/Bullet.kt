package ipca.cm.kermit

import android.graphics.Rect
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.content.Context

class Bullet(x: Int, y: Int, context: Context) {

    //position variables
    var x = 0
    var y = 0

    // Bitmap image
    var bitmap : Bitmap

    //collision rect
    var collisionRect : Rect

    var active = true

    init {
        this.x = x + 50
        this.y = y + 20
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player_mod1)
        bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, true)
        collisionRect = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update(){
        y -= 5
        collisionRect.left = x
        collisionRect.top = y
        collisionRect.right = x + bitmap.width
        collisionRect.bottom = y + bitmap.height
    }
}