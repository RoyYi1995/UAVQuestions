package view

interface IMainView {
    /**
     * 界面获取无人机信息
     */
    fun inputUavInfo()

    /**
     * 获取指定无人机信息编号
     */
    fun inputUavIndex()

    /**
     * 界面显示指定无人机信息
     */
    fun showUavMsg(msg:String)
}