plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.android.gallery3d"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.gallery3d"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        renderscriptTargetApi = 21
        renderscriptSupportModeEnabled = true
    }

    signingConfigs {
        create("android") {
            storeFile = file("../platform.jks")
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
    }
    buildTypes {
        named("debug") {
            signingConfig = signingConfigs.getByName("android")
        }
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("android")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
    lintOptions(Action {
        this.disable = mutableSetOf("all")
        this.isAbortOnError=false
    })
    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }
    sourceSets {
        getByName("main") {
            jniLibs {
                srcDirs("src/main/jni/lib")
            }
        }
    }
}

dependencies {
//    implementation(project(":gallerycommon"))
    //https://repo.maven.apache.org/maven2/com/googlecode/mp4parser/isoparser/
    implementation("com.googlecode.mp4parser:isoparser:1.0-RC-2")
    //https://central.sonatype.com/artifact/com.adobe.xmp/xmpcore
    implementation("com.adobe.xmp:xmpcore:5.1.2")
    implementation("androidx.legacy:legacy-support-v13:1.0.0")

//    implementation("androidx.core:core-ktx:1.12.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.11.0")
//    implementation("androidx.activity:activity:1.8.2")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}