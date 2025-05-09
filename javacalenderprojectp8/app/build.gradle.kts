plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.javacalenderproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.javacalenderproject"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.recyclerview)
    // Dependencies related to JUNIT - remove comments if you need to run tests:
    //("org.testng:testng:6.9.6")
    //testImplementation(libs.junit)
    //androidTestImplementation(libs.ext.junit)
    //androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // for http requests
    implementation("com.google.code.gson:gson:2.8.9") // for json parsing
    implementation("io.github.cdimascio:dotenv-java:3.0.0") // env variable package
    implementation("androidx.room:room-common:2.6.1")
    //implementation("libs.gridlayout")
    implementation("com.github.prolificinteractive:material-calendarview:1.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation ("com.google.code.gson:gson:2.8.6")


}