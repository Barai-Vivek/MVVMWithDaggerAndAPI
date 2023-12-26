package com.example.mvvmwithdaggerandapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmwithdaggerandapi.databinding.PeopleAdapterItemBinding
import com.example.mvvmwithdaggerandapi.model.People
import javax.inject.Inject
import javax.xml.transform.ErrorListener


class PeopleAdapter @Inject constructor() : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var peopleList = emptyList<People>()
    private var _binding: PeopleAdapterItemBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding should not be accessed before onCreateView or after onDestroyView")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding =
            PeopleAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("name => ${peopleList[position].name}")
        holder.bind(peopleList[position], position)
    }

    private var onItemClickListener: ((People) -> Unit)? = null

    fun setItemClickListener(listener: (People) -> Unit) {
        onItemClickListener = listener
    }

    inner class ViewHolder(private val binding: PeopleAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: People, adapterPosition: Int) {
            binding.people = people
            binding.position = adapterPosition
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(people)
                }
            }
        }
    }

    fun setData(newData: List<People>) {
        val peopleDiffUtils = PeopleDiffUtils(peopleList, newData)
        val diffUtils = DiffUtil.calculateDiff(peopleDiffUtils)
        peopleList = newData
        diffUtils.dispatchUpdatesTo(this)
    }
}