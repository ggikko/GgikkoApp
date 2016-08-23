GGIKKO SAMPLE APP (JIHONG PARK)
===================================

A sample that shows how you save some data by using OPEN API that based on various libraries and use MVP pattern (dagger2, rx, retrofit2, mockito). That shows how to write basic test code (UI & Network service). 

Screenshots
------------
<img src="https://github.com/ggikko/GgikkoApp/blob/master/sample1.png" width="270" height="480" />
<img src="https://github.com/ggikko/GgikkoApp/blob/master/sample2.png" width="270" height="480" />

Code style
------------
This repository's code style follow google code styleguide .
ref : https://github.com/google/styleguide

Pre-requisites
--------------

- Android SDK 24
- Android Build Tools v24.0.0
- Android Support Repository

Project architcture
------------
- MVP
- minSdkVersion 16
- targetSdkVersion 24
- JavaVersion.VERSION 1.8(retro lambda)
    * java/.../ggikkoapp/di : App, Activity, Fragment depedency injection and some component & module customized
    * java/.../network : manage service & model
    * java/.../ui : each activities, fragments, adapter, presenter, interface etc..
    * java/.../util : animator, network config, database managing object, logging
    * .../androidTest : each layer(view, presenter, model.. using mock) test, UI testing(using Espresso)
    * .../test : DI test, service test(using mockito)

Libraries
------------

	// Dagger - http://google.github.io/dagger/
    apt "com.google.dagger:dagger-compiler:${dagger2Version}"
    compile "com.google.dagger:dagger:${dagger2Version}"
    provided "org.glassfish:javax.annotation:${glassfishVersion}"

    // Lombok - https://github.com/rzwitserloot/lombok
    provided "org.projectlombok:lombok:${lombokVersion}"
    apt "org.projectlombok:lombok:${lombokVersion}"

    // view binding - https://github.com/JakeWharton/butterknife
    apt "com.jakewharton:butterknife-compiler:${butterKnifeVersion}"
    compile "com.jakewharton:butterknife:${butterKnifeVersion}"

    //android
    compile "com.android.support:appcompat-v7:${supportLibraryVersion}"
    compile "com.android.support:design:${supportLibraryVersion}"
    compile "com.android.support:support-v4:${supportLibraryVersion}"
    compile "com.android.support:support-annotations:${supportLibraryVersion}"
    compile "com.android.support:recyclerview-v7:${supportLibraryVersion}"
    compile "com.android.support:cardview-v7:${supportLibraryVersion}"
    compile "com.android.support:design:${supportLibraryVersion}"

    //retrofit - https://github.com/square/retrofit
    compile "com.squareup.retrofit2:retrofit:${retrofit2Version}"
    compile "com.squareup.retrofit2:converter-gson:${retrofit2Version}"
    compile "com.squareup.retrofit2:adapter-rxjava:${retrofit2Version}"

    //glide - https://github.com/bumptech/glide
    compile "com.github.bumptech.glide:glide:${glideVersion}"

    //logging  - https://github.com/square/okhttp/tree/master/okhttp/src/main/java/okhttp3
    compile "com.squareup.okhttp3:okhttp:${okhttpVersion}"
    compile "com.squareup.okhttp3:logging-interceptor:${okhttpVersion}"

    //rx
    compile "io.reactivex:rxandroid:${rxAndroidVersion}"

    //font
    compile "com.tsengvn:Typekit:${typekitVersion}"

    //swipelayout
    compile "com.daimajia.swipelayout:library:${swipelayoutVersion}"

    //test - https://github.com/junit-team/junit4
    //     - https://github.com/mockito/mockito - for junit
    //     - https://github.com/crittercism/dexmaker - for android test
    //     - https://github.com/aNNiMON/Lightweight-Stream-API
    //     - https://github.com/square/assertj-android

    // JUnit4 Rules
    testApt "com.google.dagger:dagger-compiler:${dagger2Version}"
    testCompile "com.annimon:stream:${streamapiVersion}"
    testCompile "com.squareup.assertj:assertj-android:${assertjVersion}"
    testCompile 'junit:junit:4.12'

    // Android JUnit Runner
    androidTestCompile 'com.android.support:support-annotations:24.0.0'
    androidTestCompile "com.android.support.test:runner:${testRunnerVersion}"
    androidTestCompile "com.android.support.test:rules:${testRunnerVersion}"
    androidTestCompile "com.android.support.test.espresso:espresso-core:${espressoVersion}"
    androidTestCompile "com.crittercism.dexmaker:dexmaker:${dexmakerVersion}"
    androidTestCompile "com.crittercism.dexmaker:dexmaker-dx:${dexmakerVersion}"
    androidTestCompile "com.crittercism.dexmaker:dexmaker-mockito:${dexmakerVersion}"
    androidTestCompile "org.hamcrest:hamcrest-library:${hamcrestVersion}"
 
    ext {
	    supportLibraryVersion = '24.1.1'
	    retrofit2Version = '2.0.2'
	    butterKnifeVersion = '8.2.1'
	    dagger2Version = '2.5'
	    glassfishVersion = '10.0-b28'
	    lombokVersion = "1.12.6"
	    glideVersion = '3.7.0'
	    okhttpVersion = '3.3.1'
	    typekitVersion = '1.0.0'
	    testRunnerVersion = "0.5"
	    espressoVersion = "2.2.2"
	    rxAndroidVersion = '1.1.0'
	    dexmakerVersion = "1.4"
	    hamcrestVersion = "1.3"
	    assertjVersion = "1.1.1"
	    streamapiVersion = "1.1.0"
	    swipelayoutVersion = "1.2.0@aar"
	}


