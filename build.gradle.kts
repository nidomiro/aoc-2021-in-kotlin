plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {

        }
        test {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:5.0.1")
                implementation("io.kotest:kotest-property:5.0.1")
            }
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
