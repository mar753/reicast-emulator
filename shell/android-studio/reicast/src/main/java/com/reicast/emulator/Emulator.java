package com.reicast.emulator;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatDelegate;

import com.android.util.DreamTime;
import com.reicast.emulator.emu.JNIdc;

public class Emulator extends Application {

    public static final String pref_dynarecopt = "dynarec_opt";
    public static final String pref_unstable = "unstable_opt";
    public static final String pref_dynsafemode = "dyn_safemode";
    public static final String pref_cable = "dc_cable";
    public static final String pref_dcregion = "dc_region";
    public static final String pref_broadcast = "dc_broadcast";
    public static final String pref_rtt = "dc_rtt";
    public static final String pref_limitfps = "limit_fps";
    public static final String pref_nosound = "sound_disabled";
    public static final String pref_mipmaps = "use_mipmaps";
    public static final String pref_widescreen = "stretch_view";
    public static final String pref_frameskip = "frame_skip";
    public static final String pref_clipping = "clipping";
    public static final String pref_pvrrender = "pvr_render";
    public static final String pref_syncedrender = "synced_render";
    public static final String pref_modvols = "modifier_volumes";
    public static final String pref_resolutionv = "resolution_vertical";
    public static final String pref_resolutionh = "resolution_horizontal";
    public static final String pref_bootdisk = "boot_disk";
    public static final String pref_usereios = "use_reios";

    public static boolean dynarecopt = true;
    public static boolean idleskip = true;
    public static boolean unstableopt = false;
    public static boolean dynsafemode = false;
    public static int cable = 3;
    public static int dcregion = 3;
    public static int broadcast = 4;
    public static int rtt = 1;
    public static boolean limitfps = true;
    public static boolean nobatch = false;
    public static boolean nosound = false;
    public static boolean mipmaps = true;
    public static boolean widescreen = false;
    public static boolean subdivide = false;
    public static int frameskip = 0;
    public static boolean clipping = false;
    public static boolean pvrrender = false;
    public static boolean syncedrender = false;
    public static boolean modvols = true;
    public static int resolutionv = 100;
    public static int resolutionh = 100;
    public static String bootdisk = null;
    public static boolean usereios = false;
    
    /**
     * Load the user configuration from preferences
     *
     */
    public void getConfigurationPrefs(SharedPreferences mPrefs) {
        Emulator.dynarecopt = mPrefs.getBoolean(pref_dynarecopt, dynarecopt);
        Emulator.unstableopt = mPrefs.getBoolean(pref_unstable, unstableopt);
        Emulator.cable = mPrefs.getInt(pref_cable, cable);
        Emulator.dcregion = mPrefs.getInt(pref_dcregion, dcregion);
        Emulator.broadcast = mPrefs.getInt(pref_broadcast, broadcast);
        Emulator.rtt = mPrefs.getInt(pref_rtt, rtt);
        Emulator.limitfps = mPrefs.getBoolean(pref_limitfps, limitfps);
        Emulator.nosound = mPrefs.getBoolean(pref_nosound, nosound);
        Emulator.mipmaps = mPrefs.getBoolean(pref_mipmaps, mipmaps);
        Emulator.widescreen = mPrefs.getBoolean(pref_widescreen, widescreen);
        Emulator.frameskip = mPrefs.getInt(pref_frameskip, frameskip);
        Emulator.clipping = mPrefs.getBoolean(pref_clipping, clipping);
        Emulator.pvrrender = mPrefs.getBoolean(pref_pvrrender, pvrrender);
        Emulator.syncedrender = mPrefs.getBoolean(pref_syncedrender, syncedrender);
        Emulator.resolutionv = mPrefs.getInt(pref_resolutionv, resolutionv);
        Emulator.resolutionh = mPrefs.getInt(pref_resolutionh, resolutionh);
        Emulator.bootdisk = mPrefs.getString(pref_bootdisk, bootdisk);
        Emulator.usereios = mPrefs.getBoolean(pref_usereios, usereios);
    }

    /**
     * Write configuration settings to the emulator
     *
     */
    public void loadConfigurationPrefs() {
        JNIdc.dynarec(Emulator.dynarecopt ? 1 : 0);
        JNIdc.idleskip(Emulator.idleskip ? 1 : 0);
        JNIdc.unstable(Emulator.unstableopt ? 1 : 0);
        JNIdc.safemode(Emulator.dynsafemode ? 1 : 0);
        JNIdc.cable(Emulator.cable);
        JNIdc.region(Emulator.dcregion);
        JNIdc.broadcast(Emulator.broadcast);
        JNIdc.rtt(Emulator.rtt);
        JNIdc.limitfps(Emulator.limitfps ? 1 : 0);
        JNIdc.nobatch(Emulator.nobatch ? 1 : 0);
        JNIdc.nosound(Emulator.nosound ? 1 : 0);
        JNIdc.mipmaps(Emulator.mipmaps ? 1 : 0);
        JNIdc.widescreen(Emulator.widescreen ? 1 : 0);
        JNIdc.subdivide(Emulator.subdivide ? 1 : 0);
        JNIdc.frameskip(Emulator.frameskip);
        JNIdc.clipping(Emulator.clipping ? 1 : 0);
        JNIdc.pvrrender(Emulator.pvrrender ? 1 : 0);
        JNIdc.syncedrender(Emulator.syncedrender ? 1 : 0);
        JNIdc.modvols(Emulator.modvols ? 1 : 0);
        JNIdc.usereios(Emulator.usereios ? 1 : 0);
        JNIdc.frameskip(Emulator.resolutionv);
        JNIdc.frameskip(Emulator.resolutionh);
        JNIdc.bootdisk(Emulator.bootdisk);
        JNIdc.dreamtime(DreamTime.getDreamtime());
    }

    public void loadGameConfiguration(String gameId) {
        SharedPreferences mPrefs = getSharedPreferences(gameId, Activity.MODE_PRIVATE);
        JNIdc.dynarec(mPrefs.getBoolean(pref_dynarecopt, dynarecopt) ? 1 : 0);
        JNIdc.unstable(mPrefs.getBoolean(pref_unstable, unstableopt) ? 1 : 0);
        JNIdc.safemode(mPrefs.getBoolean(pref_dynsafemode, dynsafemode) ? 1 : 0);
        JNIdc.frameskip(mPrefs.getInt(pref_frameskip, frameskip));
        JNIdc.pvrrender(mPrefs.getBoolean(pref_pvrrender, pvrrender) ? 1 : 0);
        JNIdc.syncedrender(mPrefs.getBoolean(pref_syncedrender, syncedrender) ? 1 : 0);
        JNIdc.modvols(mPrefs.getBoolean(pref_modvols, modvols) ? 1 : 0);
        JNIdc.resolutionv(mPrefs.getInt(pref_resolutionv, resolutionv));
        JNIdc.resolutionh(mPrefs.getInt(pref_resolutionh, resolutionh));
        JNIdc.bootdisk(mPrefs.getString(pref_bootdisk, bootdisk));
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
