package com.example.cryptolist.search.data.source

import com.example.cryptolist.search.data.dto.Response

interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
}