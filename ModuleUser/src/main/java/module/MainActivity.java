package module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.qihuan.commonmodule.base.BaseActivity;
import com.qihuan.usermodule.R;
import com.qihuan.usermodule.view.MeFragment;

/**
 * MainActivity
 *
 * @author Qi
 */
public class MainActivity extends BaseActivity {

    private Fragment content = MeFragment.newInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fl_content, content)
            .commit();
    }
}
