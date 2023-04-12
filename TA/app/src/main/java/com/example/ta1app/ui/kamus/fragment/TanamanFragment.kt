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
import com.example.ta1app.adapter.TanamanAdapter
import com.example.ta1app.data.DataTanaman
import com.example.ta1app.ui.detail.DetailActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TanamanFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var listTanaman: ArrayList<DataTanaman>

    lateinit var namaTanaman: Array<String>
    lateinit var namaLatinTanaman: Array<String>
    lateinit var deskripsiTanaman: Array<String>
    lateinit var fotoTanaman : Array<Int>

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
        dataTanaman()
        showRecyclerList()

    }

    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val listTanamanAdapter = TanamanAdapter(listTanaman)
        recyclerView.adapter = listTanamanAdapter
        listTanamanAdapter.setOnItemClickCallback(object : TanamanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataTanaman) {
                showSelectedList(data)
            }
        })

    }

    private fun showSelectedList(data: DataTanaman) {
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra("DATA_TANAMAN",data)
        startActivity(i)
    }


    private fun dataTanaman(){
        listTanaman = arrayListOf()
        namaTanaman = arrayOf(
            getString(R.string.padi_pulen),
            getString(R.string.padi_gogo),
            getString(R.string.padi_ketan),
            getString(R.string.padi_wangi),
        )
        namaLatinTanaman = arrayOf(
            getString(R.string.latin_padi_pulen),
            getString(R.string.latin_padi_gogo),
            getString(R.string.latin_padi_ketan),
            getString(R.string.latin_padi_wangi)
        )

        fotoTanaman = arrayOf(
            R.drawable.padi_pulen,
            R.drawable.padi_gogo,
            R.drawable.padi_ketan,
            R.drawable.padi_wangi,
        )
        deskripsiTanaman = arrayOf(
            getString(R.string.desc_padi_pulen),
            getString(R.string.desc_padi_gogo),
            getString(R.string.desc_padi_ketan),
            getString(R.string.desc_padi_wangi)
        )
        for (i in namaTanaman.indices){
            val tanaman = DataTanaman(
                i,
                namaTanaman[i],
                namaLatinTanaman[i],
                fotoTanaman[i],
                deskripsiTanaman[i]
            )
            listTanaman.add(tanaman)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TanamanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}