package com.example.westernsanctions.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.westernsanctions.databinding.ItemCompanyBinding
import com.example.westernsanctions.model.CompanySanctionsItem

class RVAdapter(private val sanctionsList: List<CompanySanctionsItem>,private val context:Context):RecyclerView.Adapter<SanctionsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SanctionsHolder {
        val binding = ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SanctionsHolder(binding,context)
    }

    override fun onBindViewHolder(holder: SanctionsHolder, position: Int) {
        holder.bind(sanctionsList[position])
    }

    override fun getItemCount(): Int {
      return sanctionsList.size
    }

}

class SanctionsHolder(private val binding: ItemCompanyBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(companySanctionsItem: CompanySanctionsItem) {
        with(binding) {
            if (companySanctionsItem.sanctioned==false){
                tvSource.text = companySanctionsItem.source
                tvSanctioned.text = companySanctionsItem.sanctioned.toString()
            }else{
            tvSource.text = companySanctionsItem.source
            tvSanctioned.text = companySanctionsItem.sanctioned.toString()
            tvProgram.text = companySanctionsItem.program
            tvName.text = companySanctionsItem.name.joinToString()
            tvAddress.text = companySanctionsItem.address.joinToString()
            tvInfo.text = companySanctionsItem.info
            btnUrl.setOnClickListener {
                val uri = Uri.parse(companySanctionsItem.source_url)
                val launchBrowser = Intent(Intent.ACTION_VIEW,uri)
                context.startActivity(launchBrowser)
            }}

        }
    }
}



