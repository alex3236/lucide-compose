import br.com.devsrsouza.svg2compose.IconNameTransformer
import br.com.devsrsouza.svg2compose.ParsingResult
import br.com.devsrsouza.svg2compose.Svg2Compose
import br.com.devsrsouza.svg2compose.VectorType
import java.io.File

plugins {
	alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.dev.tonholo.s2c)
}

kotlin {
    androidTarget()
    jvm("desktop")
    js(IR) {
        browser()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
            }
        }
    }
}

android {
    namespace = "moe.alex3236.compose.lucide"
    compileSdk = 35
    defaultConfig {
        minSdk = 21
    }
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.google.com")
        maven("https://jcenter.bintray.com")
    }
    dependencies {
        classpath("com.github.DevSrSouza:svg-to-compose:-SNAPSHOT")
        classpath("com.google.guava:guava:23.0")
        classpath("com.android.tools:sdk-common:27.2.0-alpha16")
        classpath("com.android.tools:common:27.2.0-alpha16")
        classpath("com.squareup:kotlinpoet:1.7.2")
        classpath("org.ogce:xpp3:1.1.6")
    }
}

tasks.register("generateCompose") {
    group = "generation"
    description = "Generate Compose icons from SVG files"
    
    val assetsDir = file("icons")
    val srcDir = file("src/commonMain/kotlin")
    
    inputs.dir(assetsDir)
    outputs.dir(srcDir)
    
    doLast {
        Svg2Compose.parse(
            applicationIconPackage = "moe.alex3236.compose.lucide",
            accessorName = "Lucide",
            outputSourceDirectory = srcDir,
            vectorsDirectory = assetsDir,
            type = VectorType.SVG,
            allAssetsPropertyName = "AllIcons",
            iconNameTransformer = object : IconNameTransformer {
                override fun invoke(iconName: String, group: String): String {
                    return iconName.split("-").joinToString("") { it.capitalize() }
                }
            }
        )
    }
}

//svgToCompose {
//    processor {
//        common {
//            optimize(false)
//            recursive()
//            icons {
//                minify()
//                noPreview()
//                @OptIn(DelicateSvg2ComposeApi::class)
//                persist()
//            }
//        }

//        val icons by creating {
//            from(layout.projectDirectory.dir("icons"))
//            destinationPackage("moe.alex3236.compose.lucide")
//        }
//    }
//}