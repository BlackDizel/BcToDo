package org.byters.bctodo.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderBase(viewId: Int, vParent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(vParent.context).inflate(viewId, vParent, false)) {

    abstract fun setData(position: Int)

}
