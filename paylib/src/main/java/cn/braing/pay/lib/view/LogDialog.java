package cn.braing.pay.lib.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.dialog.widget.base.BaseDialog;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.bean.ServerLogEvent;
import cn.braing.pay.lib.util.LogUtils;
import cn.braing.pay.lib.util.SizeUtils;


public class LogDialog extends BaseDialog<LogDialog> {

    private String mContent;
    private View mView;
     TextView resp;
     TextView req;
    ImageView mZxImg;
    ImageView mMineSettingDialogClose;
    ImageView mBalloon;
    public LogDialog(@NonNull Context context, String content) {
        super(context);
        widthScale(0.75f);
         showAnim(new BounceEnter());
        this.mContent = content;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView() {
        EventBus.getDefault().register(this);
        mView = View.inflate(mContext, R.layout.dialog_log, null);
        return mView;
    }

    @Override
    public void setUiBeforShow() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
         this.req = (TextView) mView.findViewById(R.id.reqData);
         this.resp = (TextView) mView.findViewById(R.id.respData);
        this.req.setText("请求数据："+mContent);
        mView.findViewById(R.id.mine_setting_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
     }


    @Override
    public void show() {
        super.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(ServerLogEvent event) {

        resp.setText("返回数据:"+event.data);
    }

}
