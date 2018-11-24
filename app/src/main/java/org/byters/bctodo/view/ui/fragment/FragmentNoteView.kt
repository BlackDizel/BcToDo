package org.byters.bctodo.view.ui.fragment

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

class FragmentNoteView : FragmentBase() {

    @Inject
    lateinit var presenterNoteView: IPresenterNoteView

    lateinit var listenerPresenter: IPresenterNoteViewListener

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

    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    private fun initViews(view: View) {
        tvTitle = view.findViewById(R.id.tvTitle)
        tvBody = view.findViewById(R.id.tvBody)
    }

    inner class PresenterListener : IPresenterNoteViewListener {
        override fun setData(title: String?, body: String?) {
            if (!isAdded) return
            tvTitle.text = if (TextUtils.isEmpty(title)) "" else title
            tvBody.text = if (TextUtils.isEmpty(body)) "" else body

        }
    }
}
