package com.example.westernsanctions.model

data class CompanySanctionsItem(
    val address: List<String>,
    val info: String,
    val name: List<String>,
    val program: String,
    val sanctioned: Boolean,
    val source: String,
    val source_url: String,
    val type: String
)