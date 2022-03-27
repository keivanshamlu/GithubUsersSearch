package com.shamlou.bases_android.edittext

import android.text.TextWatcher
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun TextInputEditText.textInputAsFlow() = callbackFlow {
        val watcher: TextWatcher = doOnTextChanged { textInput: CharSequence?, _, _, _ ->
            trySend(textInput)
        }
        awaitClose { this@textInputAsFlow.removeTextChangedListener(watcher) }
    }