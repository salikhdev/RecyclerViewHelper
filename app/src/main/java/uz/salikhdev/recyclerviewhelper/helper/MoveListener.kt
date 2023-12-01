package uz.salikhdev.recyclerviewhelper.helper

interface MoveListener {

    fun moveItem(startPosition: Int, endPosition: Int)
    fun swipeItem(position: Int, dictation: Int)

}