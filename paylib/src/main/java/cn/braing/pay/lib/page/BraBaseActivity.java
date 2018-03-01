package cn.braing.pay.lib.page;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.braing.pay.lib.R;
import cn.braing.pay.lib.util.AppManager;
import cn.braing.pay.lib.view.DrawableCenterTextView;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/1
 * desc：
 * <pre>
 */

public abstract class BraBaseActivity extends AppCompatActivity {
    private RelativeLayout mToolbar;
    protected ImageView mTbLeftView;
    private TextView mTbCenterView;
    protected DrawableCenterTextView mTbRightView;
    private View line;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        if (Build.VERSION.SDK_INT >= 21) {
            ActionBar ab = getSupportActionBar();
            if (ab != null) ab.setElevation(0);
        }
        if (getSupportActionBar() != null) initToolbar();
    }


    protected void initToolbar() {
        View view = View.inflate(this, R.layout.bratoolbar, null);
        line = view.findViewById(R.id.hor_line);
        mToolbar = (RelativeLayout) view.findViewById(R.id.toolbar);
        mTbLeftView = (ImageView) view.findViewById(R.id.toolbar_left);
        mTbCenterView = (TextView) view.findViewById(R.id.toolbar_center);
        mTbRightView = (DrawableCenterTextView) view.findViewById(R.id.toolbar_right);
        mTbCenterView.setText(getTitle());
         mTbLeftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ActionBar.LayoutParams alp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
            actionBar.setCustomView(view, alp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }
    }

    @NonNull
    protected String getEditText(EditText edit) {
        return edit.getText().toString().trim();
    }

    protected boolean verifyEditText(EditText... edits) {
        for (EditText edit : edits) {
            if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                Toast.makeText(this, edit.getHint().toString() + "不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }


    public void setTitleName(String titleName) {
        mTbCenterView.setText(titleName);
    }


    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }
}
