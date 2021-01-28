package com.example.car

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qmuiteam.qmui.widget.QMUITopBar

class ServiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<QMUITopBar>(R.id.topbar).setTitle("服务")
        view.findViewById<View>(R.id.service_tv_shop_query).setOnClickListener {
            Log.d("ServiceFragment", "onViewCreated: 跳新页面")
/*            ARouter.getInstance()
                .build("/serviceNetwork/mainList")
                .withString("cityName", "全国")
                .withString("navType", "drive")
                .navigation()*/
        }
    }
}
