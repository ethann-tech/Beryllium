pluginManagement {
    repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        google()
        jcenter()
        mavenCentral()
    }
}

rootProject.name = "Beryllium"
include(":app")
include(":library:framework")
include(":module:primary")
include(":library:common")
