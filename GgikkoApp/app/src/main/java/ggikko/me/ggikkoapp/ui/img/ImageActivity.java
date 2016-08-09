package ggikko.me.ggikkoapp.ui.img;

import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;

public class ImageActivity extends InjectionActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_image;
    }

    @Override
    protected void onCreate() {
        unbinder = ButterKnife.bind(this);
    }

}
