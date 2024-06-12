// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.jetbrainsCompose) apply false


    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"


}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        //classpath ("com.android.tools.build:gradle:7.2.2")
        // versão anterior 2.44
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        //classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
        classpath ("com.android.tools.build:gradle:8.0.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")

    }
}

