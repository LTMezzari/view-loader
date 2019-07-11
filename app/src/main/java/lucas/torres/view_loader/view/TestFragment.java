package lucas.torres.view_loader.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lucas.torres.view_loader.R;
import lucas.torres.viewloader.loader.ViewLoader;


/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public class TestFragment extends Fragment {

	private TextView tvTest;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		ViewLoader.with(R.id.class)
				.from(view)
				.into(this)
				.load();


		tvTest.setText("This was a test");
	}
}
