package com.example.westernsanctions.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.westernsanctions.databinding.ItemPersonalBinding
import com.example.westernsanctions.model.PersonalSanctionsItem

class RVAdapter(
    private val sanctionsList: List<PersonalSanctionsItem>,
    private val context: Context,
) : RecyclerView.Adapter<SanctionsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SanctionsHolder {
        val binding =
            ItemPersonalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SanctionsHolder(binding, context)
    }

    override fun onBindViewHolder(holder: SanctionsHolder, position: Int) {
        holder.bind(sanctionsList[position])
    }

    override fun getItemCount(): Int {
        return sanctionsList.size
    }

}

class SanctionsHolder(private val binding: ItemPersonalBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(personalSanctionsItem: PersonalSanctionsItem) {
        with(binding) {
            if (personalSanctionsItem.sanctioned == false) {
                tvSource.text = personalSanctionsItem.source
                tvSanctioned.text = personalSanctionsItem.sanctioned.toString()
            } else if (personalSanctionsItem.source == "The UK Sanctions List") {
                defaultData(binding, personalSanctionsItem)
                binding.tvAddress.text =
                    "Birth country and place: ${personalSanctionsItem.birth_country}${personalSanctionsItem.birth_place}\n" +
                            "Birthday: ${personalSanctionsItem.birthday}"
                //tvAddress.text = personalSanctionsItem.address.joinToString()?:"не указан"

                btnUrl.setOnClickListener {
                    if (!personalSanctionsItem.source_url.isNullOrEmpty()) {
                        val uri = Uri.parse(personalSanctionsItem.source_url)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(launchBrowser)
                    } else {
                        Toast.makeText(context, "URL is Empty", Toast.LENGTH_LONG).show()
                    }
                }
            } else if (personalSanctionsItem.source == "EU Financial Sanctions Database (FSD)") {
                defaultData(binding, personalSanctionsItem)
                binding.tvAddress.text =
                    "Birth country and place: ${personalSanctionsItem.birth_country}${personalSanctionsItem.birth_place}\n" +
                            "Birthday: ${personalSanctionsItem.birthday}"
                binding.tvInfo.text = "Function: ${personalSanctionsItem.function}"
                btnUrl.setOnClickListener {
                    if (!personalSanctionsItem.doc_url.isNullOrEmpty()) {
                        val uri = Uri.parse(personalSanctionsItem.doc_url)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(launchBrowser)
                    } else {
                        Toast.makeText(context, "URL is Empty", Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                defaultData(binding, personalSanctionsItem)
                tvInfo.text = personalSanctionsItem.info
                btnUrl.setOnClickListener {
                    if (!personalSanctionsItem.source_url.isNullOrEmpty()) {
                        val uri = Uri.parse(personalSanctionsItem.source_url)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uri)
                        context.startActivity(launchBrowser)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun defaultData(binding: ItemPersonalBinding, personalSanctionsItem: PersonalSanctionsItem) {
        with(binding) {
            tvSource.text = personalSanctionsItem.source
            tvProgram.text = personalSanctionsItem.program
            tvName.text = "Name: ${personalSanctionsItem.name.joinToString()}"
        }
    }
}



