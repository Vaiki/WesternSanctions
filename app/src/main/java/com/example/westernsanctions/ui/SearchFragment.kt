package com.example.westernsanctions.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.westernsanctions.R
import com.example.westernsanctions.databinding.FragmentSearchBinding


class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

binding.btnSearch.setOnClickListener {
    if (!binding.teSearchCompany.text.isNullOrEmpty()){
        initRecyclerView()
    }
}


    }

    private fun initRecyclerView(){
        binding.rvSanctionsList.layoutManager = LinearLayoutManager(context)
        displayCompanySanctionsList()
    }
    private fun displayCompanySanctionsList(){
        viewModel.getResultPersonalSanctions(binding.teSearchCompany.text.toString())
        viewModel.personalSanctionsLiveData.observe(viewLifecycleOwner,{
            binding.rvSanctionsList.adapter = context?.let { it1 -> RVAdapter(it, it1) }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}