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

rootProject.name = "Foodies"
include(":app")
include(":core")
include(":core_ui")
include(":repository")
include(":feature_splash")
include(":feature_catalog")
include(":feature_details")
include(":feature_basket")
include(":feature_search")
