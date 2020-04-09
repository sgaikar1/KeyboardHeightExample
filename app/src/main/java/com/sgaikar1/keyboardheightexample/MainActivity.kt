package com.sgaikar1.keyboardheightexample

import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val checkLayout =object :SizeNotifierFrameLayout(this){
           override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
               super.onMeasure(widthMeasureSpec, heightMeasureSpec)
               val keyboardSize =  keyboardHeight
               Toast.makeText(this@MainActivity,"$keyboardSize",Toast.LENGTH_SHORT).show()
               this.postDelayed(Runnable {
                   if(findViewById<TextView>(R.id.tv2)!=null)
                   findViewById<TextView>(R.id.tv2).text = keyboardSize.toString()
               },1000)

           }
       }
//        val tve = TextView(this)
        checkLayout.setDelegate(object:SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate{
            override fun onSizeChanged(keyboardHeight: Int, isWidthGreater: Boolean) {
                Toast.makeText(this@MainActivity,"New Size $keyboardHeight",Toast.LENGTH_SHORT).show()
            }
        })
        findViewById<LinearLayout>(R.id.parent).addView(checkLayout)
        findViewById<RadioGroup>(R.id.rbgroup).setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.adjustNothing->{this@MainActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);}
                    R.id.adjustUnsoecified->{this@MainActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);}
                    R.id.adjustResize->{this@MainActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);}
                    R.id.adjustPan->{this@MainActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);}
                }
            }
        })
    }
}
