package org.byters.bctodo.view.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import org.byters.bctodo.R
import org.byters.bctodo.view.ui.view.callback.IViewSearchListener

class ViewSearch : FrameLayout, View.OnClickListener, TextView.OnEditorActionListener {

    private var listener: IViewSearchListener? = null

    lateinit var tvSearch: TextView

    lateinit var vContent: View

    constructor(context: Context) : this(context, null) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.view_search, this)
        findViewById<View>(R.id.ivSearch).setOnClickListener(this)
        findViewById<View>(R.id.ivSearchCancel).setOnClickListener(this)
        vContent = findViewById(R.id.flSearch)
        tvSearch = findViewById(R.id.etSearch)
        tvSearch.setOnEditorActionListener(this)
    }

    fun setListener(listener: IViewSearchListener) {
        this.listener = listener
    }

    override fun onClick(v: View?) {
        if (v == null) return
        if (v.id == R.id.ivSearch)
            onClickSearch()
        if (v.id == R.id.ivSearchCancel)
            onClickHide()
    }

    private fun onClickHide() {
        vContent.visibility = View.GONE
        tvSearch.text = ""
        listener?.onHide()
    }

    private fun onClickSearch() {
        if (vContent.visibility == View.GONE) {
            vContent.visibility = View.VISIBLE
            listener?.onShow()
        } else listener?.onQuery(tvSearch.text.toString())
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        listener?.onQuery(tvSearch.text.toString())
        return true
    }

}
