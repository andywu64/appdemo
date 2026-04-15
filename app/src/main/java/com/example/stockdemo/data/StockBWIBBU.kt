package com.example.stockdemo.data

import com.google.gson.annotations.SerializedName

// 上市個股日本益比、殖利率及股價淨值比
const val ExchangeReportBWIBBUALL = "v1/exchangeReport/BWIBBU_ALL"

data class StockBWIBBU(
    @SerializedName("Code")
    val code: String,           //股票代號,
    @SerializedName("Name")
    val name: String,           //股票名稱,
    @SerializedName("PEratio")
    val pERatio: String,        //本益比,
    @SerializedName("DividendYield")
    val dividendYield: String,  //殖利率(%),
    @SerializedName("PBratio")
    val pBRatio: String,        //股價淨值比
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StockBWIBBU) return false

        return code == other.code &&
                name == other.name &&
                pERatio == other.pERatio &&
                dividendYield == other.dividendYield &&
                pBRatio == other.pBRatio
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}

