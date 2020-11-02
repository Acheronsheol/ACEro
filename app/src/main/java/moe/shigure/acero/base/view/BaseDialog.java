package moe.shigure.acero.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.LayoutRes;
import androidx.annotation.StyleRes;

public abstract class BaseDialog {

    protected Dialog mDialog;
    protected Activity mActivity;
    protected View mView;

    protected abstract boolean initEventBus();

    @LayoutRes
    protected abstract int initLayoutRes();

    @StyleRes
    protected abstract int initDialogStyleRes();

    public BaseDialog(Activity mActivity){
        this.mActivity = mActivity;
        this.mView = View.inflate(mActivity, initLayoutRes(), null);
        buildDialog();
    }

    protected void buildDialog() {
        mDialog = new Dialog(mActivity, initDialogStyleRes());
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        mDialog.getWindow().setGravity(Gravity.CENTER);
        mDialog.setContentView(mView);
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                BaseDialog.this.onDismiss();
            }
        });

//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.setCancelable(false);
    }

    public BaseDialog show() {
        try {
            if(initEventBus() && !EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().register(this);
            }
            mDialog.show();//显示对话框
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    protected void onDismiss(){
        if(initEventBus() && EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

}
