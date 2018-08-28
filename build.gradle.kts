plugins {
    kotlin("jvm") version "1.2.51"
}


dependencies {
    compile(kotlin("stdlib"))
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "0.22.5")
    compile(group = "org.projectlombok", name = "lombok", version = "1.16.20")
}

repositories {
    jcenter()
    mavenLocal()
}