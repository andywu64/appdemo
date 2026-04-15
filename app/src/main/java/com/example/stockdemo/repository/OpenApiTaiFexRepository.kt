package com.example.stockdemo.repository

import com.example.stockdemo.data.StockAll
import com.example.stockdemo.data.StockAvg
import com.example.stockdemo.data.StockBWIBBU

object OpenApiTaiFexRepository {
    private const val _previewStockBWIBBUCode = "1101"
    private val _previewStockBWIBBU = listOf(
        StockBWIBBU(
            code = "1101",
            name = "台泥",
            pERatio = "28.49",
            dividendYield = "2.95",
            pBRatio = "1.06",
        )
    )

    const val previewStockBWIBBUCode = _previewStockBWIBBUCode
    val previewStockBWIBBU = _previewStockBWIBBU

    private val _previewStockAvg = listOf(
        StockAvg(
            code = "0050",
            name = "元大台灣50",
            closingPrice = "192.35",
            monthlyAveragePrice = "193.30",
        )
    )

    val previewStockAvg = _previewStockAvg

    private val _previewStockAll = StockAll(
        code = "0050",
        name = "元大台灣50",
        tradeVolume = "8215118",
        tradeValue = "1589824232",
        openingPrice = "194.70",
        highestPrice = "194.75",
        lowestPrice = "192.25",
        closingPrice = "192.35",
        change = "-0.7000",
        transaction = "12809",
    )

    val previewStockAll = _previewStockAll
}