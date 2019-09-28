# View Loader
This is a library that loads all view components into a class. For it to work, the names of the ids and variables must be equal.

The View Loader can slow down the application, but in many cases, it will not be noticeable.

```java
ViewLoader.get(R.id.class)
	.into(MainActivity.this)
	.from(BaseActivity.class)//Optional and reusable
	.find(view);//.findWithLog(view) to show the ViewLoader logs
```

[![](https://jitpack.io/v/LTMezzari/view-loader.svg)](https://jitpack.io/#LTMezzari/view-loader)

Get the library [here](https://jitpack.io/#LTMezzari/view-loader)
