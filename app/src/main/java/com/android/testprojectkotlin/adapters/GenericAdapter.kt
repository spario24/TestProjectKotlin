package com.android.testprojectkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/*
 val adapter = GenericAdapter(
            layoutId = R.layout.item_sample,
            items = dataList
        ) { view, item, position ->
            val textView: TextView = view.findViewById(R.id.textView)
            textView.text = "$position: $item"
        }
* */
class GenericAdapter1<T>(
    private val layoutId: Int,
    private val items: List<T>,
    private val bindView: (View, T, Int) -> Unit
) : RecyclerView.Adapter<GenericAdapter1.GenericViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return GenericViewHolder(view, bindView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class GenericViewHolder<T>(
        private val view: View,
        private val bindView: (View, T, Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: T, position: Int) {
            bindView(view, item, position)
        }
    }
}

/*
*
* val adapter = GenericAdapter(
            items = dataList,
            inflate = ItemSampleBinding::inflate
        ) { binding, item, position ->
            binding.textView.text = "$position: $item"
        }
* */
class GenericAdapter2<T, VB : ViewBinding>(
    private val items: List<T>,
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
    private val onBind: (VB, T, Int) -> Unit
) : RecyclerView.Adapter<GenericAdapter2.GenericViewHolder<VB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<VB> {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<VB>, position: Int) {
        onBind(holder.binding, items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class GenericViewHolder<VB : ViewBinding>(val binding: VB) :
        RecyclerView.ViewHolder(binding.root)
}

/* *
 val adapter = GenericCustomViewAdapter(
            items = dataList,
            createView = { parent -> ItemCustomView(parent.context) },
            bindView = { view, item, position ->
                view.setText("Position $position: $item")
            }
        )
* */
class GenericCustomViewAdapter<T, V : android.view.View>(
    private val items: List<T>,
    private val createView: (ViewGroup) -> V,
    private val bindView: (V, T, Int) -> Unit
) : RecyclerView.Adapter<GenericCustomViewAdapter.GenericViewHolder<V>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<V> {
        val customView = createView(parent)
        return GenericViewHolder(customView)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<V>, position: Int) {
        bindView(holder.view, items[position], position)
    }

    override fun getItemCount(): Int = items.size

    class GenericViewHolder<V : android.view.View>(val view: V) : RecyclerView.ViewHolder(view)
}