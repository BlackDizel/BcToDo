package org.byters.bctodo.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import org.byters.bctodo.ApplicationToDo
import org.byters.bctodo.R
import org.byters.bctodo.view.presenter.IPresenterNoteCreate
import org.byters.bctodo.view.presenter.callback.IPresenterNoteCreateListener
import javax.inject.Inject

class FragmentNoteCreate : FragmentBase(), View.OnClickListener {

    @Inject
    lateinit var presenterNoteCreate: IPresenterNoteCreate

    lateinit var listenerPresenter: IPresenterNoteCreateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as ApplicationToDo).component.inject(this)
        listenerPresenter = PresenterListener()
        presenterNoteCreate.setListener(listenerPresenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_note_create, container, false)
        initViews(view)
        return view
    }

    private lateinit var etTitle: EditText
    private lateinit var etBody: EditText

    private fun initViews(view: View) {
        etTitle = view.findViewById(R.id.etTitle)
        etBody = view.findViewById(R.id.etBody)
        view.findViewById<View>(R.id.tvSave).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        presenterNoteCreate.onClickSave(etTitle.text.toString(), etBody.text.toString())
    }

    inner class PresenterListener : IPresenterNoteCreateListener {
        override fun finish() {
            if (!isAdded) return
            activity?.onBackPressed()
        }
    }
}