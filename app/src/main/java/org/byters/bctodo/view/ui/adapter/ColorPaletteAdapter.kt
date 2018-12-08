package org.byters.bctodo.view.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.jaredrummler.android.colorpicker.ColorPanelView
import org.byters.bctodo.R

internal class ColorPaletteAdapter(val listener: OnColorSelectedListener, val colors: IntArray) : BaseAdapter() {

    var selectedPosition: Int = 0

    override fun getCount(): Int = colors.size


    override fun getItem(position: Int): Int = colors[position]


    override fun getItemId(position: Int): Long = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            holder = ViewHolder(parent.context)
            convertView = holder.view
        } else
            holder = convertView.tag as ViewHolder

        holder.setup(position)
        return convertView
    }


    internal interface OnColorSelectedListener {

        fun onColorSelected(color: Int)
    }

    private inner class ViewHolder internal constructor(context: Context) {

        internal var colorPanelView: ColorPanelView
        internal var imageView: ImageView
        internal var view: View

        init {
            view = View.inflate(context, R.layout.cpv_color_item_square, null)
            colorPanelView = view.findViewById(R.id.cpv_color_panel_view)
            imageView = view.findViewById(R.id.cpv_color_image_view)
            view.tag = this
        }

        internal fun setup(position: Int) {
            colorPanelView.color = colors[position]
            imageView.setImageResource(if (selectedPosition == position) R.drawable.cpv_preset_checked else 0)
            setOnClickListener(position)
        }

        private fun setOnClickListener(position: Int) {
            colorPanelView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    listener.onColorSelected(colors[position])
                }
            })
            colorPanelView.setOnLongClickListener(object : View.OnLongClickListener {
                override fun onLongClick(v: View): Boolean {
                    colorPanelView.showHint()
                    return true
                }
            })
        }
    }
}