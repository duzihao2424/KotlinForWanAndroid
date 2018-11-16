package support.com.dzh.myapplication.model

import com.google.gson.annotations.SerializedName

data class HoneList(
        @SerializedName("curPage")
        val curPage: Int,
        @SerializedName("datas")
        val datas: List<Data>,
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

data class Data(
        @SerializedName("apkLink")
        val apkLink: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("chapterName")
        val chapterName: String,
        @SerializedName("collect")
        val collect: Boolean,
        @SerializedName("courseId")
        val courseId: Int,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("envelopePic")
        val envelopePic: String,
        @SerializedName("fresh")
        val fresh: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("niceDate")
        val niceDate: String,
        @SerializedName("origin")
        val origin: String,
        @SerializedName("projectLink")
        val projectLink: String,
        @SerializedName("publishTime")
        val publishTime: Long,
        @SerializedName("superChapterId")
        val superChapterId: Int,
        @SerializedName("superChapterName")
        val superChapterName: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: Int,
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("visible")
        val visible: Int,
        @SerializedName("zan")
        val zan: Int
)

data class Tag(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
)