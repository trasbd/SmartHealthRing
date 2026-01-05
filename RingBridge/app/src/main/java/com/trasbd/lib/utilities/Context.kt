package com.trasbd.lib.utilities

import android.content.Context
import android.content.ContextWrapper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope

fun Context.getLifecycleScope() = (this as? LifecycleOwner ?: (this as? ContextWrapper)?.baseContext as? LifecycleOwner)?.lifecycleScope