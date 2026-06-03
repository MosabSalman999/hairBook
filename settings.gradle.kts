pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "HairBook"
include(":app")
include(":core:database")
include(":core:ui")
include(":feature:auth")
include(":feature:browse")
include(":feature:detail")
include(":feature:finder")
include(":feature:favourites")
include(":feature:admin")
include(":feature:booking")
include(":feature:profile")
