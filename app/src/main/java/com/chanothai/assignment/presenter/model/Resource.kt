package com.chanothai.assignment.presenter.model

enum class ResourceState {
    SUCCESS, FAILED
}

data class Resource<T>(val status: ResourceState) {

    var errorMessage: String? = null
    var data: T? = null
}