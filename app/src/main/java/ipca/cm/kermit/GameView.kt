package ipca.cm.kermit

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.ImageView


class GameView : SurfaceView, Runnable {

    //life removal timer + boolean so it doesn't remove the player's lives all at once
    var lifeRemoval : Boolean = true
    var lifeRemovalTimer : Int = 0

    //game variables
    var surfaceHolder : SurfaceHolder? = null
    var gameThread : Thread? = null
    var canvas : Canvas? = null
    var paint : Paint = Paint()
    var _isMoving = false

    //player variables
    var player : Player? = null
    val kermit : ImageView? = null

    //enemy variables
    var enemies : Enemies? = null
    var playing = false

    //bullet variables
    var bullets : Bullets? = null
    var createBullet = false
    var bulletCount = 0

    var mContext : Context? = null

    var lifeIcon : Bitmap? = null
    var coinScore : Bitmap? = null
    var background : Bitmap? = null

    private fun init(context: Context?, width: Int, height: Int){
        surfaceHolder = holder
        player = Player(width, height, context!!)
        enemies = Enemies(width, height, 3, context)
        mContext = getContext()
        bullets = Bullets(context, height)
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
        bullets?.update()
        for (e in enemies?.enemiesArray!!) {

            if (e.y >= e.maxY * 4 && e.playing) {
                e.playing = false
                if (player?.lifesRemaining!! > 0) player?.lifesRemaining = player?.lifesRemaining!! - 1
                else {
                    val intent = Intent(mContext, Scores::class.java)
                    mContext?.startActivity(intent)
                }
            }

            if (Rect.intersects(player!!.collisionRect, e.collisionRect) && lifeRemoval && lifeRemovalTimer >= 120){
                e.playing = false
                lifeRemoval = false
                lifeRemovalTimer = 0
                if (player?.lifesRemaining!! > 0) player?.lifesRemaining = player?.lifesRemaining!! - 1
                else {
                    val intent = Intent(mContext, Scores::class.java)
                    mContext?.startActivity(intent)
                }
            }

            for (b in bullets?.bulletsArray!!) {
                if (Rect.intersects(b.collisionRect, e.collisionRect) && b.active) {
                    e.playing = false
                    b.active = false
                    player?.currentScore = player?.currentScore?.plus(100)!!
                }
            }
            if (lifeRemovalTimer > 120) lifeRemoval = true
            lifeRemovalTimer++
        }
        player!!.bitTimer++
    }

    private fun control() {
        Thread.sleep(17L)
    }

    private fun draw() {
        surfaceHolder?.let {
            if (it.surface.isValid) {
                canvas = surfaceHolder?.lockCanvas()
                canvas?.drawColor(Color.BLACK)

                /*background = BitmapFactory.decodeResource(context.resources, R.drawable.fundo)
                background = Bitmap.createScaledBitmap(background!!, width, height, true)
                canvas?.drawBitmap(background!!, 0f, 0f, null)*/

                //lives left text drawing
                paint.color = Color.WHITE
                paint.textSize = 40F

                lifeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.life1)
                lifeIcon = Bitmap.createScaledBitmap(lifeIcon!!, 50, 50, true)
                canvas?.drawBitmap(lifeIcon!!, 0f, 10f, paint)
                canvas?.drawText(player?.lifesRemaining.toString(), 50f, 50f, paint)

                coinScore = BitmapFactory.decodeResource(context.resources, R.drawable.point)
                coinScore = Bitmap.createScaledBitmap(coinScore!!, 50, 50, true)

                canvas?.drawBitmap(coinScore!!, 100f, 10f, paint)
                canvas?.drawText(player?.currentScore.toString(), 150f, 50f, paint)

                //enemies drawing cycle
                for (e in enemies?.enemiesArray!!) {
                    if (e.playing)
                        canvas?.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }
                player?.changeBitMap()
                canvas?.drawBitmap(player!!.bitDraw, player!!.x.toFloat(), player!!.y.toFloat(), paint)

                //drawing bullets
                if (bullets?.bulletsArray!!.size > 0) {
                    for (b in bullets?.bulletsArray!!)
                        if (b.active)
                            canvas?.drawBitmap(b.bitmap, b.x.toFloat(), b.y.toFloat(), paint)
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
                _isMoving = true
            }

            MotionEvent.ACTION_UP-> {
                if (!_isMoving) bullets?.addBullet(player?.KermiteWidth!!, player?.KermitHeight!!)
                _isMoving = false
            }
        }
        return true
    }
}
