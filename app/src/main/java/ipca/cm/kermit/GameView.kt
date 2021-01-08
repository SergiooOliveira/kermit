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
    var surfaceHolder : SurfaceHolder? = null
    var gameThread : Thread? = null

    var player : Player? = null
    val kermit : ImageView? = null

    var canvas : Canvas? = null
    var paint : Paint = Paint()

    var squares = arrayListOf<Square>()

    private fun init(context: Context?, width: Int, height: Int){
        surfaceHolder = holder

        kermit?.findViewById<ImageView>(R.id.kermit)
        player = Player(width, height, kermit)

        /*for (i in 1..3) {
            squares.add(Square(width, height))
        }*/
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
                canvas?.drawRect(
                        player?.x!!.toFloat(),
                        player?.y!!.toFloat(),
                        player?.x!!.toFloat() + 100f,
                        player?.y!!.toFloat() + 100f, paint)
                surfaceHolder?.unlockCanvasAndPost(canvas)
            }

        }

        /*surfaceHolder?.let {
            if (it.surface.isValid){
                canvas = surfaceHolder?.lockCanvas()
                canvas?.drawColor(Color.BLACK)

                paint.color = Color.WHITE
                for(s in squares){
                    canvas?.drawRect(
                            s.x.toFloat(),
                            s.y.toFloat(),
                            s.x.toFloat() + 100F,
                            s.y.toFloat() + 100F, paint)
                }

                surfaceHolder?.unlockCanvasAndPost(canvas)
            }
        }*/
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
            MotionEvent.ACTION_UP -> {
            }
            MotionEvent.ACTION_MOVE -> {
                player?.x = event.x.toInt()
            }
        }
        return true

        /*when (event?.action){
            MotionEvent.ACTION_UP ->{

            }
            MotionEvent.ACTION_DOWN ->{
                for(s in squares){
                    if (event.x  >  s.x &&  event.x  <  s.x+100) {
                        if (s.y < event.y && s.y+100 >  event.y) {
                            s.isGoingDown = true
                        }
                    }
                }
            }
        }*/
        return true
    }
}
