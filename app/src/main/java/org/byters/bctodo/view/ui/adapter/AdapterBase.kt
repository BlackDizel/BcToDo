package org.byters.bctodo.view.ui.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class AdapterBase : RecyclerView.Adapter<ViewHolderBase>() {

    override fun onBindViewHolder(holder: ViewHolderBase, position: Int) {
        holder.setData(position)
    }

}
