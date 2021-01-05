package com.example.halloweencostumes

import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

/**
 * Assignment 2
 * Course:          PROG 39402
 * Author:          Laura Stewart (stelaura)
 * Description:     RecyclerView list adapater for costumes
 */
class CostumeListAdapter(private val costumes: List<Costume>,private val listener: OnItemClickListener): RecyclerView.Adapter<CostumeListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostumeListAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.costume_list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CostumeListAdapter.MyViewHolder, position: Int) {
        val costume = costumes[position]
     //   val bitmapImage = BitmapFactory.decodeFile(costume.imageFile)
        holder.imageCostume.setImageURI(Uri.parse(costume.imageFile))
        holder.txtCostumeName.text = costume.costumeName
    }


    override fun getItemCount(): Int {
        return costumes.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val txtCostumeName = itemView.findViewById<MaterialTextView>(R.id.textViewCostumeName)
        val imageCostume = itemView.findViewById<ShapeableImageView>(R.id.imageCostume)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
