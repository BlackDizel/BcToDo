package org.byters.bctodo.view.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterNoteView
import org.byters.bctodo.view.presenter.callback.IPresenterNoteViewListener
import javax.inject.Inject

class FragmentNoteView : FragmentBase(), View.OnClickListener {

    @Inject
    lateinit var presenterNoteView: IPresenterNoteView

    lateinit var listenerPresenter: IPresenterNoteViewListener

    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as ApplicationToDo).component.inject(this)
        listenerPresenter = PresenterListener()
        presenterNoteView.setListener(listenerPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_note_view, container, false)
        initViews(view)
        presenterNoteView.onCreateView()
        return view
    }

    private fun initViews(view: View) {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvBody = view.findViewById(R.id.tvBody)
        view.findViewById<View>(R.id.ivEdit).setOnClickListener(this)
        view.findViewById<View>(R.id.ivDelete).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.ivEdit)
            presenterNoteView.onClickEdit()
        if (v?.id == R.id.ivDelete)
            presenterNoteView.onClickDelete()
    }

    inner class PresenterListener : IPresenterNoteViewListener {
        override fun setData(title: String?, body: String?, font:Typeface) {
            if (!isAdded) return
            tvTitle.text = if (TextUtils.isEmpty(title)) "" else title
            tvBody.text = if (TextUtils.isEmpty(body)) "" else body
            setFont(tvTitle, tvBody, font=font)
        }

        fun setFont(vararg items: TextView, font: Typeface) {
            items.forEach { it.setTypeface(font) }

        }

        override fun finish() {
            if (!isAdded) return
            activity?.onBackPressed()
        }
    }
}
