package presenter

interface IUavPre {
    /**
     * 验证并封装无人机信息
     */
    fun submitDate(info:String)

    /**
     * 获取对应表示的无人机信息
     */
    fun getIndexDate(index:Int)
}