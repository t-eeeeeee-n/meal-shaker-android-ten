package com.example.frontend.data.repository

interface SelectRepository<T> {
    suspend fun getOptions(): List<T>{
        throw UnsupportedOperationException("このリポジトリではサポートされていません")
    }
    suspend fun getOptions(code: String): List<T> {
        throw UnsupportedOperationException("codeはこのリポジトリではサポートされていません")
    }
}