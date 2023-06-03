# FoodFlow
FoodFlow is an Android app that provide users with a variety of meals to plan their weekly meals, adding to favorites or just exploring meals.


## Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Screenshots](#screenshots)
- [Technologies and Libraries Used](#technologies-and-libraries-used)
- [dependencies](#dependencies)
- [Installation](#installation)
- [Contact](#contact)

## About the Project

FoodFlow helps users plan their weekly meals by providing them with a list of meals from diffrenet  categories, and areas. With FoodFlow, users can search for meals or filter a list of meals by category, area, or ingredient. The app also has a feature to add a certain meal to favorites, making it easy for users to access their favorite meals whenever they want.
## Features
- List meals from different areas and categories
- Plan weekly meals
- Search for meals
- Filter searched meals by category, area, or ingredient
- Add meals to favorites
- View favorites and week meals in offline mode
- Show a full detailed description about meal includes country, category, ingredients, instructions, and a tutorial video

## Screenshots



## Technologies and Libraries Used

- MVP: For implementing the Model-View-Presenter architecture pattern
- Repository Pattern: For separating data storage and access from the rest of the app
- Retrofit: For making HTTP requests and handling API responses from [www.themealdb.com](https://www.themealdb.com/api.php)
- Room: For local data storage and access
- RxJava: For reactive programming and asynchronous data flow
- Navigation Component: For implementing navigation in the app
- Firebase Authentication: For email and Google authentication
- Youtube Player: For playing videos in the app
- Facebook Shimmer Animation: For displaying loading animations
- Lottie Animation: For displaying vector animations

## dependencies

The FoodFlow app uses the following dependencies:

```groovy
dependencies {
    def room_version = "2.5.1"
    def nav_version = "2.5.3"


    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

    //Material Design
    implementation 'com.google.android.material:material:1.9.0'

    //Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    
    //Network Image
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    
    //Circular Image
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    //Navigation
    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"
    
    //Room
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    //lottie animation
    implementation "com.airbnb.android:lottie:6.0.0"

    //FireBase Authentication
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:32.0.0')
    implementation 'com.google.firebase:firebase-auth'
    
    // Google Play services library
    implementation 'com.google.android.gms:play-services-auth:20.5.0'

    //Youtube WebView Player
    implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0'

    //Facebook Shimmer Animation
    implementation 'com.facebook.shimmer:shimmer:0.5.0'

    //RXJava
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    // RxAndroid
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'

    //Retrofit Rx adapter
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.9.0'

    //Room To Rx
    implementation "androidx.room:room-rxjava2:2.5.1"
}
```
## Installation

To install and run the FoodFlow app, you can follow these steps:

1. Clone the repository to your local machine:
  
   ```
   git clone https://github.com/mahmoodawd/FoodFlow.git
   ```
2. Open the project in Android Studio.
3. Build the project using the Gradle build system.
4. Run the app on an emulator or a physical device.

That's it! The app should now be up and running on your device.

## Contact
<p align="left"> <a href="https://www.linkedin.com/in/mahmoodawd" target="_blank"><img src="https://img.shields.io/badge/linkedin-%230177B5?style=plastic&logo=linkedin&logoColor=white"/></a> <a href="mailto:mahmooodawd@gmail.com"><img src="https://img.shields.io/badge/gmail-%23FF0000?style=plastic&logo=gmail&logoColor=white"/></a> <a href="https://wa.me/+201141680631" target="_blank"><img src="https://img.shields.io/badge/whatsapp-%25FFA200?style=plastic&logo=whatsapp&logoColor=white"/></a> </p>
 
