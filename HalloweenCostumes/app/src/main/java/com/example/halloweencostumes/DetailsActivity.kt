package com.example.halloweencostumes

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

/**
 * Assignment 2
 * Course:          PROG 39402
 * Author:          Laura Stewart (stelaura)
 * Description:     Shows the steps and materials for making a costume.
 */
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // get the costume index
        val index = intent.getIntExtra("COSTUME", -1)

        if (index > -1) {
            val costume = MainActivity.getCostume(index)

            // display details
            val txtTitle = findViewById<MaterialTextView>(R.id.textViewDetailsTitle)
            txtTitle.text = costume?.costumeName

            val imageView = findViewById<ShapeableImageView>(R.id.imageDetailsCostume)
            imageView.setImageURI(Uri.parse(costume?.imageFile))

            // create formatted string for the items
            var itemsFormatted = ""
            for(item in costume?.materials!!) {
                itemsFormatted += "${item}\n"
            }
            val txtItems = findViewById<MaterialTextView>(R.id.textViewItems)
            txtItems.text = itemsFormatted

            var stepsFormatted = ""
            for (step in costume?.steps!!) {
                stepsFormatted += "${step}\n"
            }
            val txtSteps = findViewById<MaterialTextView>(R.id.textViewSteps)
            txtSteps.text = stepsFormatted
        }
    }
}