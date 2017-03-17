package mrh.framtest20160801.ui.pull;

import android.os.Bundle;

import java.net.Socket;

import mrh.framtest20160801.R;
import mrh.framtest20160801.ui.base.BaseAty;

/**
 * @author Yiku
 * @date 2016/9/23 10:23
 */
public class FastPullAty extends BaseAty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar.setTitle("极光");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = new Socket();
            }
        }).start();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.aty_fastpull;
    }

    @Override
    protected void initialized() {




    }

    @Override
    protected void requestData() {

    }
}
