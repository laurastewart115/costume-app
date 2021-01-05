package com.example.halloweencostumes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Assignment 2
 * Course:          PROG 39402
 * Author:          Laura Stewart (stelaura)
 * Description:     App dashboard with a list of costumes and an Add button
 */
class MainActivity : AppCompatActivity(), CostumeListAdapter.OnItemClickListener{

    lateinit var costumesAdapter: CostumeListAdapter

    companion object Costumes {
        var costumes: ArrayList<Costume> = ArrayList<Costume>()

        fun addCostume(costume: Costume) {
            costumes.add(costume)
        }

        fun getCostume(index: Int) : Costume? {
            if (0 <= index && index < costumes.count())
                return costumes[index]
            else
                return null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fill recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        costumesAdapter = CostumeListAdapter(costumes, this)
        recyclerView.adapter = costumesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnAdd = findViewById<FloatingActionButton>(R.id.btnGoToAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    //triggered when a list item is clicked
    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("COSTUME", position)
        startActivity(intent)
    }
}