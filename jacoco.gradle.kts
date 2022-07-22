plugins {
    java
    jacoco
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.isEnabled = true
        html.isEnabled = true
    }
}

tasks.register("jacoco") {
    dependsOn(tasks.jacocoTestCoverageVerification)
    dependsOn(tasks.jacocoTestReport)
}

tasks.check {
    finalizedBy(tasks.jacocoTestReport)
}