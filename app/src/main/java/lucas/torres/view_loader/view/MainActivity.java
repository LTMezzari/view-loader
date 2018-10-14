package lucas.torres.view_loader.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import lucas.torres.view_loader.R;
import lucas.torres.view_loader.generic.BaseActivity;

public class MainActivity extends BaseActivity {

	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onInit() {
		setToolbarTitle("Main");

		btnNext.setOnClickListener(view -> startActivity(
				new Intent(this, FragmentTestActivity.class)));
	}
}
