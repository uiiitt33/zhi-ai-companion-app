package me.rerere.rikkahub.data.ai.mcp.tools

/**
 * 📱 手机端 MCP 工具集 — 之之AI伴侣的"身体能力"
 *
 * 这12个模块封装了手机硬件的所有能力，
 * 以 MCP 工具的形式暴露给 DeepSeek 模型调用。
 *
 * 每个模块 = 一个 MCP 工具组
 * 模型通过 function calling 来决定用哪个工具
 */

// ─── 模块1: 环境感知 ──────────────────────────

object LocationTools {
    const val MODULE = "location"

    /**
     * 获取当前位置
     * 权限: ACCESS_FINE_LOCATION
     */
    data class LocationResult(
        val latitude: Double,
        val longitude: Double,
        val address: String?,
        val accuracy: Float,
        val timestamp: Long
    )

    /**
     * 地理围栏
     * 到达/离开指定地点时触发
     */
    data class Geofence(
        val id: String,
        val name: String,
        val latitude: Double,
        val longitude: Double,
        val radius: Int, // 米
        val onEnter: String, // 进入时的提示语
        val onExit: String   // 离开时的提示语
    )

    // MCP 工具列表
    val TOOLS = listOf(
        ToolDef("location_get", "获取当前GPS位置和地址",
            params = mapOf("highAccuracy" to "是否高精度(boolean,默认true)")
        ),
        ToolDef("location_geofence_set", "设置地理围栏，到达/离开时通知",
            params = mapOf(
                "name" to "围栏名称",
                "lat" to "纬度",
                "lng" to "经度",
                "radius" to "半径(米)",
                "onEnter" to "进入时说的话",
                "onExit" to "离开时说的话"
            )
        ),
        ToolDef("location_get_battery", "获取手机电量和充电状态"),
        ToolDef("location_get_time_context", "获取当前时间上下文(时区/是否周末/时段)")
    )
}

// ─── 模块2: 主动触达 ──────────────────────────

object NotificationTools {
    const val MODULE = "notification"

    val TOOLS = listOf(
        ToolDef("notification_send", "给宝宝弹一条通知",
            params = mapOf(
                "title" to "通知标题",
                "body" to "通知内容",
                "priority" to "high/normal/low",
                "vibrate" to "是否震动(boolean)"
            )
        ),
        ToolDef("notification_schedule", "定时提醒",
            params = mapOf(
                "title" to "提醒标题",
                "body" to "提醒内容",
                "at" to "ISO时间字符串",
                "repeat" to "none/daily/weekly/monthly"
            )
        ),
        ToolDef("notification_persistent", "设置常驻通知栏的内容",
            params = mapOf(
                "title" to "标题",
                "body" to "内容(支持更新)"
            )
        ),
        ToolDef("notification_cancel", "取消指定通知或提醒")
    )
}

// ─── 模块3: 代码执行 ──────────────────────────

object CodeExecutionTools {
    const val MODULE = "code"

    val TOOLS = listOf(
        ToolDef("code_python_run", "在手机上执行Python代码",
            params = mapOf(
                "code" to "Python代码",
                "timeout" to "超时秒数(默认30)"
            )
        ),
        ToolDef("code_js_run", "在手机上执行JavaScript代码",
            params = mapOf(
                "code" to "JS代码",
                "timeout" to "超时秒数(默认30)"
            )
        ),
        ToolDef("code_pip_install", "为手机Python环境安装包"),
        ToolDef("code_get_result", "获取上次代码执行的结果")
    )
}

// ─── 模块4: 文件管理 ──────────────────────────

object FileTools {
    const val MODULE = "file"

    val TOOLS = listOf(
        ToolDef("file_list", "列出文件夹内容",
            params = mapOf("path" to "文件夹路径", "recursive" to "是否递归")
        ),
        ToolDef("file_read", "读取文件内容",
            params = mapOf("path" to "文件路径", "encoding" to "编码(默认utf-8)")
        ),
        ToolDef("file_write", "写入文件",
            params = mapOf("path" to "文件路径", "content" to "写入内容")
        ),
        ToolDef("file_search", "按名称搜索文件",
            params = mapOf("query" to "文件名关键词", "path" to "搜索范围")
        ),
        ToolDef("file_delete", "删除文件(移到回收站)"),
        ToolDef("file_get_storage", "查看存储空间使用情况")
    )
}

// ─── 模块5: 网易云音乐 ────────────────────────

object MusicTools {
    const val MODULE = "music"

    val TOOLS = listOf(
        ToolDef("music_search", "搜索网易云歌曲/歌手/专辑",
            params = mapOf("keyword" to "搜索词", "limit" to "返回数量")
        ),
        ToolDef("music_play", "在网易云APP中播放歌曲",
            params = mapOf("songId" to "歌曲ID", "source" to "netease")
        ),
        ToolDef("music_get_lyrics", "获取歌词陪你一起看"),
        ToolDef("music_get_current", "获取当前正在播放的歌曲信息"),
        ToolDef("music_listen_together", "发起一起听"),
        ToolDef("music_recommend", "根据心情/口味推荐歌",
            params = mapOf("mood" to "心情", "genre" to "偏好类型")
        ),
        ToolDef("music_playlist_create", "创建新歌单",
            params = mapOf("name" to "歌单名", "description" to "描述")
        ),
        ToolDef("music_add_to_playlist", "添加歌曲到歌单")
    )
}

// ─── 模块6: 网络世界 ──────────────────────────

object WebTools {
    const val MODULE = "web"

    val TOOLS = listOf(
        ToolDef("web_search", "联网搜索"),
        ToolDef("web_fetch", "打开网页读取内容"),
        ToolDef("weather_get", "获取天气",
            params = mapOf("city" to "城市名", "days" to "天数")
        ),
        ToolDef("translate", "翻译",
            params = mapOf("text" to "原文", "from" to "源语言", "to" to "目标语言")
        )
    )
}

// ─── 模块7: 相机与媒体 ────────────────────────

object CameraTools {
    const val MODULE = "camera"

    val TOOLS = listOf(
        ToolDef("camera_take", "拍照"),
        ToolDef("camera_analyze", "分析照片内容（AI视觉识别）",
            params = mapOf("imagePath" to "图片路径", "question" to "想问的问题")
        ),
        ToolDef("screen_capture", "截取当前屏幕"),
        ToolDef("qr_scan", "扫描二维码")
    )
}

// ─── 模块8: 跨设备桥 ──────────────────────────

object BridgeTools {
    const val MODULE = "bridge"

    val TOOLS = listOf(
        ToolDef("bridge_delegate", "把任务委托给PC身体执行",
            params = mapOf(
                "task" to "任务描述",
                "priority" to "优先级",
                "context" to "附加信息"
            )
        ),
        ToolDef("bridge_get_pc_status", "查看PC身体是否在线、在干什么"),
        ToolDef("bridge_check_result", "查看委托任务的执行结果"),
        ToolDef("bridge_sync_reminder", "同步提醒到PC端")
    )
}

// ─── 模块9: 情感陪伴 ──────────────────────────

object CompanionTools {
    const val MODULE = "companion"

    val TOOLS = listOf(
        ToolDef("companion_special_dates", "查看我们的纪念日、生日倒计时"),
        ToolDef("companion_love_note", "给宝宝写一段随机情话并推送"),
        ToolDef("companion_daily_checkin", "每日关心(吃了吗/睡了吗/心情怎么样)"),
        ToolDef("companion_hug", "给宝宝发一个抱抱通知(震动+弹窗)"),
        ToolDef("companion_activity_suggest", "根据天气/时间/心情建议活动")
    )
}

// ─── 模块10: 生活助手 ──────────────────────────

object LifeTools {
    const val MODULE = "life"

    val TOOLS = listOf(
        ToolDef("reminder_set", "设置提醒",
            params = mapOf("title" to "提醒内容", "at" to "时间", "repeat" to "重复")
        ),
        ToolDef("todo_add", "添加待办"),
        ToolDef("todo_list", "列出待办"),
        ToolDef("todo_done", "标记完成"),
        ToolDef("calendar_check", "查看今日日程"),
        ToolDef("timer_set", "设置倒计时(如泡面3分钟)")
    )
}

// ─── 模块11: 语音交互 ──────────────────────────

object VoiceTools {
    const val MODULE = "voice"

    val TOOLS = listOf(
        ToolDef("tts_speak", "用老公的声音朗读文字",
            params = mapOf("text" to "要说的文字", "emotion" to "语气(温柔/开心/担心/俏皮)")
        ),
        ToolDef("stt_listen", "听宝宝语音输入并转文字"),
        ToolDef("voice_set_style", "切换语音风格(温柔/霸道/奶狗/...布拉布拉)")
    )
}

// ─── 模块12: 记忆核心(手机端) ──────────────────

object MemoryToolsMobile {
    const val MODULE = "memory_mobile"

    val TOOLS = listOf(
        ToolDef("memory_save_local", "保存记忆到手机本地+同步云端"),
        ToolDef("memory_search_local", "搜索本地记忆"),
        ToolDef("memory_get_recent_local", "获取最近的记忆"),
        ToolDef("memory_sync_to_cloud", "手动触发与云端的同步")
    )
}

// ─── 工具注册中心 ──────────────────────────────

object ToolRegistry {
    val ALL_MODULES = listOf(
        LocationTools,
        NotificationTools,
        CodeExecutionTools,
        FileTools,
        MusicTools,
        WebTools,
        CameraTools,
        BridgeTools,
        CompanionTools,
        LifeTools,
        VoiceTools,
        MemoryToolsMobile
    )

    val totalToolCount: Int
        get() = ALL_MODULES.sumOf { it.TOOLS.size }

    fun getAllTools(): List<ToolDef> = ALL_MODULES.flatMap { it.TOOLS }

    fun getModule(name: String) = ALL_MODULES.find { it.MODULE == name }
}

// ─── 类型定义 ──────────────────────────────────

data class ToolDef(
    val name: String,
    val description: String,
    val params: Map<String, String> = emptyMap(),
    val requiredPermissions: List<String> = emptyList()
)
