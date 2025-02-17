/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2018-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.overgrownpixel.overgrownpixeldungeon;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.gloves.Gloves;
import com.overgrownpixel.overgrownpixeldungeon.scenes.PixelScene;
import com.overgrownpixel.overgrownpixeldungeon.scenes.WelcomeScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.DeviceCompat;

import javax.microedition.khronos.opengles.GL10;

import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.util.Log;

import android.content.pm.PackageManager; // 添加这个 import，用于 PackageManager.PERMISSION_GRANTED

import android.app.AlertDialog; // 如果使用对话框
import androidx.annotation.NonNull;

public class OvergrownPixelDungeon extends Game {

	private static final int REQUEST_READ_PHONE_NUMBERS = 1;

	//variable constants for specific older versions of shattered, used for data conversion
	//versions older than v0.6.5c are no longer supported, and data from them is ignored
	public static final int v0_6_5c = 264;
	
	public static final int v0_7_0c = 311;
	public static final int v0_7_1d = 323;
	public static final int v0_7_2d = 340;
	public static final int v0_7_3  = 346;
	
	public OvergrownPixelDungeon() {
	    



		super( sceneClass == null ? WelcomeScene.class : sceneClass );
		
		//v0.7.0
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.bombs.Bomb.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.Bomb" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfRetribution.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfPsionicBlast" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.potions.elixirs.ElixirOfMight.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.potions.PotionOfMight" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.spells.MagicalInfusion.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.scrolls.ScrollOfMagicalInfusion" );
		
		//v0.7.1
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.SpiritBow.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.missiles.Boomerang" );
		
		com.watabou.utils.Bundle.addAlias(
				Gloves.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.Knuckles" );
		
		//v0.7.2
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfDisarming.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.stones.StoneOfDetectCurse" );
		
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Elastic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.curses.Elastic" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Elastic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Dazzling" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Elastic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Eldritch" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Grim.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Stunning" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Chilling.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Venomous" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Kinetic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Vorpal" );
		
		//v0.7.3
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Kinetic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Precise" );
		com.watabou.utils.Bundle.addAlias(
				com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Kinetic.class,
				"com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments.Swift" );
	}
		

	private void checkPhoneNumbersPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS)
            == PackageManager.PERMISSION_GRANTED) {
	    if (Music.INSTANCE != null) { // 添加判空检查
        // 权限已授予，注册回调
        Music.INSTANCE.registerTelephonyCallback(this);
    } else {
            Log.e("Permission", "Music.INSTANCE is null in checkPhoneNumbersPermission");
            // 可以添加其他处理，例如禁用相关功能或提示用户
        }
    }
	    else {
        // 请求权限
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_NUMBERS},
                REQUEST_READ_PHONE_NUMBERS);
    
	}
	}
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);

		updateSystemUI();
		OGPDSettings.landscape ( OGPDSettings.landscape() );
		
		Music.INSTANCE.enable( OGPDSettings.music() );
		Music.INSTANCE.volume( OGPDSettings.musicVol()/10f );
		Sample.INSTANCE.enable( OGPDSettings.soundFx() );
		Sample.INSTANCE.volume( OGPDSettings.SFXVol()/10f );
		
		Music.setMuteListener();

		Sample.INSTANCE.load(
				Assets.SND_CLICK,
				Assets.SND_BADGE,
				Assets.SND_GOLD,

				Assets.SND_STEP,
				Assets.SND_WATER,
				Assets.SND_OPEN,
				Assets.SND_UNLOCK,
				Assets.SND_ITEM,
				Assets.SND_DEWDROP,
				Assets.SND_HIT,
				Assets.SND_MISS,

				Assets.SND_DESCEND,
				Assets.SND_EAT,
				Assets.SND_READ,
				Assets.SND_LULLABY,
				Assets.SND_DRINK,
				Assets.SND_SHATTER,
				Assets.SND_ZAP,
				Assets.SND_LIGHTNING,
				Assets.SND_LEVELUP,
				Assets.SND_DEATH,
				Assets.SND_CHALLENGE,
				Assets.SND_CURSED,
				Assets.SND_EVOKE,
				Assets.SND_TRAP,
				Assets.SND_TOMB,
				Assets.SND_ALERT,
				Assets.SND_MELD,
				Assets.SND_BOSS,
				Assets.SND_BLAST,
				Assets.SND_PLANT,
				Assets.SND_RAY,
				Assets.SND_BEACON,
				Assets.SND_TELEPORT,
				Assets.SND_CHARMS,
				Assets.SND_MASTERY,
				Assets.SND_PUFF,
				Assets.SND_ROCKS,
				Assets.SND_BURNING,
				Assets.SND_FALLING,
				Assets.SND_GHOST,
				Assets.SND_SECRET,
				Assets.SND_BONES,
				Assets.SND_BEE,
				Assets.SND_DEGRADE,
				Assets.SND_MIMIC );

		if (!OGPDSettings.systemFont()) {
			RenderedText.setFont("fonts/pixelfont.ttf");
		} else {
			RenderedText.setFont( null );
		}
		
checkPhoneNumbersPermission();
	}
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_READ_PHONE_NUMBERS) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
		
            // 权限已授予，注册回调

if (Music.INSTANCE != null) { // 添加判空检查		
            Music.INSTANCE.registerTelephonyCallback(this);
        } else {
            // 权限被拒绝，处理逻辑，例如提示用户
	Log.e("Permission", "Music.INSTANCE is null in onRequestPermissionsResult"); // 正确的日志信息
             // 可以显示一个对话框告诉用户为什么需要这个权限
	}
	} else { Log.e("Permission", "READ_PHONE_NUMBERS permission denied.");
           // 权限被拒绝的处理
		
if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_NUMBERS)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            new AlertDialog.Builder(this)
                .setTitle("Phone Number Permission Needed")
                .setMessage("This app needs the phone number permission to properly handle incoming calls during gameplay.")
                .setPositiveButton("OK", (dialog, which) -> {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_NUMBERS},
                            REQUEST_READ_PHONE_NUMBERS);
                })
                .setNegativeButton("Cancel", null)
                .show();

        } else {
            // No explanation needed; request the permission
           // You can also inform the user that the functionality is disabled.
        // 用户选择了“不再询问”
                // 可以提示用户到设置中手动开启权限
               Toast.makeText(this, "请在设置中启用电话权限。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
	}		
        }
    }
}

	/* */
@Override
    protected void onDestroy() {
        super.onDestroy(); // 必须调用 super.onDestroy()
/* */
if (Music.INSTANCE != null) { // 添加判空检查
	Log.d("Permission", "onDestroy: about to unregister telephony callback"); // 取消注册前
       
        Music.INSTANCE.unregisterTelephonyCallback(this); // 取消注册回调
	Log.d("Permission", "onDestroy: telephony callback unregistered"); // 取消注册后
    } else {
        Log.d("Permission", "onDestroy: Music.INSTANCE is null, no need to unregister");
}
 // onDestroy() 方法的结束括号
}  
/* */

	/*
else {
        Log.e("Permission", "Music.INSTANCE is null in onDestroy");
    }
    */
	    /* */
    }
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if (scene instanceof PixelScene){
			((PixelScene) scene).saveWindows();
		}
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onWindowFocusChanged( boolean hasFocus ) {
		super.onWindowFocusChanged( hasFocus );
		if (hasFocus) updateSystemUI();
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
		super.onMultiWindowModeChanged(isInMultiWindowMode);
		updateSystemUI();
	}

	public static void switchNoFade(Class<? extends PixelScene> c){
		switchNoFade(c, null);
	}

	public static void switchNoFade(Class<? extends PixelScene> c, SceneChangeCallback callback) {
		PixelScene.noFade = true;
		switchScene( c, callback );
	}
	
	public static void seamlessResetScene(SceneChangeCallback callback) {
		if (scene() instanceof PixelScene){
			((PixelScene) scene()).saveWindows();
			switchNoFade((Class<? extends PixelScene>) sceneClass, callback );
		} else {
			resetScene();
		}
	}
	
	public static void seamlessResetScene(){
		seamlessResetScene(null);
	}
	
	@Override
	protected void switchScene() {
		super.switchScene();
		if (scene instanceof PixelScene){
			((PixelScene) scene).restoreWindows();
		}
	}
	
	@Override
	public void onSurfaceChanged( GL10 gl, int width, int height ) {
		
		if (scene instanceof PixelScene &&
				(height != Game.height || width != Game.width)) {
			((PixelScene) scene).saveWindows();
		}

		super.onSurfaceChanged( gl, width, height );

		updateDisplaySize();

	}

	public void updateDisplaySize(){
		boolean landscape = OGPDSettings.landscape();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			instance.setRequestedOrientation(landscape ?
					ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE :
					ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		} else {
			instance.setRequestedOrientation(landscape ?
					ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
					ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		
		if (view.getMeasuredWidth() == 0 || view.getMeasuredHeight() == 0)
			return;

		dispWidth = view.getMeasuredWidth();
		dispHeight = view.getMeasuredHeight();

		float dispRatio = dispWidth / (float)dispHeight;

		float renderWidth = dispRatio > 1 ? PixelScene.MIN_WIDTH_L : PixelScene.MIN_WIDTH_P;
		float renderHeight = dispRatio > 1 ? PixelScene.MIN_HEIGHT_L : PixelScene.MIN_HEIGHT_P;

		//force power saver in this case as all devices must run at at least 2x scale.
		if (dispWidth < renderWidth*2 || dispHeight < renderHeight*2)
			OGPDSettings.put( OGPDSettings.KEY_POWER_SAVER, true );

		if (OGPDSettings.powerSaver()){

			int maxZoom = (int)Math.min(dispWidth/renderWidth, dispHeight/renderHeight);

			renderWidth *= Math.max( 2, Math.round(1f + maxZoom*0.4f));
			renderHeight *= Math.max( 2, Math.round(1f + maxZoom*0.4f));

			if (dispRatio > renderWidth / renderHeight){
				renderWidth = renderHeight * dispRatio;
			} else {
				renderHeight = renderWidth / dispRatio;
			}

			final int finalW = Math.round(renderWidth);
			final int finalH = Math.round(renderHeight);
			if (finalW != width || finalH != height){

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						view.getHolder().setFixedSize(finalW, finalH);
					}
				});

			}
		} else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					view.getHolder().setSizeFromLayout();
				}
			});
		}
	}

	public static void updateSystemUI() {

		boolean fullscreen = Build.VERSION.SDK_INT < Build.VERSION_CODES.N
								|| !instance.isInMultiWindowMode();

		if (fullscreen){
			instance.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			instance.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		}

		if (DeviceCompat.supportsFullScreen()){
			if (fullscreen && OGPDSettings.fullscreen()) {
				instance.getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
						View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
						View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
						View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
			} else {
				instance.getWindow().getDecorView().setSystemUiVisibility(
						View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
			}
		}

	}
	
}
