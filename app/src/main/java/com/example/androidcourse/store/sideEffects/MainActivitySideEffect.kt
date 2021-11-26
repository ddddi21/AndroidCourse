package com.example.androidcourse.store.sideEffects

import com.example.androidcourse.store.MainActivityAction
import com.example.androidcourse.store.MainActivityState
import com.freeletics.rxredux.SideEffect

typealias MainActivitySideEffect = SideEffect<MainActivityState, MainActivityAction>
