package com.example.androidcourse.store

sealed class MainActivityNews

class ShowComputationError(val error: String) : MainActivityNews()
