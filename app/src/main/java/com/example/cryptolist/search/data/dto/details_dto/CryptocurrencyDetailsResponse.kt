package com.example.cryptolist.search.data.dto.details_dto

import com.example.cryptolist.search.data.dto.Response
import com.google.gson.annotations.SerializedName

data class CryptocurrencyDetailsResponse(
    @SerializedName("additional_notices") val additional_notices: List<Any>,
    @SerializedName("asset_platform_id") val asset_platform_id: Any,
    @SerializedName("block_time_in_minutes") val block_time_in_minutes: Int,
    val categories: List<String>,
    @SerializedName("community_data") val community_data: CommunityData,
    @SerializedName("country_origin") val country_origin: String,
    val description: Description,
    @SerializedName("detail_platforms") val detail_platforms: DetailPlatforms,
    @SerializedName("developer_data") val developer_data: DeveloperData,
    @SerializedName("genesis_date") val genesis_date: String,
    @SerializedName("hashing_algorithm") val hashing_algorithm: String,
    val id: String,
    val image: Image,
    @SerializedName("last_updated") val last_updated: String,
    val links: Links,
    val localization: Localization,
    @SerializedName("market_cap_rank") val market_cap_rank: Int,
    @SerializedName("market_data") val market_data: MarketData,
    val name: String,
    val platforms: Platforms,
    @SerializedName("preview_listing") val preview_listing: Boolean,
    @SerializedName("public_notice") val public_notice: Any,
    @SerializedName("sentiment_votes_down_percentage") val sentiment_votes_down_percentage: Double,
    @SerializedName("sentiment_votes_up_percentage") val sentiment_votes_up_percentage: Double,
    @SerializedName("status_updates") val status_updates: List<Any>,
    val symbol: String,
    val tickers: List<Ticker>,
    @SerializedName("watchlist_portfolio_users") val watchlist_portfolio_users: Int,
    @SerializedName("web_slug") val web_slug: String
) : Response()