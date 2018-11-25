package org.byters.bctodo.view.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterNoteEdit
import org.byters.bctodo.view.presenter.callback.IPresenterNoteEditListener
import javax.inject.Inject

class FragmentNoteEdit : FragmentBase(), View.OnClickListener {

    @Inject
    lateinit var presenterNoteEdit: IPresenterNoteEdit

    lateinit var listenerPresenter: IPresenterNoteEditListener

    private lateinit var etTitle: TextView
    private lateinit var etBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as ApplicationToDo).component.inject(this)
        listenerPresenter = PresenterListener()
        presenterNoteEdit.setListener(listenerPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_note_edit, container, false)
        initViews(view)
        presenterNoteEdit.onCreateView()
        return view
    }

    private fun initViews(view: View) {
        etTitle = view.findViewById(R.id.etTitle)
        etBody = view.findViewById(R.id.etBody)
        view.findViewById<View>(R.id.tvSave).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        presenterNoteEdit.onClickSave(etTitle.text.toString(), etBody.text.toString())
    }

    inner class PresenterListener : IPresenterNoteEditListener {
        override fun setData(title: String?, body: String?) {
            if (!isAdded) return
            if (!isAdded) return
            etTitle.text = if (TextUtils.isEmpty(title)) "" else title
            etBody.text = if (TextUtils.isEmpty(body)) "" else body
        }

        override fun finish() {
            if (!isAdded) return
            activity?.onBackPressed()
        }
    }
}
