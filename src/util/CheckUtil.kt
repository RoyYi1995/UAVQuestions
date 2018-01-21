package util

import bean.UavMsg
import java.util.regex.Pattern

object CheckUtil {
    private val MAX_LENGTH = 7
    private val FIRST_LENGTH = 4
    var first = true

    /**
     * 判断信息长度
     */
    fun checkSizeError(msgArray: List<String>): Boolean {
        var error = true
        if (first){
            //如果是第一条，判断长度是否是4
            if (msgArray.size == FIRST_LENGTH){
                error = false
            }
            first = false
        }else{
            //不是第一条，判断长度是否是7
            if (msgArray.size == MAX_LENGTH){
                error = false
            }
        }
        return error
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
    fun checkUavIdError(lastUavId: ArrayList<UavMsg>, nowUavId: String): Boolean {
        var error = true
        if (!first){
            //不是第一条，判断是否和上一条相同
            if (lastUavId.last().id == nowUavId){
                error = false
            }
        }else{
            error = false
        }
        return error
    }

    /**
     * 判断id是否为数字或字母
     */
    fun checkIdFormatError(uavId: String): Boolean {
        val regEx = "^[A-Za-z0-9]+$"
        val p = Pattern.compile(regEx)
        return !p.matcher(uavId).matches()

    }
}