buildscript {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
  dependencies {
    classpath(libs.android)
    classpath(libs.kotlin)
  }
}


tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}