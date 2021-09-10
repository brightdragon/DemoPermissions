package com.bd.demopermissions.ui.main

import android.Manifest
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.bd.demopermissions.databinding.MainFragmentBinding

val requestPermissions = arrayOf(

    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.ACCESS_FINE_LOCATION

)

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private val requestSinglePermissionLaucher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){ isGranted : Boolean ->
            if(isGranted) {
                Log.e("##", "isGranted")
            }else {
                Log.e("##", "isDenied")
            }
    }

    private val requestMultiPermissionLaucher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { map ->
            for(entry in map.entries){

                Log.e("##", "entry key : ${entry.key}")
                Log.e("##", "entry value : ${entry.value}")

            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding = MainFragmentBinding.inflate(inflater)
        binding.model = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    private fun initObserve(){
        with(viewModel){
            permissionEvent.observe(viewLifecycleOwner, {
//                this@MainFragment.requestSinglePermission()
                this@MainFragment.requestMultiPermission()
            })
        }
    }

    private fun requestSinglePermission(){
        requestSinglePermissionLaucher.launch(Manifest.permission.CAMERA)
    }

    private fun requestMultiPermission(){
        requestMultiPermissionLaucher.launch(requestPermissions)
    }


}