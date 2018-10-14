package lucas.torres.viewloader.loader;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public final class ViewLoader {

	private static final String TAG = "ViewLoader";
	public static boolean shouldShowLog;
	private Class<?> idClass;
	private View view;
	private Object object;
	private ArrayList<Class<?>> sClasses;

	/**
	 * Constructor for a View Loader instance.
	 * If it is not needed to load super class components, the constructor and findViews is enough.
	 *
	 * @param idClass The R.id class of the module.
	 * @param view View from where you want to load the components.
	 * @param object Object in witch the components will be loaded.
	 */
	public ViewLoader (Class<?> idClass, View view, Object object) {
		this.idClass = idClass;
		this.view = view;
		this.object = object;
		this.sClasses = new ArrayList<>();
	}

	/**
	 * Add a super class to load the components.
	 * It is needed to get the inherited fields.
	 *
	 * @param sClass The super class of the object.
	 * @return This view loader instance.
	 */
	public ViewLoader addSuperToLoad (Class<?> sClass) {
		sClasses.add(sClass);
		return this;
	}

	/**
	 * Add all super classes to load the components.
	 * It is needed to get the inherited fields.
	 *
	 * @param sClasses The super classes of the object.
	 * @return This view loader instance.
	 */
	public ViewLoader addSupersListToLoad (List<Class<?>> sClasses){
		this.sClasses.addAll(sClasses);
		return this;
	}

	/**
	 * Add all super classes to load the components.
	 * It is needed to get the inherited fields.
	 *
	 * @param sClasses The super classes of the object.
	 * @return This view loader instance.
	 */
	public ViewLoader addSupersListToLoad (Class<?>... sClasses){
		this.sClasses.addAll(Arrays.asList(sClasses));
		return this;
	}

	/**
	 * Find all views from the classes and load it in the object.
	 *
	 * If the variable shouldShowLog is false, no Log will be shown in the log cat.
	 */
	public void findViews () {
		loadComponents(idClass, view, object, object.getClass());

		if(!sClasses.isEmpty()) {
			for (Class<?> c : sClasses) {
				loadComponents (idClass, view, object, c);
			}
		}
	}

	private static void loadComponents (Class<?> idClass, View view, Object object, Class<?> oClass) {
		for (Field field : oClass.getDeclaredFields()) {
			if (!View.class.isAssignableFrom(field.getType())) {
				if(shouldShowLog) {
					Log.d(TAG, field.getName() + " is not a view. ("+oClass.getSimpleName()+")");
				}
			}

			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);

			try {
				Field id = idClass.getDeclaredField(field.getName());
				field.set(object, view.findViewById(id.getInt(idClass)));
			} catch (NoSuchFieldException | IllegalAccessException e) {
				if(shouldShowLog) {
					Log.d(TAG, "There is no field "+field.getName()+" in ids. ("+oClass.getSimpleName()+")");
				}
			}

			field.setAccessible(isAccessible);
		}
	}
}
