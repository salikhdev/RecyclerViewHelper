package uz.salikhdev.recyclerviewhelper.util

import uz.salikhdev.recyclerviewhelper.model.ItemData
import kotlin.random.Random

private var count = 0

fun createData(): ItemData {
    val title = "Name ${Random.nextInt(1, 100)}"
    return ItemData(id = count++, name = title)
}
