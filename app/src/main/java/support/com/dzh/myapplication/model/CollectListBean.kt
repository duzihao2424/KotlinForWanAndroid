package support.com.dzh.myapplication.model

import com.google.gson.annotations.SerializedName

data class CollectListBean(
        @SerializedName("curPage")
        val curPage: Int,
        @SerializedName("datas")
        val datas: List<CollectData>,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("over")
        val over: Boolean,
        @SerializedName("pageCount")
        val pageCount: Int,
        @SerializedName("size")
        val size: Int,
        @SerializedName("total")
        val total: Int
)

data class CollectData(
        @SerializedName("author")
        val author: String,
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("chapterName")
        val chapterName: String,
        @SerializedName("courseId")
        val courseId: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("envelopePic")
        val envelopePic: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("niceDate")
        val niceDate: String,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("originId")
        val originId: Int,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("visible")
        val visible: Int,
        @SerializedName("zan")
        val zan: Int
)