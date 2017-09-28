Easy Animated Vector Drawable
============

Demo
----

![alt text](https://github.com/DenisMondon/EasyAnimatedVectorDrawable/blob/master/images/demo.gif "Demo gif")


Download
--------

```groovy
dependencies {
    compile 'com.blunderer:easy-animated-vector-drawable:1.0.1'
}
```


How to use it
-------------

Simply call setImageType() with your ImageView and the type of the drawable:
```java
    ImageView imageView = (ImageView) findViewById(R.id.image_view);

    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY);
```

If you want to change the color of the drawable, just do this:
```java
    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY, Color.RED);
```
or
```java
    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY, ContextCompat.getColor(context, R.color.red));
```

Here is the list of all currently supported types:
- PLAY
- PAUSE
- STOP


CUSTOMIZATION
-------------

*The included drawable types are not useful for your app?*  
You can use **your own animated vector drawables** like this:  
**COMING SOON**

License
-------

    Copyright 2017 Denis Mondon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
