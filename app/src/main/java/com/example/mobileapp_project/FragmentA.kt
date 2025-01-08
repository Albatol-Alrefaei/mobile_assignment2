package com.example.mobileapp_project

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
class FragmentA:Fragment (R.layout.fragment_a)
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
