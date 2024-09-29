package wangjun.dependencies

object Versions {
    const val compileSdk = 33
    const val buildTools = "29.0.3"
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.8.21"
    const val coroutines = "1.3.9"
    const val coroutines_android = "1.3.9"
    const val androidxArch = "2.0.0"
    const val mockito = "2.23.0"

    const val appcompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val retrofit = "2.9.0"
    const val retrofit_converter_gson = "2.9.0"
    const val okhttp_logging_interceptor = "4.10.0"
    const val swipeRefreshLayout = "1.1.0"
    const val material = "1.2.0-beta01"
    const val persistentCookieJar = "v1.0.1"
    const val material_dialogs = "3.3.0"
    const val livedata_ktx = "2.2.0"
    const val viewPager2 = "1.0.0"
    const val core_ktx = "1.3.0"
    const val navigation = "2.5.0"
    const val recyclerView = "1.1.0"
    const val viewmodel_ktx = "2.2.0"
    const val lifecycle_extension = "2.2.0"
    const val coil_compose = "2.1.0"
}

object Deps {

    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    const val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extension}"

    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieJar}"

    // third
    const val material_dialogs_core = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"
    const val material_dialogs_input = "com.afollestad.material-dialogs:input:${Versions.material_dialogs}"
    const val coil_compose = "io.coil-kt:coil-compose:${Versions.coil_compose}"

}