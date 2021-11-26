package com.example.androidcourse.store.sideEffects

import com.example.androidcourse.common.Constants
import com.example.androidcourse.common.exception.LettersNotAllowedException
import com.example.androidcourse.common.exception.UnExpectedException
import com.example.androidcourse.data.CalculatorService
import com.example.androidcourse.store.*
import com.freeletics.rxredux.StateAccessor
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import java.lang.NumberFormatException

class WriteNumberSideEffect(
    private val calculatorService: CalculatorService,
    private val newsRelay: Relay<MainActivityNews>
) : MainActivitySideEffect {

    override fun invoke(
        actions: Observable<MainActivityAction>,
        state: StateAccessor<MainActivityState>
    ): Observable<out MainActivityAction> {

        return actions.ofType<WriteValues>()
            .switchMap {
                if (state.invoke().error == null && state.invoke().lastChangedFields != null
                    && state.invoke().preLastChangedFields != null
                ) {
                    computation(
                        state.invoke().lastChangedFields!!,
                        state.invoke().preLastChangedFields!!
                    )
                        .map<MainActivityAction> {
                            ComputationSuccess(
                                Triple(
                                    it.first.toString(),
                                    it.second.toString(),
                                    it.third.toString()
                                )
                            )
                        }
                        .doOnError { throwable ->
                            when (throwable) {
                                LettersNotAllowedException -> newsRelay.accept(
                                    ShowComputationError(
                                        throwable.message.toString()
                                    )
                                )
                            }
                        }
                        .onErrorReturnItem(ErrorComputation)
                        .toObservable()
                        .startWith(StartComputation)
                } else {
                    Single.just(ErrorComputation)
                        .toObservable()
                }
            }
    }

    private fun computation(
        lastValue: Pair<String, String>,
        preLastValue: Pair<String, String>
    ): Single<Triple<Int, Int, Int>> {
        try {
            val lastNumber = Integer.parseInt(lastValue.second)
            val preLastNumber = Integer.parseInt(preLastValue.second)
            return when (lastValue.first) {
                Constants.FIRST_TERM -> {
                    when (preLastValue.first) {
                        Constants.SECOND_TERM -> {
                            calculatorService.getSum(
                                lastNumber,
                                preLastNumber
                            )
                        }
                        Constants.SUM -> {
                            calculatorService.getSecondTerm(
                                lastNumber,
                                preLastNumber
                            )
                        }
                        else -> {
                            Single.error(UnExpectedException)
                        }
                    }
                }
                Constants.SECOND_TERM -> {
                    when (preLastValue.first) {
                        Constants.FIRST_TERM -> {
                            calculatorService.getSum(
                                preLastNumber,
                                lastNumber
                            )
                        }
                        Constants.SUM -> {
                            calculatorService.getFirstTerm(
                                lastNumber,
                                preLastNumber
                            )
                        }
                        else -> {
                            Single.error(UnExpectedException)
                        }
                    }
                }
                Constants.SUM -> {
                    when (preLastValue.first) {
                        Constants.FIRST_TERM -> {
                            calculatorService.getSecondTerm(
                                preLastNumber,
                                lastNumber
                            )
                        }
                        Constants.SECOND_TERM -> {
                            calculatorService.getFirstTerm(
                                preLastNumber,
                                lastNumber
                            )
                        }
                        else -> {
                            Single.error(UnExpectedException)
                        }
                    }
                }
                else -> {
                    Single.error(UnExpectedException)
                }
            }
        } catch (e: NumberFormatException) {
            return Single.error(LettersNotAllowedException)
        }
    }
}