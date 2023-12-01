package uz.salikhdev.recyclerviewhelper.adapter

import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import uz.salikhdev.recyclerviewhelper.databinding.ItemViewBinding
import uz.salikhdev.recyclerviewhelper.helper.MoveListener
import uz.salikhdev.recyclerviewhelper.model.ItemData

class MyAdapter : RecyclerView.Adapter<MyAdapter.SwipeViewHolder>(), MoveListener {

    private val data = ArrayList<ItemData>()
    private var touchHelper: ItemTouchHelper? = null

    fun setTouchHelper(touchHelper: ItemTouchHelper) {
        this.touchHelper = touchHelper
    }

    fun setData(data: ArrayList<ItemData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: ItemData) {
        this.data.add(data)
        notifyItemInserted(this.data.size)
    }


    inner class SwipeViewHolder(private val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnTouchListener, GestureDetector.OnGestureListener {

        private var gestureDetector: GestureDetector? = null

        init {
            itemView.setOnTouchListener(this)
            gestureDetector = GestureDetector(itemView.context, this)
        }

        fun bindData(data: ItemData) {
            binding.textView.text = "${data.id} ${data.name}"
        }

        override fun onTouch(v: View?, event: MotionEvent): Boolean {

            gestureDetector?.onTouchEvent(event)

            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            return false
        }

        override fun onShowPress(e: MotionEvent) {

        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return false
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            return false
        }

        override fun onLongPress(e: MotionEvent) {
            touchHelper?.startDrag(this)
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return false
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        return SwipeViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun moveItem(startPosition: Int, endPosition: Int) {
        Log.d("MoveItemTag", "moveItem: $startPosition , $endPosition")
        val oldData = data[startPosition]
        data.removeAt(startPosition)
        data.add(endPosition, oldData)
        notifyItemMoved(startPosition, endPosition)
    }

    override fun swipeItem(position: Int, dictation: Int) {

    }
}