package com.aplication.coupons.mainModule.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aplication.coupons.BR
import com.aplication.coupons.R
import com.aplication.coupons.common.utils.hideKeyboard
import com.aplication.coupons.databinding.ActivityMainBinding
import com.aplication.coupons.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.coupon.observe(this@MainActivity){ coupon ->
                binding.isActive = coupon != null && coupon.isActive
            }
            it.getSnackbarMsg().observe(this@MainActivity){ msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
            }
            it.isHideKeyboard().observe(this@MainActivity){ isHide ->
                if (isHide) hideKeyboard(this@MainActivity, binding.root)
            }
        }
    }
}