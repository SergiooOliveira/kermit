package ipca.cm.kermit

import android.graphics.Rect
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Player {

    // player position
    var x = 0
    var y = 0

    var shootX = 0
    var shootY = 0

    var KermitHeight : Int
        get() = y
        set(value) {}

    // number of lifes
    var lifesRemaining = 3

    // score
    var currentScore = 0

    // shooting boolean
    private var _isShooting = false

    var isShooting : Boolean
    get() = _isShooting
    set(value) {
        _isShooting = value
    }

    // Bitmap image
    var bitmap : Bitmap

    //collision rect
    var collisionRect : Rect

    constructor(width: Int, height: Int, context: Context){
        x = width / 2
        y = height - (height / 4)
        shootY = y
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.angry_pepe)
        bitmap = Bitmap.createScaledBitmap(bitmap, 175, 175, true)
        collisionRect = Rect(x,y,bitmap.width,bitmap.height)
    }

    fun update(){
        collisionRect.left = x
        collisionRect.top = y
        collisionRect.right = x + bitmap.width
        collisionRect.bottom = y + bitmap.height
    }

    fun shoot() {
        shootY -= 5
    }
}