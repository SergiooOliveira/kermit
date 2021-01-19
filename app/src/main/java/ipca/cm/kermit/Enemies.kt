package ipca.cm.kermit

import android.content.Context

class Enemies {
    val enemiesArray = arrayListOf<Enemy>()
    var spawnTimer = 0
    constructor(width: Int, height: Int, quantity: Int, context: Context) {

        for (i in 1..quantity) {
            //while loop to delay the spawn of enemies
            //TODO: get the delays working for the first time
            while ( spawnTimer < 50000) {
                if (spawnTimer == 30000) {
                    enemiesArray.add(Enemy(width, height, context))
                    spawnTimer++
                }
                else
                    spawnTimer++
            }
            spawnTimer = 0
        }
    }

    fun update(height: Int) {
        for (e in enemiesArray) {
            e.update(height)
        }
    }
}
