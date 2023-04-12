package com.example.ta1app.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ta1app.R
import com.example.ta1app.data.DataTanaman

class TanamanAdapter(
    private val listTanaman: ArrayList<DataTanaman>) : RecyclerView.Adapter<TanamanAdapter.TanamanViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

        class TanamanViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
            val namaTanaman : TextView = itemView.findViewById(R.id.titleList)
            val namaLatinTanaman: TextView = itemView.findViewById(R.id.titleLatinList)
            val imageTanaman: ImageView = itemView.findViewById(R.id.imageList)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanamanViewHolder {
        val view = View.inflate(parent.context, R.layout.list_kamus, null)
        return TanamanViewHolder(view)
    }

    override fun onBindViewHolder(holder: TanamanViewHolder, position: Int) {
        val itemTanaman = listTanaman[position]
        holder.namaTanaman.text = itemTanaman.nama_tanaman
        holder.namaLatinTanaman.text = itemTanaman.nama_latin
        holder.imageTanaman.setImageResource(itemTanaman.foto)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTanaman[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listTanaman.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTanaman)
    }
}



