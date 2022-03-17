plugins {
  id("com.android.application")
  id("kotlin-android")
}

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "com.alorma.groceries.business"
    minSdk = 23
    targetSdk = 31
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.get()
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.activity)
  implementation(libs.bundles.androidx.compose)

  implementation(libs.androidx.material)

  testImplementation(libs.testing.junit)

  debugImplementation(libs.testing.compose.manifest)

  androidTestImplementation(libs.testing.androidx.junit)
  androidTestImplementation(libs.testing.espresso.core)
  androidTestImplementation(libs.testing.compose.ui)
}
