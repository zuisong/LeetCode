plugins {
    kotlin("jvm") version "1.3.30"
    kotlin("kapt") version "1.3.30"
}


kapt {
    useBuildCache = true
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
    compile("org.jetbrains.exposed:exposed:0.17.3")
    compileOnly("org.projectlombok:lombok:1.18.8")
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
    compile("mysql:mysql-connector-java:5.1.47")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}

repositories {
    maven("https://dl.bintray.com/kotlin/exposed/")
    mavenLocal()
    jcenter()
}
