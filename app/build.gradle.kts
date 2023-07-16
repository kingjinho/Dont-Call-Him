plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
}

android {
    namespace 'com.kingjinho.dontcallhim'
    compileSdk 33

    defaultConfig {
        applicationId "com.kingjinho.dontcallhim"
        minSdk 29
        targetSdk 33
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary true
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    packagingOptions {
        resources {
            excludes += '/META-INF/{AL2.0,LGPL2.1}'
        }
    }
    buildFeatures {
        viewBinding true
    }

    testOptions.unitTests {
        includeAndroidResources true
        returnDefaultValues true
    }
}

dependencies {

    implementation "androidx.appcompat:appcompat:1.6.1"
    implementation "androidx.core:core-ktx:1.10.1"
    implementation "com.google.android.material:material:1.9.0"

    // Testing dependencies
    androidTestImplementation "androidx.arch.core:core-testing:2.2.0"
    testImplementation "androidx.arch.core:core-testing:2.2.0"
    androidTestImplementation "androidx.test.espresso:espresso-contrib:3.5.1"
    androidTestImplementation "androidx.test.espresso:espresso-core:3.5.1"
    implementation "androidx.test.espresso:espresso-idling-resource:3.5.1"

    // To use the androidx.test.core APIs
    androidTestImplementation "androidx.test:core:1.5.0"
    testImplementation "androidx.test:core:1.5.0"
    // Kotlin extensions for androidx.test.core
    androidTestImplementation "androidx.test:core-ktx:1.5.0"
    testImplementation "androidx.test:core-ktx:1.5.0"

    testImplementation "org.robolectric:robolectric:4.9"

    // To use the JUnit Extension APIs
    androidTestImplementation "androidx.test.ext:junit:1.1.5"
    // Kotlin extensions for androidx.test.ext.junit
    testImplementation "androidx.test.ext:junit-ktx:1.1.5"

    // To use the Truth Extension APIs
    androidTestImplementation "androidx.test.ext:truth:1.5.0"
    testImplementation "com.google.truth:truth:1.1.4"
    androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2"

    // To use the androidx.test.runner APIs
    androidTestImplementation "androidx.test:runner:1.5.2"

    // To use android test orchestrator
    androidTestUtil "androidx.test:orchestrator:1.4.2"

    debugImplementation "androidx.fragment:fragment-testing:1.6.0"

    implementation "androidx.navigation:navigation-fragment-ktx:2.6.0"
    implementation "androidx.navigation:navigation-ui-ktx:2.6.0"

    androidTestImplementation "androidx.navigation:navigation-testing:2.6.0"

    implementation 'com.google.dagger:dagger:2.46.1'
    kapt 'com.google.dagger:dagger-compiler:2.46.1'

    implementation "androidx.room:room-runtime:2.5.2"
    implementation "androidx.room:room-ktx:2.5.2"
    annotationProcessor "androidx.room:room-compiler:2.5.2"
    kapt "androidx.room:room-compiler:2.5.2"
    testImplementation "androidx.room:room-testing:2.5.2"
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
    kotlinOptions {
        // Treat all Kotlin warnings as errors
        allWarningsAsErrors = true

        freeCompilerArgs += '-opt-in=kotlin.RequiresOptIn'

        // Set JVM target to 1.8
        jvmTarget = "1.8"
    }
}