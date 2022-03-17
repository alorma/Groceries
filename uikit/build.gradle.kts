plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  compileSdk = 31

  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.get()
  }
}

dependencies {
  implementation(libs.bundles.androidx.compose)
}