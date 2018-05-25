plugins {
    kotlin("jvm") version "1.2.40"
}


dependencies {
    compile(kotlin("stdlib"))
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "0.22.5")
}

repositories {
    jcenter()
}