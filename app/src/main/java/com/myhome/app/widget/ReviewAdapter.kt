package com.myhome.app.widget

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.myhome.app.data.model.Article
import android.view.View
import android.content.Context
import android.view.LayoutInflater
import com.myhome.app.R
import kotlinx.android.synthetic.main.list_item.view.*

class ReviewAdapter  (private var context: Context, private var items:MutableList<Article>,
                      var type: Int) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v :View

        if(viewType == 1){
            v = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
            return MyListViewHolder(v)
        }
        v = LayoutInflater.from(parent?.context).inflate(R.layout.grid_item, parent, false)
        return MyGridViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item : Article = items.get(position)

        if(holder?.itemViewType == 1){
            GlideApp.with(context).load(item.media[0].uri).into((holder as MyListViewHolder).avatar)
            holder.name.text = item.title
        }else{
            GlideApp.with(context).load(item.media[0].uri).into((holder as MyGridViewHolder).avatar)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return type;
    }

    override fun getItemCount(): Int {
        return items.size
    }



    fun updateItems(newUsers:List<Article>){
        val size : Int = items.size
        items.addAll(newUsers)
        notifyDataSetChanged()
    }


    inner class MyListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.name
        var avatar : ImageView = view.avatar


    }


    inner class MyGridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatar : ImageView = view.avatar


    }


}