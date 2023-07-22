import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {

    namespace = "com.kingjinho.dontcallhim"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.kingjinho.dontcallhim"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }

    testOptions.unitTests {
        isIncludeAndroidResources = true
        isReturnDefaultValues = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.material)

    // Testing dependencies
    androidTestImplementation(libs.arch.core.testing)
    testImplementation(libs.arch.core.testing)

    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.espresso.idling.resource)

    // To use the androidx.test.core APIs
    androidTestImplementation(libs.test.core)
    testImplementation(libs.test.core)
    // Kotlin extensions for androidx.test.core
    androidTestImplementation(libs.test.core.ktx)
    testImplementation(libs.test.core.ktx)

    testImplementation(libs.robolectric)

    // To use the JUnit Extension APIs
    androidTestImplementation(libs.junit)
    // Kotlin extensions for androidx.test.ext.junit
    testImplementation(libs.junit.ktx)

    // To use the Truth Extension APIs
    androidTestImplementation(libs.truth)
    testImplementation(libs.google.truth)
    androidTestImplementation(libs.coroutine.testing)
    testImplementation(libs.coroutine.testing)

    // To use the androidx.test.runner APIs
    androidTestImplementation(libs.testRunner)

    // To use android test orchestrator
    androidTestUtil(libs.orchestrator)

    debugImplementation(libs.fragment.testing)

    implementation(libs.navgitation.fragment.ktx)
    implementation(libs.navgitation.ui.ktx)
    androidTestImplementation(libs.navgitation.testing)

    //dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)
    testImplementation(libs.room.testing)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compipler)

    // Hilt for instrumentation tests
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compipler)

    // Hilt for local unit tests
    testImplementation(libs.hilt.testing)
    kaptTest(libs.hilt.compipler)
}


tasks.withType(KotlinCompile::class.java).configureEach {
    kotlinOptions {
        // Treat all Kotlin warnings as errors
        allWarningsAsErrors = false

        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"

        // Set JVM target to 1.8
        jvmTarget = "1.8"
    }
}