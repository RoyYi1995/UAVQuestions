package util

import bean.UavMsg

object CheckUtil {
    private val MAX_LENGTH = 7
    private val FIRST_LENGTH = 4
    var first = true

    /**
     * 判断信息长度
     */
    fun checkSizeError(msgArray: List<String>): Boolean {
        return if (first && msgArray.size == FIRST_LENGTH) {
            //是否是第一道msg，并且长度是否合适
            first = false
            return true
        } else msgArray.size == MAX_LENGTH
    }

    /**
     * 判断当前坐标是否是上个坐标之和
     * @param lastUavMsg 上一个坐标
     * @param nowUavMsg 当前坐标
     */
    fun checkCoordinate(lastUavMsg: UavMsg, nowUavMsg: UavMsg): Boolean {
        return lastUavMsg.x + lastUavMsg._x == nowUavMsg.x && lastUavMsg.y + lastUavMsg._y == nowUavMsg.y && lastUavMsg.z + lastUavMsg._z == nowUavMsg.z
    }

    /**
     * 判断id是否一致
     */
    fun checkUavId(lastUavId:String,nowUavId:String):Boolean{
        return lastUavId == nowUavId
    }
}