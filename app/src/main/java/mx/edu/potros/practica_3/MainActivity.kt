package mx.edu.potros.practica_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView=findViewById(R.id.guessing)
        val down: Button=findViewById(R.id.down)
        val up: Button=findViewById(R.id.up)
        val generate: Button=findViewById(R.id.generate)
        val guessed: Button=findViewById(R.id.guessed)

        generate.setOnClickListener {
            num=Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility= View.INVISIBLE
            guessed.visibility= View.VISIBLE
            down.isEnabled = true
            up.isEnabled = true
        }

        up.setOnClickListener {
            minValue=num
            if(checkigLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :( Me ganastes")
            }
        }

        down.setOnClickListener {
            maxValue=num
            if(checkigLimits()){
                num = Random.nextInt(minValue,maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText(num.toString())
            }
        }

        guessed.setOnClickListener {
            if(!won){
                guessings.setText("Adivine tu numero es el $num")
                guessed.setText("Volver a jugar")
                down.isEnabled = false
                up.isEnabled = false
                won=true
            }
            else{
                generate.visibility=View.VISIBLE
                guessings.setText("Tap on generate to Start")
                guessed.setText("Guessed")
                guessed.visibility=View.GONE
                resetValue()
            }
        }
    }

    fun resetValue(){
        minValue=0
        maxValue=100
        num=0
        won=false
    }

    fun checkigLimits():Boolean{
        return minValue!=maxValue
    }
}