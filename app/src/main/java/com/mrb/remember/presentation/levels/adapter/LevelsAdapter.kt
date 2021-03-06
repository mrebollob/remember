package com.mrb.remember.presentation.levels.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrb.remember.R
import com.mrb.remember.domain.extension.inflate
import com.mrb.remember.domain.model.Level
import kotlin.properties.Delegates

class LevelsAdapter : RecyclerView.Adapter<LevelViewHolder>() {

    var levels: List<Level> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChange() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = parent.inflate(R.layout.level_list_item)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val level = levels[position]
        holder.render(level)
    }

    override fun getItemCount(): Int {
        return levels.count()
    }

    private fun notifyDataSetChange() {
        notifyDataSetChanged()
    }
}