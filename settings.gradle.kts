pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "edison_android_exercise"

include(":app")
include(":core:data")
include(":core:network")
include(":core:domain")
include(":core:datastore")
include(":core:testing")
include(":feature:fact")
