def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        parameters {
            pipelineParams.components.each { component ->
                string (name: "${component.helmSelector}", description: 'Version of iloan sql')
            }
            // choice(name: 'version', choices:"3.4\n4.4", description: "Build for which version?" )
        }
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
          stage('test2') {
              steps {
                  script {
                      checkStatus()
                  }
              }
          }
        }
    }
}
