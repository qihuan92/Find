package module

import com.qihuan.commonmodule.base.BaseApp
import com.qihuan.commonmodule.net.Api

import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * DailyApp
 *
 * @author Qi
 */
class DailyApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        RetrofitUrlManager.getInstance().putDomain(Api.ZHIHU_DOMAIN_VALUE, Api.ZHIHU_BASE_URL)
    }
}
