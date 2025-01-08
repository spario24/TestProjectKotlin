package com.android.testprojectkotlin.adapters

import androidx.recyclerview.widget.DiffUtil


class GenericDiffUtilCallback<T : Any>(
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSame(oldItem, newItem)
    }
}


class GenericDiffUtilCallback1<T : Any>(
    private val selector: (o:T)-> Any
) : DiffUtil.ItemCallback<T>() {

    override fun areContentsTheSame(a: T, b: T): Boolean {
        val x = selector(a)
        val y = selector(b)
        return if(x is Int && y is Int) x==y
        else x==y
    }

    override fun areItemsTheSame(a: T, b: T): Boolean {
        return selector(a) == selector(b)
    }

}