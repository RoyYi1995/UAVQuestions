package bean

/**
 * 无人机信息bean
 */
data class UavMsg(var id:String="", var x:Int = 0, var y:Int = 0, var z:Int = 0, var _x:Int = 0, var _y:Int = 0, var _z:Int = 0, var error: Boolean = false)