package com.example.androidcourse.store

import com.example.androidcourse.store.sideEffects.MainActivitySideEffect
import com.freeletics.rxredux.reduxStore
import com.jakewharton.rxrelay2.Relay
import io.reactivex.subjects.PublishSubject

class MainActivityStore(
    sideEffect: List<MainActivitySideEffect>,
    val newsRelay: Relay<MainActivityNews>
) {

    val actionRelay = PublishSubject.create<MainActivityAction>()

    val state = actionRelay.reduxStore(
        MainActivityState(),
        sideEffect,
        MainActivityReducer()::reduce
    )
}
