package module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.qihuan.commonmodule.base.BaseActivity;
import com.qihuan.dailymodule.R;
import com.qihuan.dailymodule.view.DailyFragment;

/**
 * MainActivity
 *
 * @author Qi
 */
public class MainActivity extends BaseActivity {

    private Fragment content = DailyFragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_daily);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fl_content, content)
            .commit();
    }
}
