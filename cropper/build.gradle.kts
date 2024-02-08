plugins {
  id("org.jetbrains.dokka")
  id("org.jetbrains.kotlin.android")
  id("com.android.library")
  id("org.jetbrains.kotlin.plugin.parcelize")
  id("com.vanniktech.maven.publish")
  id("app.cash.licensee")
  id("app.cash.paparazzi")


  id ("maven-publish")

}


licensee {
  allow("Apache-2.0")
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
}

android {
  namespace = "com.canhub.cropper"

  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
  }

  buildFeatures {
    viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}


dependencies {
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.exifinterface)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)

}

dependencies {
  testImplementation(libs.androidx.fragment.testing)
  testImplementation(libs.androidx.test.junit)
  testImplementation(libs.junit)
  testImplementation(libs.mock)
  testImplementation(libs.robolectric)
}

afterEvaluate {
  publishing {
    publications {
      // Creates a Maven publication called "release".
      create<MavenPublication>("release") {
        // Applies the component for the release build variant.
        from(components["release"])

        // Customize attributes of the publication.
        groupId = "com.github.kws124"
        artifactId = "Android-Image-Cropper"
        version = "1.0.0"
      }
      // Creates a Maven publication called "debug".
      create<MavenPublication>("debug") {
        // Applies the component for the debug build variant.
        from(components["debug"])

        groupId = "com.github.kws124"
        artifactId = "Android-Image-Cropper"
        version = "1.0.0"
      }
    }
  }
}


