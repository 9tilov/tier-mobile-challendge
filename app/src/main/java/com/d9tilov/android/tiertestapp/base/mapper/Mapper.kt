package com.d9tilov.android.tiertestapp.base.mapper

interface Mapper<T, E> {
    fun toDataModel(dbModel: T): E
    fun toDbModel(dataModel: E): T
}
