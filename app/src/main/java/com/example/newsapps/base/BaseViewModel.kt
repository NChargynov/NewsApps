package com.example.newsapps.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel(){
    var loading = MutableLiveData<Boolean>()
}