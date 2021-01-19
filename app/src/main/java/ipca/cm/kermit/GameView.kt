package ipca.cm.kermit

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.ImageView

class GameView : SurfaceView, Runnable {

    var playing = false

    //life removal timer + boolean so it doesn't remove the player's lives all at once
    var lifeRemoval : Boolean = true
    var lifeRemovalTimer : Int = 0

    var surfaceHolder : SurfaceHolder? = null
    var gameThread : Thread? = null

    var player : Player? = null
    val kermit : ImageView? = null
    var enemies : Enemies? = null

    var canvas : Canvas? = null
    var paint : Paint = Paint()

    private fun init(context: Context?, width: Int, height: Int){
        surfaceHolder = holder
        player = Player(width, height, context!!)
        enemies = Enemies(width, height, 3, context!!)
    }

    constructor(context: Context?, width: Int, height: Int) : super(context){
        init(context, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context, 0, 0)
    }

    override fun run() {
        while (playing){
            update()
            draw()
            control()
        }
    }

    private fun update() {
        player?.update()
        enemies?.update(player!!.KermitHeight)
        for (e in enemies?.enemiesArray!!) {

            if (e.y >= e.maxY * 4 && e.playing) {
                e.playing = false
                if (player?.lifesRemaining!! > 0) player?.lifesRemaining = player?.lifesRemaining!! - 1
                //TODO: gameover logic
                //else
            }

            if (Rect.intersects(player!!.collisionRect, e.collisionRect) && lifeRemoval && lifeRemovalTimer >= 120){
                e.playing = false
                lifeRemoval = false
                lifeRemovalTimer = 0
                if (player?.lifesRemaining!! > 0) player?.lifesRemaining = player?.lifesRemaining!! - 1
                //TODO: gameover logic
                //else
            }

            if (lifeRemovalTimer > 120) lifeRemoval = true
            lifeRemovalTimer++
        }
        //TODO: shooting and bullet collision testing + score
        if (player?.isShooting!!) {
            player?.shoot()
            if (player?.shootY!! <= height)
                player?.isShooting = false
        }
    }

    private fun control() {
        Thread.sleep(17L)
    }

    private fun draw() {
        surfaceHolder?.let {
            if (it.surface.isValid) {
                canvas = surfaceHolder?.lockCanvas()
                canvas?.drawColor(Color.BLACK)
                paint.color = Color.WHITE

                //lives left text drawing
                paint.textSize = 40F
                canvas?.drawText("Lives remaining: " + player?.lifesRemaining, 0f, 50f, paint)

                //enemies drawing cycle
                for (e in enemies?.enemiesArray!!) {
                    if (e.playing)
                        canvas?.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }

                //player drawing
                canvas?.drawBitmap(player!!.bitmap, player!!.x.toFloat(), player!!.y.toFloat(), paint)

                //drawing bullets
                paint.color = Color.GREEN
                if (player?.isShooting!!) {
                    canvas?.drawRect(
                            player?.shootX!!.toFloat(),
                            player?.shootY!!.toFloat(),
                            player?.shootX!!.toFloat() + 100f,
                            player?.shootY!!.toFloat() + 100f,
                            paint)
                }
                surfaceHolder?.unlockCanvasAndPost(canvas)
            }
        }
    }

    fun pause(){
        playing = false
        gameThread?.join()
    }

    fun resume(){
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                player?.x = event.x.toInt()
            }
            MotionEvent.ACTION_DOWN -> {
                // shoot tongue
                player?.shootX = player?.x!!
                player?.isShooting = true
            }
        }
        return true
    }
}
