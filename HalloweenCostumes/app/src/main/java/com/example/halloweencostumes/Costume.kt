package com.example.halloweencostumes

/**
 * Assignment 2
 * Course:          PROG 39402
 * Author:          Laura Stewart (stelaura)
 * Description:     A costume with a name, picture, materials, and steps for creating it.
 */
class Costume {
    var costumeName: String
    var imageFile: String
    var materials: ArrayList<String>
    var steps: ArrayList<String>

    constructor(costumeName: String, imageFile: String, materials: ArrayList<String>, steps: ArrayList<String>) {
        this.costumeName = costumeName
        this.imageFile = imageFile
        this.materials = materials
        this.steps = steps
    }
}