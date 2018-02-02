package cn.braing.pay.lib.page;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.braing.pay.lib.bean.ServerLogEvent;

/**
 * <pre>
 * author：张俊
 * date： 2018/2/1
 * desc：
 * <pre>
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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



    public abstract void setServerData(ServerLogEvent serverData);


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(ServerLogEvent event) {
        setServerData(event);
    }
}
