package com.example.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.widget.QMUIRadiusImageView
import com.qmuiteam.qmui.widget.QMUITopBar

class MineFragment : Fragment() {

    private lateinit var mAvatar: QMUIRadiusImageView
    private lateinit var mNiceName: TextView
    private lateinit var mPhone: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAvatar = view.findViewById(R.id.iv_mine_avatar)
        mNiceName = view.findViewById(R.id.tv_mine_nickname)
        mPhone = view.findViewById(R.id.tv_mine_phone)
        val topbar = view.findViewById<QMUITopBar>(R.id.topbar)
        topbar.setTitle("我的")
        topbar.addRightImageButton(R.mipmap.icon_setting, R.id.right_view).setOnClickListener {
/*            ARouter.getInstance()
                .build("/setting/main")
                .withBoolean("showSettingVoicePhone", true)
                .navigation()*/
        }
        view.findViewById<View>(R.id.mine_info).setOnClickListener {
//            UserInfoActivity.launch()
        }
        view.findViewById<View>(R.id.ll_mine_car_list).setOnClickListener {
//            ARouter.getInstance()
//                                .build("/vehicleList/certificationList")
//                                .navigation();
        }
        view.findViewById<View>(R.id.ll_mine_insurance).setOnClickListener {

        }
        view.findViewById<View>(R.id.ll_mine_messages).setOnClickListener {
//            ARouter.getInstance()
//                                .build("/pushMessage/list")
//                                .navigation()
        }


    }
}
