package com.jsn.android_advanced_recyclerview.multiple_view_types

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsn.android_advanced_recyclerview.R

class MultipleViewTypesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_view_types)

        val rvMain = findViewById<RecyclerView>(R.id.rvParent)
        rvMain.layoutManager = LinearLayoutManager(this@MultipleViewTypesActivity)
        val multipleViewTypesAdapter = MultipleViewTypesAdapter(this@MultipleViewTypesActivity)
        rvMain.adapter = multipleViewTypesAdapter

    }
}