package lucas.torres.view_loader;

import android.app.Application;

import lucas.torres.viewloader.loader.ViewLoader;


/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		ViewLoader.shouldShowLog = true;
	}
}
