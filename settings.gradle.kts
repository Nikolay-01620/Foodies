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
include(":feature")
include(":feature:basket_feature")
include(":feature:catalog_feature")
include(":feature:details_feature")
include(":feature:search_feature")
include(":feature:splash_feature")
