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

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.util.SizeUtils;


public class ZxingDialog extends BaseDialog<ZxingDialog> {

    private String mContent;
    private View mView;
     TextView mMineSettingDialogVersion;
    ImageView mZxImg;
    ImageView mMineSettingDialogClose;
    ImageView mBalloon;
    private String title;
    public ZxingDialog(@NonNull Context context,String content,String title) {
        super(context);
        widthScale(0.75f);
        heightScale(0.8f);
        showAnim(new BounceEnter());
        this.mContent = content;
        this.title= title;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView() {
        mView = View.inflate(mContext, R.layout.dialog_zxing, null);
        return mView;
    }

    @Override
    public void setUiBeforShow() {
        setCanceledOnTouchOutside(false);
        setCancelable(false);
         this.mMineSettingDialogVersion = (TextView) mView.findViewById(R.id.mine_setting_dialog_version);
        this.mZxImg = (ImageView) mView.findViewById(R.id.zx_img);
        this.mMineSettingDialogClose = (ImageView) mView.findViewById(R.id.mine_setting_dialog_close);
        this.mBalloon = (ImageView) mView.findViewById(R.id.balloon);
        this.mMineSettingDialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        this.mZxImg.setImageBitmap(CodeUtils.createImage(mContent, SizeUtils.dp2px(180), SizeUtils.dp2px(180), null));
        this.mMineSettingDialogVersion.setText(title);
    }


    @Override
    public void show() {
        super.show();
    }



}
