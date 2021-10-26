package com.akshay.newsapp.core.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint

typealias BaseActivity = DaggerActivity

@AndroidEntryPoint
abstract class DaggerActivity : AppCompatActivity()
