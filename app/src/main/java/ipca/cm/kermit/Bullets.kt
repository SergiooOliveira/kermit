package ipca.cm.kermit

import android.content.Context
import android.widget.Toast

class Bullets(var context: Context, var height: Int) {

    var bulletsArray = mutableListOf<Bullet>()
    var bulletsToRemove = mutableListOf<Bullet>()

    fun addBullet(x: Int, y: Int) {
        if (bulletsArray.size < 4)
            bulletsArray.add(Bullet(x, y, context))
    }

    fun update() {
        for (b in bulletsArray) {
            b.update()
            if (b.y <= 0) b.active = false
            if (!b.active) bulletsToRemove.add(b)
        }

        bulletsArray.removeAll(bulletsToRemove)
    }
}