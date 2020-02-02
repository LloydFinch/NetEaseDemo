package com.devloper.lloydfinch.neteasedemo

import android.Manifest
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.widget.*
import com.devloper.lloydfinch.neteasedemo.RxJava.RxJavaDemo
import com.devloper.lloydfinch.neteasedemo.databinding.LayoutDataBindingBinding
import com.devloper.lloydfinch.neteasedemo.drawable.CircleDrawable
import com.devloper.lloydfinch.neteasedemo.model.User
import com.devloper.lloydfinch.neteasedemo.okhttp.OkHttpDemo
import com.devloper.lloydfinch.neteasedemo.view.ListViewAdapter
import jp.wasabeef.richeditor.RichEditor

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var tvHello: Button
    private lateinit var btnDialog: Button


    private var bitmap: Bitmap? = null
    private var drawable: Drawable? = null


    private lateinit var btnFlipper: Button
    private lateinit var btnSwitcher: Button

    private lateinit var btnDir: Button
    private lateinit var richEditor: RichEditor

    //当前次数
    private var times: LiveData<Int> = MutableLiveData()


    private lateinit var btn1: Button
    private lateinit var btn2: Button

    private lateinit var listView: ListView

    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {

        //此时window.decor==null
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")
//        setContentView(R.layout.activity_main)

        /**
         * 这里测试数据绑定
         */
        //创建绑定类，LayoutDataBindingBinding会根据布局名自动生成(编译一下就行)
        val binding: LayoutDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.layout_data_binding)
        //绑定数据
        user.name = "hello"
        binding.user = user

        //添加点击事件动态刷新变化
        testDataBinding()

//        tvHello = findViewById(R.id.tv_hello)
//        tvHello.setOnClickListener {
//            test()
//        }

//
//        btnDialog = findViewById(R.id.tv_dialog)
//        btnDialog.setOnClickListener { showDialog() }
//
//        bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
//        drawable = BitmapDrawable(resources, bitmap)
//        bitmap?.apply {
//            drawable?.setBounds(0, 0, width, height)
//        }
//        btnDialog.background = drawable
//
//
//        val tv: TextView = findViewById(R.id.tv_drawable)
//        Log.e(TAG, "size = ${tv.compoundDrawables.size}")
//        tv.compoundDrawables.forEachIndexed { i, drawable ->
//            run {
//                Log.e(TAG, "index=$i, drawable=$drawable")
//            }
//        }
//
//        btnFlipper = findViewById(R.id.btn_flipper)
//        btnFlipper.setOnClickListener {
//            startActivity(Intent(this, ViewFlipperActivity::class.java))
//        }
//        btnSwitcher = findViewById(R.id.btn_switcher)
//        btnSwitcher.setOnClickListener {
//            startActivity(Intent(this, ImageSwitcherActivity::class.java))
//        }
//
//        btnDir = findViewById(R.id.btn_dir)
//        btnDir.setOnClickListener {
//            AndroidDirTest().test(this.applicationContext)
//        }
//
//        richEditor = findViewById(R.id.richer_editor)
//
//        initLiveData()

//        testClipToPadding()

        startActivity(Intent(this, PagingActivity::class.java))

    }

    var leval = 10
    private fun test() {
//        testRxJava()
//        testOkHttp()

//        drawable?.level = (leval--)


//        testGoogleApi()


        startActivity(Intent(this, Main2Activity::class.java))
    }

    private fun testRxJava() {
        val rxJavaDemo = RxJavaDemo()
        rxJavaDemo.test()
    }

    private fun testOkHttp() {
        val okHttpDemo = OkHttpDemo()
        okHttpDemo.test()
    }

    private fun showDialog() {

//        val dialog = Dialog(this)
//        dialog.show()

        startActivity(Intent(this, DialogActivity::class.java))
    }

    override fun onResume() {
        //此时已经有decor
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onStart() {
        //此时还没有decor
        super.onStart()
        Log.e(TAG, "onStart")
        //此时也没有decor
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show()
        }
    }


    private fun testGoogleApi() {
        //Resources直接获取Drawable
//        val drawable = ResourcesCompat.getDrawable(resources, R.drawable.shape_rect, null)
//        btnDialog.background = drawable

        //过渡动画
//        val translationDrawable: TransitionDrawable = ResourcesCompat.getDrawable(resources, R.drawable.translation_drawable, null) as TransitionDrawable
//        btnDialog.background = translationDrawable
//        translationDrawable.startTransition(1000)

        //ShapeDrawable
//        setContentView(ShapeView(this))

        //自定义Drawable
        btnDialog.background = CircleDrawable()
    }


    private val code = 10086
    private fun testPermission() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            //授予了权限
        } else {
            //未授予权限

            //检查是否需要显示权限解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //展示权限解释说明UI
                //用户: 1 勾选了"不再提示" 2 在setting里面设置未禁止就返回false
                //用户之前拒绝了该请求就返回true
            } else {
                //不需要展示权限解释，直接请求权限
                //会在onRequestPermissionsResult()里收到返回结果，如果系统已经禁止了该权限，则会立即返回到onRequestPermissionsResult()中，
                //并且是DENIED状态
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), code)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            code -> {
                //是自己发起的权限请求码
                grantResults.apply {
                    if (size > 0 && get(0) == PackageManager.PERMISSION_GRANTED) {
                        //授予了权限
                    } else {
                        //获取权限失败，展示提示UI
                    }
                }
            }
            else -> {
                //ignore all other requests
            }
        }
    }

    private fun testView() {
        Log.e(TAG, "decorView visibility: ${window.decorView}")
    }


    private fun testRicherEditor() {
        var e: EditText
        richEditor.setTextBackgroundColor(Color.RED)
    }


//    private fun initLiveData() {
//        times.observe(this, Observer<Int> {
//            Log.e(TAG, "times has been changed to $it")
//
//        })
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    /**
     * 测试同时按下两个button的反应
     */
    private fun testTouchEvent() {
        btn1 = findViewById(R.id.btn_1)
        btn1.setOnClickListener {
            Toast.makeText(this, "button1 ", Toast.LENGTH_SHORT).show()
        }

        btn2 = findViewById(R.id.btn_2)
        btn2.setOnClickListener {
            Toast.makeText(this, "button2 ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testClipChildren() {
//        btn1 = findViewById(R.id.btn_test)
//        btn1.setOnClickListener {
//            Toast.makeText(this, "click child", Toast.LENGTH_SHORT).show()
//        }
//
//        findViewById<View>(R.id.ll_test_parent).setOnClickListener {
//            Toast.makeText(this, "click parent", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun testClipToPadding() {
        listView = findViewById(R.id.list_view)
        listView.adapter = ListViewAdapter()
    }

    private fun testDataBinding() {

        /**
         * 测试单向绑定
         */
        val tvName: TextView = findViewById(R.id.tv_name)
        tvName.setOnClickListener {
            user.name = "name${System.currentTimeMillis()}"
        }

        /**
         * 测试双向绑定
         */
        val editName: EditText = findViewById(R.id.edit_name)
        editName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                /**
                 * 这里不用set，user的name会自动改变
                 */
//                s?.apply {
//                    user.name = toString()
//                }
                Log.d(TAG, "name: ${user.name}")
            }

        })
    }

}
