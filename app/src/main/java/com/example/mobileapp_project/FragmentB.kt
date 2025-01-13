package com.example.mobileapp_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log

class FragmentB:Fragment (R.layout.fragment_b)
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("INSTANCES","OR Currently in $this")
    }
    override fun onResume()
    {
        super.onResume()
        Log.d("INSTANCES","OR Currently in $this")

    }
}