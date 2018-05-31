package module

import com.qihuan.commonmodule.base.BaseApp
import com.qihuan.commonmodule.net.Api

import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * MovieApp
 *
 * @author Qi
 */
class MovieApp : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        RetrofitUrlManager.getInstance().putDomain(Api.DOUBAN_DOMAIN_VALUE, Api.DOUBAN_BASE_URL)
    }
}
