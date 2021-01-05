package com.example.halloweencostumes

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Assignment 2
 * Course:          PROG 39402
 * Author:          Laura Stewart (stelaura)
 * Description:     RecyclerView list adapater for costumes
 * Reference:       Tutorial for image picker: https://devofandroid.blogspot.com/2018/09/pick-image-from-gallery-android-studio_15.html
 */
class CreateActivity : AppCompatActivity() {

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    private var imageString = ""
    lateinit var imageView: ShapeableImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val btnImagePicker = findViewById<MaterialButton>(R.id.btnChooseImage)
        imageView = findViewById<ShapeableImageView>(R.id.imageCreateCostume)
        val editTextTitle = findViewById<TextInputEditText>(R.id.textFieldCostumeTitle)
        val editTextItems = findViewById<TextInputEditText>(R.id.textFieldCostumeItems)
        val editTextSteps = findViewById<TextInputEditText>(R.id.textFieldCostumeSteps)
        val btnSave = findViewById<MaterialButton>(R.id.btnSaveCostume)

        btnImagePicker.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                //show popup to request runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else{
                //permission already granted
                pickImageFromGallery();
            }
        }

        btnSave.setOnClickListener {
            val title = editTextTitle.text.toString()

            val itemsString = editTextItems.text.toString()
            val itemsArray = ArrayList(itemsString.split("\n"))

            val stepsString = editTextSteps.text.toString()
            val stepsArray = ArrayList(stepsString.split("\n"))

            if (!imageString.isNullOrEmpty() && !title.isNullOrEmpty() && itemsArray.count() > 0 && stepsArray.count() > 0) {
                // make a costume object and store it
                val costume = Costume(title, imageString, itemsArray, stepsArray)
                MainActivity.addCostume(costume)

                // go back to the main activity
                Toast.makeText(applicationContext,"Costume saved. Have a spoopy day!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(applicationContext,"Fill all the fields, or else.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageString = data?.data.toString()

            imageView.setImageURI(Uri.parse(imageString))
        }
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}