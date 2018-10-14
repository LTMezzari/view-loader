package lucas.torres.view_loader.generic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import lucas.torres.view_loader.R;
import lucas.torres.viewloader.loader.ViewLoader;


/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public abstract class BaseActivity extends AppCompatActivity {

	private View view;

	private TextView tvTitle;

	@Override
	protected void onStart() {
		super.onStart();

		view = getWindow().getDecorView().getRootView();

		new ViewLoader(R.id.class, view, this)
				.addSuperToLoad(BaseActivity.class)
				.findViews();

		onInit();
	}

	protected abstract void onInit ();

	protected View getView() {
		return view;
	}

	protected void setToolbarTitle(String title){
		tvTitle.setText(title);
	}
}
