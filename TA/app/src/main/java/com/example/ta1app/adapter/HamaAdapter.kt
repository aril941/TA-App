package com.example.ta1app.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ta1app.R
import com.example.ta1app.data.DataHama
import com.example.ta1app.data.DataTanaman

class HamaAdapter(
    private val listHama: ArrayList<DataHama>) : RecyclerView.Adapter<HamaAdapter.HamaViewHolder>(){
    private lateinit var onItemClickCallback: HamaAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: HamaAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class HamaViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val namaHama : TextView = itemView.findViewById(R.id.titleList)
        val namaLatinHama: TextView = itemView.findViewById(R.id.titleLatinList)
        val imageHama: ImageView = itemView.findViewById(R.id.imageList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HamaViewHolder {
        val view = View.inflate(parent.context, R.layout.list_kamus, null)
        return HamaViewHolder(view)
    }

    override fun onBindViewHolder(holder: HamaViewHolder, position: Int) {
        val itemHama = listHama[position]
        holder.namaHama.text = itemHama.nama_hama
        holder.namaLatinHama.text = itemHama.nama_latin
        holder.imageHama.setImageResource(itemHama.foto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listHama[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listHama.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataHama)
    }
}
