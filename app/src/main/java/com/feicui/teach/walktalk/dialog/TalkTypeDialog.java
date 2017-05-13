package com.feicui.teach.walktalk.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feicui.teach.walktalk.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/12 0012.
 *  对讲类型弹框
 */

public class TalkTypeDialog extends BaseDialog {

    @BindView(R.id.rb_dialog_talk_type_first)
    RadioButton mRadioFirst;

    @BindView(R.id.rg__dialog_talk_type)
    RadioGroup mRadioGroup;


    public TalkTypeDialog(Context context, int layoutId) {
        super(context, layoutId);
        init();
    }

    public void init() {
        mRadioFirst.setChecked(true);
    }


    @OnClick({R.id.btn_talk_type_cancel, R.id.btn_talk_type_sure})
    public void handleEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_talk_type_cancel://取消
                cancel();
                break;
            case R.id.btn_talk_type_sure://确定
                if (mOnTalkTypeDialogFinishResultListener != null)
                    for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
                        if (mRadioGroup.getChildAt(i) instanceof RadioButton && ((RadioButton) mRadioGroup.getChildAt(i)).isChecked()) {
                            mOnTalkTypeDialogFinishResultListener.onTalkTypeResult(i);
                            break;
                        }
                    }
                cancel();
                break;
        }

    }


    OnTalkTypeDialogFinishResultListener mOnTalkTypeDialogFinishResultListener;

    public void setOnTalkTypeDialogFinishResultListener(OnTalkTypeDialogFinishResultListener mOnTalkTypeDialogFinishResultListener) {
        this.mOnTalkTypeDialogFinishResultListener = mOnTalkTypeDialogFinishResultListener;
    }

    /**
     * 回调接口传出选择结果
     */
    public interface OnTalkTypeDialogFinishResultListener{
       void onTalkTypeResult(int index);
    }



}