package com.jsn.android_advanced_recyclerview


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jsn.android_advanced_recyclerview.multiple_view_types.MultipleViewTypesActivity
import com.jsn.android_advanced_recyclerview.pagination.PaginationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.firstButton).setOnClickListener {
//            ViewCompat.getWindowInsetsController(it)?.hide(WindowInsetsCompat.Type.systemBars())
            startActivity(Intent(this@MainActivity, MultipleViewTypesActivity::class.java))
        }
        findViewById<Button>(R.id.secondButton).setOnClickListener {
            startActivity(Intent(this@MainActivity, MultipleViewTypesActivity::class.java))
        }
        findViewById<Button>(R.id.thirdButton).setOnClickListener {
            startActivity(Intent(this@MainActivity, PaginationActivity::class.java))
        }
    }
}