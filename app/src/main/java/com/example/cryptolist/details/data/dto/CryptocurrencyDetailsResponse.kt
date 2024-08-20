package com.example.cryptolist.details.data.dto

import com.example.cryptolist.details.data.dto.crypto_details_response.Description
import com.example.cryptolist.details.data.dto.crypto_details_response.Image
import com.example.cryptolist.search.data.dto.Response
import com.google.gson.annotations.SerializedName

data class CryptocurrencyDetailsResponse(
    val id: String,
    val categories: List<String>,
    val description: Description,
    val image: Image,
    val name: String,
) : Response()