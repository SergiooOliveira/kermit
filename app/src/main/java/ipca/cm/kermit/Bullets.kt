package ipca.cm.kermit

import android.content.Context

class Bullets {
    var bulletsArray = mutableListOf<Bullet>()

    constructor(width: Int, height: Int, quantity: Int, context: Context) {

        for (i in 1..quantity) {
            //while loop to delay the spawn of enemies
            bulletsArray.add(Bullet(width, height, context))
        }
    }
    fun update(){
        for (b in bulletsArray) {
            b.update()
        }
    }
}