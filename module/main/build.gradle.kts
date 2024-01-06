plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ethan.main"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(libs.androidx.multidex)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.guava)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore.preferences.rxjava3)
    implementation(libs.google.hilt.android)
    implementation(libs.legacy.support.v4)
    kapt(libs.google.hilt.android.compiler)
    implementation(platform(libs.platform.androidx.compose.bom))
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.android.ext)
    androidTestImplementation(libs.test.android.espresso)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.dsltablayout)
    implementation(libs.dsltablayout.delegateforviewpage2)
    implementation(libs.refresh.kernel)
    implementation(libs.refresh.header.classics)
    implementation(libs.refresh.footer.classics)
    implementation(libs.xbanner)
    implementation(libs.banner2)
    implementation(libs.slf4j.api)
    implementation(libs.logback.android)
    implementation(libs.toaster)
    implementation(libs.butterfly)
    implementation(libs.butterfly.compose)
    kapt(libs.butterfly.compiler)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.ethan.zincum)
    implementation(libs.ultimatebarx)
    implementation(libs.live.event.bus)
    implementation(libs.baserecyclerviewadapterhelper)
    implementation(libs.tencent.tbsdk)
    compileOnly(project(":library:framework"))
    compileOnly(project(":library:common"))
    compileOnly(project(":library:network"))
}