package com.caferk.my_movies.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.caferk.kotlinbasearchitecture.domain.entity.ResultsItem
import com.caferk.my_movies.BaseApplication
import com.caferk.my_movies.R
import javax.inject.Inject

/**
 * Created by cafer on 13.3.2018.
 */
class RecyclerMovieAdapter @Inject constructor(
        val list: MutableList<ResultsItem>,
        val context: BaseApplication
) : RecyclerView.Adapter<RecyclerMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun fillList(freshList: List<ResultsItem>?) {
        freshList?.toMutableList()?.let { list.addAll(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.tv_title)
        lateinit var tvMovieTitle: TextView

        init {
            ButterKnife.bind(this, view)
        }

        fun bind(resultsItem: ResultsItem) {
            tvMovieTitle.text = resultsItem.title
        }
    }
}