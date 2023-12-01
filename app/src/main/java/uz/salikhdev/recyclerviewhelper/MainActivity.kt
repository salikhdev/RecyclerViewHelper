package uz.salikhdev.recyclerviewhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import uz.salikhdev.recyclerviewhelper.adapter.MyAdapter
import uz.salikhdev.recyclerviewhelper.databinding.ActivityMainBinding
import uz.salikhdev.recyclerviewhelper.helper.MoveHelperCallback
import uz.salikhdev.recyclerviewhelper.util.createData

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setAdapter()

    }

    private fun setAdapter() {

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this , 3)

        val helper = ItemTouchHelper(MoveHelperCallback(adapter))
        helper.attachToRecyclerView(binding.recyclerView)
        adapter.setTouchHelper(helper)

        binding.addButton.setOnClickListener {
            adapter.addData(createData())
        }

    }


}