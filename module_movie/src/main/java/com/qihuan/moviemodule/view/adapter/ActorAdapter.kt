package com.qihuan.moviemodule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.qihuan.commonmodule.base.AutoUpdatableAdapter
import com.qihuan.commonmodule.utils.load
import com.qihuan.moviemodule.R
import com.qihuan.moviemodule.model.bean.PersonBean
import net.wujingchao.android.view.SimpleTagImageView
import kotlin.properties.Delegates


/**
 * ActorAdapter
 * @author qi
 * @date 2018/10/18
 */
class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var itemList: List<PersonBean> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        autoNotify(oldValue, newValue) { old, new ->
            old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_act_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.ivActor?.load(item.avatars.medium)
        holder.ivActor?.tagEnable = item.isDirector
        holder.tvActor?.text = item.name
        holder.itemView.setOnClickListener {

        }
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivActor: SimpleTagImageView? = itemView.findViewById(R.id.iv_act)
        val tvActor: TextView? = itemView.findViewById(R.id.tv_name)
    }
}