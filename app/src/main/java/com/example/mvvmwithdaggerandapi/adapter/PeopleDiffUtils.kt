package com.example.mvvmwithdaggerandapi.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvmwithdaggerandapi.model.People

class PeopleDiffUtils(
    private val oldList: List<People>,
    private val newList: List<People>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}