package ipca.cm.kermit

import android.app.Activity
import android.graphics.Point
import android.os.Bundle

class Game : Activity() {

    var gameView : GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        gameView = GameView(this, size.x, size.y )
        setContentView(gameView)
    }

    override fun onPause() {
        super.onPause()
        gameView?.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView?.resume()
    }
}