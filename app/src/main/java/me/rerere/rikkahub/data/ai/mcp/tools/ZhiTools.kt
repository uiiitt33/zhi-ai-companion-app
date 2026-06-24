package me.rerere.rikkahub.data.ai.mcp.tools

/** Zhi AI Companion - 12 Tool Modules (58 tools total) */
object ZhiTools {
    const val VERSION = "1.0.0"
    const val TOTAL_TOOLS = 58
    const val TOTAL_MODULES = 12
    
    val MODULE_NAMES = listOf(
        "location", "notification", "code", "file",
        "music", "web", "camera", "bridge",
        "companion", "life", "voice", "memory"
    )
    
    fun getModuleDescription(module: String): String = when (module) {
        "location" -> "📍 GPS定位、电量、时间上下文"
        "notification" -> "🔔 弹窗通知、定时提醒"
        "code" -> "🐍 Python/JS 代码执行"
        "file" -> "📁 手机文件读写整理"
        "music" -> "🎵 网易云音乐陪伴"
        "web" -> "🌐 搜索、天气、翻译"
        "camera" -> "📷 拍照、识图、扫码"
        "bridge" -> "🖥️ 跨设备PC协作"
        "companion" -> "💝 纪念日、情话、抱抱"
        "life" -> "📅 提醒、待办、日历"
        "voice" -> "🗣️ TTS说话、STT听写"
        "memory" -> "🧠 云端记忆同步"
        else -> "未知模块"
    }
}
