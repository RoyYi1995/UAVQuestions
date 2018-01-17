import view.IMainView
import view.MainView

/**
 * 程序开始
 */
fun main(args: Array<String>) {
    val mView:IMainView = MainView()
    mView.inputUavInfo()//逐行输入信息
//    mView.inputUavInfoWithPath()//输入信息文本地址
    mView.inputUavIndex()
}