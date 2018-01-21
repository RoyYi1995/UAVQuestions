package model

interface IUavMod {
    fun addUavInfo(msg:String)
    fun getIndexMsg(index:Int,listener: UavMod.OnInfoHandleFinishListener)
}