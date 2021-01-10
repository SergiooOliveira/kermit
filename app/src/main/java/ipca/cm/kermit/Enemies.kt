package ipca.cm.kermit

class Enemies {
    val enemiesArray = arrayListOf<Enemie>()

    constructor(width: Int, height: Int, quantity: Int) {
        for (i in 1..quantity) {
            enemiesArray.add(Enemie(width, height))
        }
    }

    fun update() {
        for (e in enemiesArray)
            e.update()
    }
}