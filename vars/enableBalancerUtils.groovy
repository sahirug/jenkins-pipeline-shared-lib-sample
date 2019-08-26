#!/usr/bin/env groovy

def call(body) {
    sh "mvn -v"
    echo "Enable balancer"
    return this
}
