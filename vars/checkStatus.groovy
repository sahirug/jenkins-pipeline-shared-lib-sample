#!/usr/bin/env groovy

def call(body) {
    echo "Check status"
    echo "test"
    println body

    (1..3).each {
        echo "Number: " + it
    }

    currentBuild.result = 'SUCCESS' //FAILURE to fail
    return this
}
