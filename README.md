Easy Animated Vector Drawable
============

Demo
----

![alt text](https://github.com/DenisMondon/EasyAnimatedVectorDrawable/blob/master/images/demo.gif "Demo gif")


Download
--------

```groovy
dependencies {
    implementation 'com.blunderer:easy-animated-vector-drawable:1.0.5'
}
```


How to use it
-------------

Simply call setImageType() on any ImageView and give it the type of the drawable:
```kotlin
    ImageView imageView = (ImageView) findViewById(R.id.image_view);

    // Kotlin
    imageView.setImageType(EasyAnimatedVectorDrawable.Type.PLAY)
    // Java
    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY);
```

If you want to change the color of the drawable, just give also the color:
```kotlin
    // Kotlin
    imageView.setImageType(EasyAnimatedVectorDrawable.Type.PLAY, Color.RED)
    // Java
    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY, Color.RED);
```
or
```kotlin
    // Kotlin
    imageView.setImageType(EasyAnimatedVectorDrawable.Type.PLAY, ContextCompat.getColor(context, R.color.red))
    // Java
    EasyAnimatedVectorDrawable.setImageType(imageView, EasyAnimatedVectorDrawable.Type.PLAY, ContextCompat.getColor(context, R.color.red));
```

Here is the list of all currently supported types:
- PLAY
- PAUSE
- STOP

- LEFT_ARROW
- UP_ARROW
- RIGHT_ARROW
- DOWN_ARROW

CUSTOMIZATION
-------------

*The included drawable types are not useful for your app?*  
You can use **your own animated vector drawables** like this:  
**COMING SOON**

License
-------

    Copyright 2019 Denis Mondon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
