package ipca.cm.kermit

class Enemies {
    val enemiesArray = arrayListOf<Enemy>()

    constructor(width: Int, height: Int, quantity: Int) {
        for (i in 1..quantity) {
            enemiesArray.add(Enemy(width, height))
        }
    }

    fun update() {
        for (e in enemiesArray)
            e.update()
    }
}
