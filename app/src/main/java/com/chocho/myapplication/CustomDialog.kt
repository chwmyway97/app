package com.chocho.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomDialog(private val doneText: String, private val listener: OnClickListener) :
    BottomSheetDialogFragment(),
    View.OnClickListener {

    lateinit var tvDone: TextView
    val mTag = "커스텀 다이얼로그"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val view: View = inflater.inflate(R.layout.dialog_input, container, false)

        // 텍스트뷰 세팅
        tvDone = view.findViewById<TextView>(R.id.tv_done)!!
        tvDone.setOnClickListener(this)
        tvDone.text = doneText

        return view
    }

    interface OnClickListener {
        fun onClick(dialog: CustomDialog)
    }

    override fun onClick(p0: View?) {
        listener.onClick(this)
    }
}