package com.example.mybooks.data.mapper

import com.example.mybooks.data.exception.MapperException


internal interface Mapper<in I, out O> {

    @Throws(MapperException::class)
    fun map(input: I): O
}
