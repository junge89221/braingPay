package cn.braing.pay.lib.api.subscriber;


import android.content.Context;
import android.content.DialogInterface;

import cn.braing.pay.lib.view.LoadingDialog;
import io.reactivex.disposables.Disposable;


public abstract class SimpleSubscriber<T> extends BaseSubscriber<T> {

    private boolean isShowLoadingDialog = false;
    private LoadingDialog mLoadingDialog;
    private boolean needLogin;
    private boolean needBindingDevice;
    private boolean needBindingHouse;

    public SimpleSubscriber() {
    }

    public SimpleSubscriber(boolean needLogin) {
        this.needLogin = needLogin;
    }


    /**
     * @param isShowLoadingDialog 是否显示加载dialog
     */
    public SimpleSubscriber(Context context, boolean isShowLoadingDialog) {
        this.isShowLoadingDialog = isShowLoadingDialog;
        if (isShowLoadingDialog)
            mLoadingDialog = new LoadingDialog(context);
    }


    public SimpleSubscriber(Context context, boolean isShowLoadingDialog, boolean needLogin) {
        this.needLogin = needLogin;
        this.isShowLoadingDialog = isShowLoadingDialog;
        if (isShowLoadingDialog)
            mLoadingDialog = new LoadingDialog(context);
    }

    public SimpleSubscriber(Context context, boolean isShowLoadingDialog, boolean needLogin, boolean needBindingHouse, boolean needBindingDevice) {
        this.needLogin = needLogin;
        this.needBindingDevice = needBindingDevice;
        this.needBindingHouse = needBindingHouse;
        this.isShowLoadingDialog = isShowLoadingDialog;
        if (isShowLoadingDialog)
            mLoadingDialog = new LoadingDialog(context);
    }


    public SimpleSubscriber(Context context, boolean isShowLoadingDialog, String text) {
        this.isShowLoadingDialog = isShowLoadingDialog;
        if (isShowLoadingDialog) {
            mLoadingDialog = new LoadingDialog(context);
            mLoadingDialog.setLoadingText(text);
        }
    }

    public SimpleSubscriber(Context context, boolean isShowLoadingDialog, String text, boolean needLogin) {
        this.needLogin = needLogin;
        this.isShowLoadingDialog = isShowLoadingDialog;
        if (isShowLoadingDialog) {
            mLoadingDialog = new LoadingDialog(context);
            mLoadingDialog.setLoadingText(text);
        }
    }


    @Override
    public void onSubscribe(final Disposable d) {
        if (mLoadingDialog != null && isShowLoadingDialog) {
            mLoadingDialog.show();
            mLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    d.dispose();
                }
            });
        }
    }

    @Override
    public void onComplete() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }
}
