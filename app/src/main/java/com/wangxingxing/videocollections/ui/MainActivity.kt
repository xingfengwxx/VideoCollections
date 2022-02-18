package com.wangxingxing.videocollections.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.NavHostFragment
import com.wangxingxing.videocollections.R
import com.wangxingxing.videocollections.databinding.ActivityMainBinding
import com.wangxingxing.videocollections.extension.setOnClickListener
import com.wangxingxing.videocollections.ui.common.ui.BaseActivity
import com.wangxingxing.videocollections.ui.common.ui.FixFragmentNavigator
import com.wangxingxing.videocollections.ui.community.CommunityFragment
import com.wangxingxing.videocollections.ui.homepage.HomePageFragment
import com.wangxingxing.videocollections.ui.login.LoginActivity
import com.wangxingxing.videocollections.ui.mine.MineFragment
import com.wangxingxing.videocollections.ui.notification.NotificationFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * author : 王星星
 * date : 2022/2/14 16:36
 * email : 1099420259@qq.com
 * description : 
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fragment复用
        //获取页面容器NavHostFragment
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        //获取导航控制器
        navController = NavHostFragment.findNavController(fragment!!)
        //创建自定义的Fragment导航器
        val fragmentNavigator =
            FixFragmentNavigator(
                this,
                fragment.childFragmentManager,
                fragment.id
            )
        //获取导航器提供者
        val provider = navController.navigatorProvider
        //把自定义的Fragment导航器添加进去
        provider.addNavigator(fragmentNavigator)
        //手动创建导航图
        val navGraph = initNavGraph(provider, fragmentNavigator)
        //设置导航图
        navController.graph = navGraph

        val navigationBar = binding.navigationBar

        setOnClickListener(
            navigationBar.ivHomePage,
            navigationBar.ivCommunity,
            navigationBar.ivNotification,
            navigationBar.ivMine,
            navigationBar.ivRelease
        ) {
            when(this) {
                navigationBar.ivHomePage -> {
                    setTabSelection(0)
                    navController.navigate(R.id.navigation_homepage)
                }
                navigationBar.ivCommunity -> {
                    setTabSelection(1)
                    navController.navigate(R.id.navigation_community)
                }
                navigationBar.ivNotification -> {
                    setTabSelection(2)
                    navController.navigate(R.id.navigation_notification)
                }
                navigationBar.ivMine -> {
                    setTabSelection(3)
                    navController.navigate(R.id.navigation_mine)
                }
                navigationBar.ivRelease -> {
                    LoginActivity.start(this@MainActivity)
                }
            }
        }
        setTabSelection(0)
    }
    
    private fun setTabSelection(index: Int) {
        clearAllSelected()
        when (index) {
            0 -> {
                binding.navigationBar.ivHomePage.isSelected = true
                binding.navigationBar.tvHomePage.isSelected = true
            }
            1 -> {
                binding.navigationBar.ivCommunity.isSelected = true
                binding.navigationBar.tvCommunity.isSelected = true
            }
            2 -> {
                binding.navigationBar.ivNotification.isSelected = true
                binding.navigationBar.tvNotification.isSelected = true
            }
            3 -> {
                binding.navigationBar.ivMine.isSelected = true
                binding.navigationBar.tvMine.isSelected = true
            }
            else -> {
                binding.navigationBar.ivHomePage.isSelected = true
                binding.navigationBar.tvHomePage.isSelected = true
            }
        }
    }
    
    private fun clearAllSelected() {
        binding.navigationBar.ivHomePage.isSelected = false
        binding.navigationBar.tvHomePage.isSelected = false
        binding.navigationBar.ivCommunity.isSelected = false
        binding.navigationBar.tvCommunity.isSelected = false
        binding.navigationBar.ivNotification.isSelected = false
        binding.navigationBar.tvNotification.isSelected = false
        binding.navigationBar.ivMine.isSelected = false
        binding.navigationBar.tvMine.isSelected = false
    }

    /**
     * 手动创建导航图，把目的地添加进来
     */
    private fun initNavGraph(
        provider: NavigatorProvider,
        fragmentNavigator: FixFragmentNavigator
    ): NavGraph {
        val navGraph = NavGraph(NavGraphNavigator(provider))

        //用自定义的导航器来创建目的地
        val destination1 = fragmentNavigator.createDestination()
        destination1.id = R.id.navigation_homepage
        destination1.className = HomePageFragment::class.java.canonicalName
        destination1.label = resources.getString(R.string.homepage)
        navGraph.addDestination(destination1)

        val destination2 = fragmentNavigator.createDestination()
        destination2.id = R.id.navigation_community
        destination2.className = CommunityFragment::class.java.canonicalName
        destination2.label = resources.getString(R.string.community)
        navGraph.addDestination(destination2)

        val destination3 = fragmentNavigator.createDestination()
        destination3.id = R.id.navigation_notification
        destination3.className = NotificationFragment::class.java.canonicalName
        destination3.label = resources.getString(R.string.notification)
        navGraph.addDestination(destination3)

        val destination4 = fragmentNavigator.createDestination()
        destination4.id = R.id.navigation_mine
        destination4.className = MineFragment::class.java.canonicalName
        destination4.label = resources.getString(R.string.mine)
        navGraph.addDestination(destination4)

        navGraph.startDestination = R.id.navigation_homepage
        return navGraph
    }

    /**
     * 重写返回键事件
     * fix: ComponentActivity.onBackPressed -> ... -> NavController.popBackStack()
     * 自定义导航器后，会引起NavController管理的回退栈出问题
     */
    override fun onBackPressed() {
        val currentId = navController.currentDestination?.id
        if (currentId == R.id.navigation_search) {
            navController.navigate(R.id.navigation_homepage)
        } else {
            finish()
        }
    }

    fun getNavController(): NavController {
        return navController
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}