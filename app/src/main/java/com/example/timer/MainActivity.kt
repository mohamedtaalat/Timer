package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var time:Long=1000*60*25
    var countDown:Long=1000
    var remainingTime:Long=time
    var timer:CountDownTimer?=null
    var isRemaining:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Gobtn.setOnClickListener{
            if (!isRemaining){
            StartBtn()
                binding.textView.text = "Keep going....."
            }
        }

        binding.RestTv.setOnClickListener{
            ResetTimer()
            binding.textView.text="Make Timer...."
            isRemaining=false
        }

    }

    private fun StartBtn() {
         timer = object : CountDownTimer(remainingTime, countDown) {
            override fun onTick(timeleft: Long) {

                     remainingTime=timeleft
                     binding.Timer.text = timeleft.toString()
                     String.format("02dp:02dp")
                     UpdateTime()


            }

            override fun onFinish() {

                Toast.makeText(this@MainActivity, "Finish!!", Toast.LENGTH_SHORT).show()
                isRemaining=false
            }
        }.start()

    }
    fun UpdateTime(){
        val minute = remainingTime.div(1000).div(60)
        val second= remainingTime.div(1000)%60
        val formatTime= String.format("%02d:%02d",minute,second)
        binding.Timer.text=formatTime
    }
    fun ResetTimer(){
        timer?.cancel()
        remainingTime=time
        UpdateTime()

    }
}