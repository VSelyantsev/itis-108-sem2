import java.util.Properties

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.8"
    id("org.liquibase.gradle") version "2.2.0"
    id("jacoco")
}

apply(plugin = "io.spring.dependency-management")

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
//    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("javax.mail:javax.mail-api:1.6.2")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")
    implementation("org.thymeleaf:thymeleaf-spring5:3.0.15.RELEASE")


    // db
    implementation("org.postgresql:postgresql:42.5.3")
    implementation("junit:junit:4.13.1")

    // lombok
    annotationProcessor ("org.projectlombok:lombok:1.18.26")
    compileOnly("org.projectlombok:lombok:1.18.26")

    // webjars
    implementation("org.webjars:jquery:3.6.0")
    implementation("org.webjars:bootstrap:4.6.0")
    implementation("org.webjars:webjars-locator-core:0.46")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-junit-jupiter:5.1.0")



    // liquibase
    implementation("org.liquibase:liquibase-core:4.10.0")
    liquibaseRuntime("org.liquibase:liquibase-core:4.10.0")
    liquibaseRuntime("org.postgresql:postgresql:42.5.3")
    liquibaseRuntime("info.picocli:picocli:4.6.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport{
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

jacoco {
    toolVersion = "0.8.8"

    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }
    }
}


var props = Properties()
props.load(file("src/main/resources/liquibase.properties").inputStream())

liquibase {
    activities.register("main") {
        arguments = mapOf(
            "url" to props.get("url"),
            "driver" to props.get("driver-class-name"),
            "username" to props.get("username"),
            "password" to props.get("password"),
            "changeLogFile" to props.get("change-log-file")
        )
    }
}

