import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

subprojects {
  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
  }
}
