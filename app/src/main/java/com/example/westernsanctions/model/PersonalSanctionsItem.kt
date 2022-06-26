package com.example.westernsanctions.model

data class PersonalSanctionsItem(
    val FSD_id: String,
    val Unique_id: String,
    val address: List<String>,
    val birth_country: String,
    val birth_place: String,
    val birthday: String,
    val doc_publication_data: String,
    val doc_title: String,
    val doc_url: String,
    val function: String,
    val gender: String,
    val info: String,
    val name: List<String>,
    val program: String,
    val sanctioned: Boolean,
    val source: String,
    val source_url: String,
    val type: String
)