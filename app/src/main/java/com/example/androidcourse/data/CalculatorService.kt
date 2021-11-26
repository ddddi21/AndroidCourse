package com.example.androidcourse.data

import io.reactivex.Single
import java.util.concurrent.TimeUnit


private const val WORK_TIME = 5L

class CalculatorService {

    fun getFirstTerm(secondTerm: Int, sum: Int): Single<Triple<Int, Int, Int>> =
        getResult((sum - secondTerm), secondTerm, sum)

    fun getSecondTerm(firstTerm: Int, sum: Int): Single<Triple<Int, Int, Int>> =
        getResult(firstTerm, (sum - firstTerm), sum)

    fun getSum(firstTerm: Int, secondTerm: Int): Single<Triple<Int, Int, Int>> =
        getResult(firstTerm, secondTerm, (firstTerm + secondTerm))

    private fun getResult(firstTerm: Int, secondTerm: Int, sum: Int): Single<Triple<Int, Int, Int>> =
         Single.just(Triple(firstTerm, secondTerm, sum))
            .delay(WORK_TIME, TimeUnit.SECONDS)
}
