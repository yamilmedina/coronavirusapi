import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("com.google.cloud.tools:appengine-gradle-plugin:2.2.0")
    }
}

plugins {
    id("org.springframework.boot") version "2.1.12.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    war
}

apply {
    plugin("com.google.cloud.tools.appengine")
    plugin("io.spring.dependency-management")
}

group = "dev.yamil"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Greenwich.SR5")
    }
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        setUrl("https://plugins.gradle.org/m2/")
    }
    maven { setUrl("https://repo.spring.io/libs-milestone") }
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-data-datastore")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    runtimeOnly("org.springframework.boot:spring-boot-starter-jetty")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jsoup:jsoup:1.11.3")
    compileOnly("javax.servlet:javax.servlet-api:3.1.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

configurations.all {
    exclude(group = "org.slf4j", module = "slf4j-log4j12")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

the<AppEngineAppYamlExtension>().apply {
    deploy {
        stopPreviousVersion = true
        promote = true
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}