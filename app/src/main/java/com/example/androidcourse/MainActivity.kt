package com.example.androidcourse

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.androidcourse.common.Constants
import com.example.androidcourse.data.CalculatorService
import com.example.androidcourse.databinding.ActivityMainBinding
import com.example.androidcourse.store.*
import com.example.androidcourse.store.sideEffects.WriteNumberSideEffect
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG_PROGRAMMATICALLY = "programmatically"
private const val TAG_EMPTY = ""

class MainActivity : AppCompatActivity() {

    private val relay = PublishRelay.create<MainActivityNews>()

    private lateinit var binding: ActivityMainBinding

    private val store =
        MainActivityStore(
            listOf(WriteNumberSideEffect(CalculatorService(), relay)),
            relay
        )

    private var TAG: String? = ""

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        store.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::renderPage)

        store.newsRelay
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::renderNews)

        setupTextChangedListeners()
    }

    private fun renderPage(state: MainActivityState) {
        state.values?.let {
            TAG = TAG_PROGRAMMATICALLY

            if (firstNumberEditText.text.toString() != it.first) {
                firstNumberEditText.setText(it.first)
            }
            if (secondNumberEditText.text.toString() != it.second) {
                secondNumberEditText.setText(it.second)
            }
            resultEditText.setText(it.third.toString())

            TAG = TAG_EMPTY
        }

        binding.progressBar.isVisible = state.isLoading
    }

    private fun renderNews(news: MainActivityNews) {
        when (news) {
            is ShowComputationError -> buildAlertDialog(news.error)
        }
    }

    private fun setupTextChangedListeners() {
        binding.firstNumberEditText.doAfterTextChanged { text ->
            updateValues(Pair(Constants.FIRST_TERM, text.toString()))
        }

        binding.secondNumberEditText.doAfterTextChanged { text ->
            updateValues(Pair(Constants.SECOND_TERM, text.toString()))
        }

        binding.resultEditText.doAfterTextChanged { text ->
            updateValues(Pair(Constants.SUM, text.toString()))
        }
    }

    private fun updateValues(newValue: Pair<String, String>) {
        if (TAG != TAG_PROGRAMMATICALLY) {
            store.actionRelay.onNext(
                WriteValues(newValue)
            )
        }
    }

    private fun buildAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_title)
            .setMessage(message)
            .setPositiveButton(R.string.alert_dialog_positive_button) { _, _ -> }
            .show()
    }
}