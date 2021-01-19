package ipca.cm.kermit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Bullets(context: Context, height: Int) {

    var bulletsArray = mutableListOf<Bullet>()
    var context : Context = context
    var height : Int = height

    fun addBullet(x: Int, y: Int) {
        bulletsArray.add(Bullet(x, y, context))
    }

    fun update(){
        for (b in bulletsArray) {
            b.update()
            if (b.y >= height)
                b.active = false
        }
    }
}