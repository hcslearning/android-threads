package cl.hardcybersoft.learning.android.hilos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var boton:Button? = null
    var texto:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton = findViewById<Button>(R.id.boton)
        texto = findViewById<TextView>(R.id.texto)

        this.boton?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                // con esta línea solo basta, se utilizarán Threads solo como ejemplo sencillo
                texto?.setText("holiiiii")

                // Begin thread
                object: Thread(object: Runnable{
                    override fun run() {
                        val nuevoTexto:String = "Hola soy un nuevo texto desde un Thread"
                        //texto?.setText(nuevoTexto) // this line throws exceptions, secondary thread not allowed modify UI

                        // method runOnUiThread allow change primary interface
                        runOnUiThread(object: Runnable{
                            override fun run() {
                                texto?.setText(nuevoTexto)
                            }
                        })
                    }
                }) {}.start() // always call start() for begin
                // End thread
            }

        })
    }


}