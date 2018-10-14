package lucas.torres.view_loader.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import lucas.torres.view_loader.R;
import lucas.torres.view_loader.generic.BaseActivity;


/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public class FragmentTestActivity extends BaseActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_test);
	}

	@Override
	protected void onInit() {
		setToolbarTitle("Fragment Test");
	}
}
