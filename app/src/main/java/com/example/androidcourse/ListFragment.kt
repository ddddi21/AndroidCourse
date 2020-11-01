package com.example.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {
    private var adapter: CatAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val recycle = view.findViewById<RecyclerView>(R.id.rv_cat)

        adapter = CatAdapter {
            Toast.makeText(context, "hello $it", Toast.LENGTH_SHORT).show()
        }
        adapter?.submitList(CatRepository.getCats())
        recycle.adapter = adapter
        recycle.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        return view
    }
}