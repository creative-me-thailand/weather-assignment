package com.chanothai.assignment.domain.entity

enum class ResourceState {
    SUCCESS, FAILED
}

data class Resource<T>(val status: ResourceState) {

    var errorMessage: String? = null
    var data: T? = null
}