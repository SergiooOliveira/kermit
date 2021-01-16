package ipca.cm.kermit

import java.util.*

class Enemy {
    var x = 0
    var y = 0

    var maxY = 0
    var minY = 0
    var maxX = 0
    var minX = 0

    var playing = true

    private var _isGoingDown = false

    var isGoingDown : Boolean
        get() = _isGoingDown
        set(value) {
            if (y == minY) {
                _isGoingDown = value
            }
        }

    constructor(width: Int, height: Int) {
        maxX = width
        minX = 0
        maxY = height / 4
        minY = 0

        val generator =  Random()
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)
    }

    fun update(){
        if (_isGoingDown) {
            y -= 5
        }else {
            y += 5
        }
    }
}