#!/usr/bin/env groovy

def call(body) {
    echo body
    echo "Enable balancer"
    return this
}
