package com.example.sweetfish

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sweetfish.databinding.ActivityMainBinding
import com.example.sweetfish.utils.socketEvent.Join
import com.example.sweetfish.utils.socketEvent.Message
import com.example.sweetfish.utils.socketEvent.MessageReceipt
import com.example.sweetfish.utils.socketEvent.Revocation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var socket: Socket

    //    val token = intent.getStringExtra("token").toString()
//    val username = intent.getStringExtra("username").toString()
//    val uid = intent.getIntExtra("uid", 0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_main, R.id.navigation_notifications, R.id.navigation_my
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        connectSocketIO()
        listenSocketIO()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        socket.disconnect()
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    private fun connectSocketIO() {
        // 创建 OkHttp 实例
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()
        val options = IO.Options()
        options.callFactory = okHttpClient
        // 创建 Socket.IO 连接实例
        val mSocket: Socket = IO.socket("http://xiiaoxiongmc.e2.luyouxia.net:24947", options)
        socket = mSocket
        // 连接到服务器
        socket.connect()
    }

    private fun listenSocketIO() {
        val gson = Gson()
        // 连接成功的监听器
        socket.on(Socket.EVENT_CONNECT) {
            Log.d("socket", "Connected to server")
        }
        // 添加连接断开的监听器
        socket.on(Socket.EVENT_DISCONNECT) {
            Log.d("socket", "Disconnected from server")
            val jsonMsg = it.joinToString {
                it?.toString() ?: "null"
            }
            Log.d("zz", jsonMsg)
        }
        //连接错误的监听器
        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            // 连接错误的处理逻辑
            val exception = args[0] as Exception
            Log.e("socket", "连接错误: $exception")
        }
        //message事件的监听器
        socket.on("message") { args ->
            val jsonMsg = args.joinToString {
                it?.toString() ?: "null"
            }
            Log.d("zz", jsonMsg)
            val message = gson.fromJson(jsonMsg, MessageReceipt::class.java)
            EventBus.getDefault().post(message)
        }

        
        val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val mUid = prefs.getInt("id", 0)
        //先加入房间
        socket.emit("join", gson.toJson(Join(mUid)))
    }


    @Subscribe()
    fun onMessageReceipt(event: MessageReceipt) {
        Log.d("zz", event.message)
    }

    @Subscribe()
    fun onMessageSend(event: Message) {
        Log.d("zz", "接收到发送消息事件")
        val gson = Gson()
        socket.emit("message", gson.toJson(event))
    }

    @Subscribe()
    fun onRevocation(event: Revocation) {
        Log.d("zz", "接收到撤回事件")
        val gson = Gson()
        socket.emit("revocation", gson.toJson(event))
    }
}


