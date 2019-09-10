plugins {
    kotlin("jvm") version "1.3.30"
    id("org.jetbrains.kotlin.kapt") version "1.3.30"
}


kapt {
    useBuildCache = true
}

dependencies {
    compile(kotlin("stdlib"))
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "1.2.1")
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.16.20")

    // https://mvnrepository.com/artifact/com.github.binarywang/weixin-java-mp
    compile(group = "com.github.binarywang", name = "weixin-java-mp", version = "3.3.0")
// https://mvnrepository.com/artifact/org.jetbrains.exposed/exposed
    // https://mvnrepository.com/artifact/org.jetbrains.exposed/exposed
    compile("org.jetbrains.exposed", "exposed", "0.13.6")
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
    compile("mysql:mysql-connector-java:5.1.47")
    compile("org.apache.logging.log4j", "log4j-api", "2.11.2")
    compile("org.apache.logging.log4j", "log4j-core", "2.11.2")
    compile("org.apache.logging.log4j", "log4j-slf4j-impl", "2.11.2")

}

repositories {
    maven("https://dl.bintray.com/kotlin/exposed/")
    mavenLocal()
    jcenter()
}
