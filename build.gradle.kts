plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("com.gradleup.shadow") version "8.3.0"
}

group = "com.github.goldcoin10.JavaMediaPlayer.goldcoin10"
version = "1.2.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("commons-codec:commons-codec:1.16.1")
    implementation("commons-io:commons-io:2.6")
}

tasks.test {
    useJUnitPlatform()
}


javafx {
    version = "21"
    modules( "javafx.media", "javafx.swing" )
}

application {
    mainClass.set("com.github.goldcoin10.JavaMediaPlayer.goldcoin10.Main")
}

tasks.jar {
    manifest {
        attributes["Implementation-Version"] = project.version
    }
}