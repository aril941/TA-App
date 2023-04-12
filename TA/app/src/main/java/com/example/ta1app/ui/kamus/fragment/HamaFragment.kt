package com.example.ta1app.ui.kamus.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ta1app.R
import com.example.ta1app.adapter.HamaAdapter
import com.example.ta1app.data.DataHama
import com.example.ta1app.ui.detail.DetailActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HamaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var listHama: ArrayList<DataHama>

    lateinit var namaHama: Array<String>
    lateinit var namaLatinHama: Array<String>
    lateinit var deskripsiHama: Array<String>
    lateinit var fotoHama : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kamus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_kamus)
        recyclerView.setHasFixedSize(true)
        dataHama()
        showRecyclerList()

    }

    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val listHamaAdapter = HamaAdapter(listHama)
        recyclerView.adapter = listHamaAdapter
        listHamaAdapter.setOnItemClickCallback(object : HamaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataHama) {
                showSelectedHama(data)
            }
        })
    }

    private fun showSelectedHama(data: DataHama) {
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra("DATA_HAMA",data)
        startActivity(i)
    }

    private fun dataHama(){
        listHama = arrayListOf()
        namaHama = arrayOf(
            getString(R.string.bercak_coklat),
            getString(R.string.busuk_leher),
            getString(R.string.hawar_daun)
        )
        namaLatinHama = arrayOf(
            getString(R.string.latin_busuk_leher),
            getString(R.string.latin_busuk_leher),
            getString(R.string.latin_busuk_leher)
        )
        deskripsiHama = arrayOf(
            getString(R.string.desc_bercak_coklat),
            getString(R.string.desc_busuk_leher),
            getString(R.string.desc_hawar_daun)
        )
        fotoHama = arrayOf(
            R.drawable.bercak_coklat,
            R.drawable.busuk_leher,
            R.drawable.hawar_daun
        )
        for (i in namaHama.indices){
            val Hama = DataHama(
                i,
                namaHama[i],
                namaLatinHama[i],
                fotoHama[i],
                deskripsiHama[i]
            )
            listHama.add(Hama)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HamaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}