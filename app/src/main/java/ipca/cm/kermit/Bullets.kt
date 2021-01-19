package ipca.cm.kermit

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Bullets(var context: Context, var height: Int) {

    var bulletsArray = mutableListOf<Bullet>()

    fun addBullet(x: Int, y: Int) {
        if (bulletsArray.size > 4)
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