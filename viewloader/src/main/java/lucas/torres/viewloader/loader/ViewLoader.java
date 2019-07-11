package lucas.torres.viewloader.loader;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Lucas T. Mezzari
 * @since 14/10/2018
 */
public final class ViewLoader {

	//Declares a helper idClass
	private Class<?> idClass;
	//Declares a helper view
	private View view;
	//Declares a helper object
	private Object object;
	//Declares a helper list of super classes
	private ArrayList<Class<?>> sClasses;

	/**
	 * Private constructor that initializes the array of supers.
	 */
	private ViewLoader() {
		//Initialize the list of super classes
		this.sClasses = new ArrayList<>();
	}

	/**
	 * Initialize the ViewLoader instance and set it's idClass.
	 * 
	 * @param idClass The R.id class that contains the id's of the project.
	 * @return A new ViewLoader Instance.
	 */
	public static ViewLoader with(@NonNull Class<?> idClass) {
		//Initializes a new ViewLoader instance
		ViewLoader instance = new ViewLoader();
		//Set it's id class
		instance.idClass = idClass;
		//Return the instance
		return instance;
	}

	/**
	 * Set the view of the ViewLoader instance.
	 *
	 * @param view The view from were the components will be loaded from.
	 * @return A ViewLoader instance.
	 */
	public ViewLoader from(@NonNull View view) {
		//Set the view
		this.view = view;
		//Return itself
		return this;
	}

	/**
	 * Set the object that will receive the views.
	 *
	 * @param object The object that will receive the loaded views.
	 * @return A ViewHolder instance.
	 */
	public ViewLoader into(@NonNull Object object) {
		//Set the object
		this.object = object;
		//Return itself
		return this;
	}

	/**
	 * Add a super class to be loaded from.
	 *
	 * @param sClass The super class of the object.
	 * @return A ViewHolder instance.
	 */
	public ViewLoader also(@NonNull Class<?> sClass) {
		//Add a super class to load from
		this.sClasses.add(sClass);
		//Return itself
		return this;
	}

	/**
	 * Load the views without log.
	 */
	public void load() {
		//Load the views into the object without log
		findViews(false);
	}

	/**
	 * Load the views with log.
	 */
	public void loadWithLog() {
		findViews(true);
	}

	/**
	 * Find all views from the classes and load it in the object.
	 *
	 * If the variable shouldShowLog is false, no Log will be shown in the log cat.
	 *
	 * @param shouldShowLog A boolean variable that defines if a log should be shown
	 */
	private void findViews (boolean shouldShowLog) {
		//Validate if the instance is valid
		validate();
		//Load the components from the object class
		loadComponents(object.getClass(), shouldShowLog);

		//Check if there is a super to be loaded
		if(!sClasses.isEmpty()) {
			//Loops through the supers
			for (Class<?> c : sClasses) {
				//Load the components from the super class
				loadComponents (c, shouldShowLog);
			}
		}
	}

	/**
	 * Private method that load the components from the view into the declared fields of the oCLass.
	 *
	 * @param oClass The object class that has the declared fields.
	 * @param shouldShowLog A boolean that validates if a log should be shown.
	 */
	private void loadComponents (Class<?> oClass, boolean shouldShowLog) {
		//Loops through the declared fields of the oCLass
		for (Field field : oClass.getDeclaredFields()) {
			//Check if the field is a View
			if (!View.class.isAssignableFrom(field.getType())) {
				//Log if it should
				log(field.getName() + " is not a view. ("+oClass.getSimpleName()+")", shouldShowLog);
				continue;
			}

			//Get the field accessibility
			boolean isAccessible = field.isAccessible();
			//Set the field accessibility to true
			field.setAccessible(true);

			try {
				//Get a id field from the idClass with the field name
				Field id = idClass.getDeclaredField(field.getName());
				//Set the object field value from the findViewById of the view
				field.set(object, view.findViewById(id.getInt(idClass)));
				//Log if it should
				log("Loaded "+field.getName()+" into "+object.getClass().getSimpleName()+". ("+oClass.getSimpleName()+")", shouldShowLog);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				//Log if it should
				log("There is no field "+field.getName()+" in ids. ("+oClass.getSimpleName()+")", shouldShowLog);
			}

			//Set the field accessibility back to normal
			field.setAccessible(isAccessible);
		}
	}

	/**
	 * Private helper method that logs messages.
	 *
	 * @param message The message to be logged.
	 * @param shouldShowLog If the log should be shown or not.
	 */
	private static void log(String message, boolean shouldShowLog) {
		if (!shouldShowLog) return;
		Log.d(ViewLoader.class.getSimpleName(), message);
	}

	/**
	 * Private helper method that throw IllegalArgumentException
	 */
	private void validate() {
		if (idClass == null) {
			//If idClass is null, throw a IllegalArgumentException
			throw new IllegalArgumentException("Missing a class of id's to link view and object.");
		} else if (view == null) {
			//If view is null, throw a IllegalArgumentException
			throw new IllegalArgumentException("Missing a view to load from.");
		} else if (object == null) {
			//If object is null, throw a IllegalArgumentException
			throw new IllegalArgumentException("Missing a object to load the views into.");
		}
	}
}
