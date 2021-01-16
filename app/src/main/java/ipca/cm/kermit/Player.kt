package ipca.cm.kermit

import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

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

    // imageView
    var imageView : ImageView? = null

    constructor(width: Int, height: Int, imageView: ImageView?){
        x = width / 2
        y = height - (height / 4)
        shootY = y
        this.imageView = imageView
    }

    fun shoot() {
        shootY -= 5
    }
}