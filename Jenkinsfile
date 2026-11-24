def runCmd(String cmd) {
    if (isUnix()) { sh cmd } else { bat cmd }
}

pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk   'JDK21'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser')
        string(name: 'TAGS', defaultValue: '@smoke', description: 'Cucumber tag expression')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run headless')
    }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Test') {
    steps {
        runCmd("mvn clean test -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} -Dcucumber.filter.tags=\"${params.TAGS}\"")
    }
}
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'saucedemo-automation-framework/target/surefire-reports/*.xml'

            publishHTML(target: [
                reportDir: 'saucedemo-automation-framework/target/cucumber-reports',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            archiveArtifacts artifacts: 'saucedemo-automation-framework/target/cucumber-reports/**', allowEmptyArchive: true
        }
    }
}