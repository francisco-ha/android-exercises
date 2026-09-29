plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.poo_1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.poo_1"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Reemplaza "androidx.test.runner.AndroidJUnitRunner" por tu runner personalizado ya que no
        // podemos editar la clase primaria, es como crear un archivo App que son los que se inician
        // al comenzar nuestra app:
        testInstrumentationRunner = "com.example.poo_1.CustomTestRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }
}

dependencies {


    testImplementation("junit:junit:4.12")
    // implementation -> Son librerías para el código principal de la aplicación.
    // testImplementation -> Son librerías para pruebas unitarias locales (se ejecutan en la computadora).
    // androidTestImplementation -> Se usan principalmente para probar la Interfaz de Usuario (UI)


    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("androidx.arch.core:core-testing:2.1.0") // lo utilizamos para testear los live data
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48")// Hilt para Pruebas Instrumentadas (androidTest)
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")// Hilt para Pruebas Instrumentadas (androidTest)



    /*Mokito para pruebas unitarias*/
    testImplementation("io.mockk:mockk:1.12.2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.leanback:leanback:1.0.0")

    //libreria para splashscreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    /*retrofit*/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    /*GLIDE Y PICASSO PARA IMAGENES */
    //Sigue estando activamente mantenida
    implementation("com.github.bumptech.glide:glide:4.11.0")

    //PICASSO Picasso: Está formalmente discontinuada (deprecated)
    implementation("com.squareup.picasso:picasso:2.8")

    /*Room*/
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    /*DataStore*/
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    /*librerias para scaneo de codigos qr, de barras, etc,... */
    implementation("com.journeyapps:zxing-android-embedded:4.1.0")

    /*Implementacion de ViewModel*/
    //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    /*Implementacion de LiveData*/
    //implementation("androidx.lifecycle:lifedata-viewmodel-ktx:2.3.1")

    //para trabajar con corrutinas y flows de forma sencilla
    //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

    /*Fragment*/
    implementation("androidx.fragment:fragment-ktx:1.3.2")
    /*Activity*/
    implementation("androidx.activity:activity-ktx:1.6.1")

    /*Geocalizaion*/
    implementation("com.google.android.gms:play-services-location:21.2.0")

    /*Desliza para actulizar*/
    //implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    /* NavComponent */
    val navVersion = "2.7.0"
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")

    var hilt_version = "2.48"

    //implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    implementation("com.google.dagger:hilt-android:${hilt_version}")
    //kapt es la dependencia que autogenera codigo para dagger hilt
    //tambien se agegan en el grade del proyecto: id("com.google.dagger.hilt.android") version "2.48" apply false
    //tambien en este archivo pero hasta arriba id("kotlin-kapt") id("com.google.dagger.hilt.android")
    kapt("com.google.dagger:hilt-compiler:${hilt_version}")




}