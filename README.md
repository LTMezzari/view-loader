# View Loader
This is a library that loads all view components of an View into an class. For it to work, the names of the ids and variables must be equals.

The View Loader can slow down the application, but in many cases, it will be not noticeable.

```java
new ViewLoader(R.id.class, view, this)
				.addSuperToLoad(BaseActivity.class)
				.findViews();
```

[![](https://jitpack.io/v/LTMezzari/view-loader.svg)](https://jitpack.io/#LTMezzari/view-loader)

Get the library [here](https://jitpack.io/#LTMezzari/view-loader)
