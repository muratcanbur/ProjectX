package co.icanteach.projectx.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}