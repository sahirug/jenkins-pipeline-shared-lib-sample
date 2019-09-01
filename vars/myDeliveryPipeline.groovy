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
                  script {
                      def components = pipelineParams.components
                      pipelineParams.components.each { component ->
                          echo "${component.helmSelector}"
                      }
                  }
              }
          }
        }
    }
}
