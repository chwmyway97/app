package com.chocho.myapplication

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

//프래그먼트 띄우기 위한
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frgment_button_sheet, container, false)
    }
    companion object {
        const val TAG = "BottomSheetFragment"
    }
    /** onCreateDialog
     *  목적 : 나만의 커스텀 다이얼로그를 만들기 위함.
     *  How? : dialog.setOnShowListener{}의 인자값인 'DialogInterface'를 크기 조절하고 이벤트를 달 수 있음(닫힘, 취소 등)
     * */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            // 다이얼로그 크기 설정 (인자값 : DialogInterface)
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    /** setupRatio()
     * bottomSheet
     *      - 전달받은 DialogInterface를 통해 View를 참조하도록 한다.
     *      - 이때 id 값에는 내가 만든 View가 들어가는게 아님을 주의하자.
     * layoutParams
     *      - View를 감싸고 있는 Parents에게 어떻게 레이아웃 할 것인지 설정하는데 사용함.
     * behavior
     *      - 생성한 View를 통해 BottomSheetBehavior를 생성한다.
     *      - 역할 : BottomSheetBehavior로 상태값을 '확장형'으로 설정해준다.
     * */
    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {

        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from<View>(bottomSheet)
        val layoutParams = bottomSheet!!.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 85 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}