def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        agent any
        stages {
          stage('init'){
            steps{
                echo "initializing..."
                echo "${BUILD_NUMBER}"
            }
          }
          stage('test') {
              steps {
                  config.components.each { comp ->
                      echo "${comp.helmSelector}"
                  }
              }
          }
        }
    }
}
