package com.qihuan.commonmodule.utils

import android.os.Build
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * DeviceUtils
 *
 * @author qi
 * @date 2018/5/21
 */
object DeviceUtils {
    private const val ROM_MIUI = "MIUI"
    private const val ROM_EMUI = "EMUI"
    private const val ROM_FLYME = "FLYME"
    private const val ROM_OPPO = "OPPO"
    private const val ROM_SMARTISAN = "SMARTISAN"
    private const val ROM_VIVO = "VIVO"
    private const val ROM_QIKU = "QIKU"

    private const val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
    private const val KEY_VERSION_EMUI = "ro.build.version.emui"
    private const val KEY_VERSION_OPPO = "ro.build.version.opporom"
    private const val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
    private const val KEY_VERSION_VIVO = "ro.vivo.os.version"

    private var sName: String? = null

    val isEmui: Boolean
        get() = check(ROM_EMUI)

    val isMiui: Boolean
        get() = check(ROM_MIUI)

    val isVivo: Boolean
        get() = check(ROM_VIVO)

    val isOppo: Boolean
        get() = check(ROM_OPPO)

    val isFlyme: Boolean
        get() = check(ROM_FLYME)

    val is360: Boolean
        get() = check(ROM_QIKU) || check("360")

    val isSmartisan: Boolean
        get() = check(ROM_SMARTISAN)

    private fun check(rom: String): Boolean {
        if (sName != null) {
            return sName == rom
        }

        if (!getProp(KEY_VERSION_MIUI).isNullOrEmpty()) {
            sName = ROM_MIUI
        } else if (!getProp(KEY_VERSION_EMUI).isNullOrEmpty()) {
            sName = ROM_EMUI
        } else if (!getProp(KEY_VERSION_OPPO).isNullOrEmpty()) {
            sName = ROM_OPPO
        } else if (!getProp(KEY_VERSION_VIVO).isNullOrEmpty()) {
            sName = ROM_VIVO
        } else if (!getProp(KEY_VERSION_SMARTISAN).isNullOrEmpty()) {
            sName = ROM_SMARTISAN
        } else {
            sName = if (Build.DISPLAY.toUpperCase().contains(ROM_FLYME)) {
                ROM_FLYME
            } else {
                Build.MANUFACTURER.toUpperCase()
            }
        }
        return sName == rom
    }

    private fun getProp(name: String): String? {
        val line: String
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $name")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return line
    }

}
