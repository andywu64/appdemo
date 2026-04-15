package com.example.stockdemo.ui

import androidx.annotation.StringRes
import com.example.stockdemo.R

enum class StockSortType(@get:StringRes val title: Int) {
    SortedByCodeAscending(R.string.sorted_by_code_ascending),
    SortedByCodeDescending(R.string.sorted_by_code_descending),
    //SortedByNameAscending(R.string.sorted_by_name_ascending),
   //SortedByNameDescending(R.string.sorted_by_name_descending),
}