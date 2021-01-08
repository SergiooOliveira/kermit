package ipca.cm.kermit

import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class Player {

    // player position
    var x = 0
    var y = 0

    // number of lifes
    var lifesRemaining = 3

    // score
    var currentScore = 0

    // imageView
    var imageView : ImageView? = null

    constructor(width: Int, height: Int, imageView: ImageView?){
        x = width / 2
        y = height - 500
        this.imageView = imageView
    }
}