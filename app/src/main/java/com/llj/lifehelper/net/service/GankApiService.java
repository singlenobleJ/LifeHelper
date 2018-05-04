package com.llj.lifehelper.net.service;


import com.llj.lifehelper.net.model.gank.GankBaseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/4/10 12:44
 *    描述：
 * </pre>
 */
public interface GankApiService {
	/**
	 * @param category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
	 * @param count
	 * @param page
	 * @return
	 */
	@GET("{category}/{count}/{page}")
	Observable<GankBaseModel> requestGank(@Path("category") String category, @Path("count") int count, @Path("page") int page);
}
