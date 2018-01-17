import view.IMainView
import view.MainView

/**
 * 程序开始
 */
fun main(args: Array<String>) {
    val mView:IMainView = MainView()
    mView.inputUavInfo()
    mView.inputUavIndex()
}