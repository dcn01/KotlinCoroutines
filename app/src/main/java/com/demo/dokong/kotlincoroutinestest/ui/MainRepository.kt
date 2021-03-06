package com.demo.dokong.kotlincoroutinestest.ui

import com.demo.dokong.kotlincoroutinestest.comm.BaseRepository
import com.demo.dokong.kotlincoroutinestest.dao.AppDatabase
import com.demo.dokong.kotlincoroutinestest.ktx.conver
import com.demo.dokong.kotlincoroutinestest.ktx.payloadCommon
import com.demo.dokong.kotlincoroutinestest.response.RequestUrl
import com.demo.dokong.kotlincoroutinestest.response.RetrofitClient
import com.demo.dokong.kotlincoroutinestest.response.bean.BaseListResponse
import com.demo.dokong.kotlincoroutinestest.response.bean.BaseResponse
import com.demo.dokong.kotlincoroutinestest.response.bean.CommunityBean

/**
 * Author : zofnk.
 * Email : zofnk@vip.qq.com.
 * Creat Time :  2019-9-27. 15:19
 */
class MainRepository : BaseRepository() {

    suspend fun getCommunityList(
        uid: Int = 0,
        page: Int = 1,
        size: Int = 10
    ) = request {
        RetrofitClient
            .apiServer
            .getCommunityList(uid, page, size)
            .payloadCommon()
    }

    suspend fun getCommunityListByDatabase(): BaseResponse<BaseListResponse<CommunityBean>> =
        conver(
            AppDatabase.instance
                .getResponseDao()
                .queryResponse(RequestUrl.COMMUNITY_LIST)
                .result
        )

    suspend fun getCommunityListError(
        uid: Int = 0,
        page: Int = 1,
        size: Int = 10
    ) = request {
        RetrofitClient
            .apiServer
            .getCommunityList2(uid, page, size)
            .payloadCommon()
    }
}