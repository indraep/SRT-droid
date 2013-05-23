package srt_droid;

import android.content.Intent;
import srt_droid.Account.User;
import srt_droid.Menu.MenuResto;
import srt_droid.Pesanan.Pesanan;


public class Utilities {
	public static User user;
	public static User oldUser;
	public static MenuResto menu;
	public static MenuResto oldMenu;
	public static Pesanan pesanan;
	public static Pesanan oldPesanan;
	public static Intent prev;
	public static int date1[] = new int[3];
	public static int date2[] = new int[3];
	
	
	//public static String URL = "http://10.0.2.2/SRTdroid/";
	//public static String URL = "http://192.168.43.254/SRTdroid/";
	public static String URL = "http://192.168.0.100/SRTdroid/";
	//public static String URL = "http://10.5.133.24/SRTdroid/";
	//public static String URL = "http://srtdroid.netii.net/";
	//public static String URL = "http://srt.pplcs.ui.ac.id/srtdroid/";
}
