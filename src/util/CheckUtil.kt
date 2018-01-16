package util

import bean.Uav

object CheckUtil {
    private val MAX_LENGTH = 7
    private val FIRST_LENGTH = 4
    private var first = true

    /**
     * 判断信息长度
     */
    fun checkSizeError(msgArray: List<String>): Boolean {
        return if (first && msgArray.size == FIRST_LENGTH) {
            first = false
            return true
        } else msgArray.size == MAX_LENGTH
    }

    /**
     * 判断当前坐标是否是上个坐标之和
     */
    fun checkCoordinate(lastUav: Uav, nowUav: Uav): Boolean {
        return lastUav.x + lastUav._x == nowUav.x && lastUav.y + lastUav._y == nowUav.y && lastUav.z + lastUav._z == nowUav.z
    }
}