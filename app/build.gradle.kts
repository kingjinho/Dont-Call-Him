import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
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

        testInstrumentationRunner = "com.kingjinho.dontcallhim.runner.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
            correctErrorTypes = true
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
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
    implementation(libs.coroutine)

    implementation(platform(libs.kotlin.bom))

    implementation(libs.compose.runtime)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)


    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.junit4)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // To use android test orchestrator
    androidTestUtil(libs.orchestrator)

    debugImplementation(libs.fragment.testing)

    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compipler)
    kaptAndroidTest(libs.hilt.compipler)
    kaptTest(libs.hilt.compipler)

    implementation(libs.espresso.idling.resource)

    androidTestImplementation(libs.arch.core.testing)
    androidTestImplementation(libs.espresso.contrib)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.coroutine.testing)
    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.room.testing)


    testImplementation(libs.arch.core.testing)
    testImplementation(libs.test.core)
    testImplementation(libs.test.core.ktx)
    testImplementation(libs.robolectric)
    testImplementation(libs.junit.ktx)
    testImplementation(libs.google.truth)
    testImplementation(libs.coroutine.testing)
    testImplementation(libs.hilt.testing)
    testImplementation(libs.room.testing)
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