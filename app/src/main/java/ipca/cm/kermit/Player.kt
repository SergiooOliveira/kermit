package ipca.cm.kermit

import android.graphics.Rect
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Player {

    // player position
    var x = 0
    var y = 0

    //timer to change between bitmaps
    var bitTimer : Int = 0
    var bitShow : Int = 1

    var shootX = 0
    var shootY = 0

    var KermitHeight : Int
        get() = y
        set(value) {}

    // number of lifes
    var lifesRemaining = 3

    // score
    var currentScore = 0

    // Bitmap image
    var bitmap : Bitmap
    var bitmap2 : Bitmap
    var bitDraw : Bitmap

    //collision rect
    var collisionRect : Rect

    constructor(width: Int, height: Int, context: Context){
        x = width / 2
        y = height - (height / 4)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player_mod1)
        bitmap2 = BitmapFactory.decodeResource(context.resources, R.drawable.player_mod2)
        bitmap = Bitmap.createScaledBitmap(bitmap, 175, 175, true)
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, 175, 175, true)
        bitDraw = bitmap
        collisionRect = Rect(x,y,bitmap.width,bitmap.height)
    }

    fun update(){
        collisionRect.left = x
        collisionRect.top = y
        collisionRect.right = x + bitmap.width
        collisionRect.bottom = y + bitmap.height
    }

    fun changeBitMap(){
        if (bitTimer >= 20 && bitShow == 2){
            bitDraw = bitmap
            bitTimer = 0
            bitShow = 1
        }
        if (bitTimer >= 20 && bitShow == 1) {
            bitDraw = bitmap2
            bitTimer = 0
            bitShow = 2
        }
    }

}