package com.example.newsapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coroutin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutin)
        getInformation()
    }

    private fun getInformation(){
        CoroutineScope(IO).launch {
            val info = getInfo()
            val text = getOldInfo(info)
            printResult(text)
        }
    }

    //retrofit запрос
    private suspend fun getOldInfo(result: String): String{
        //retrofit запрос или БД запрос
        val oldResult = "result 2"
        delay(2000)
        return "$result $oldResult"
    }

    //retrofit запрос
    private suspend fun getInfo(): String{
        val result = "result 1"
        delay(2000)
        return result
    }

    private suspend fun printResult(result: String){
        withContext(Main){
            textView.text = result
        }
    }

}