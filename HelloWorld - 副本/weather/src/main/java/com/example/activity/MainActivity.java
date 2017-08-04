package com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.db.WeatherDB;
import com.example.db.WeatherOpenHelper;
import com.example.entity.CityEntity;
import com.example.entity.WeatherData;
import com.example.net.GetAllCitysAsyncTask;
import com.example.net.GetWholeJsonAsyncTask;
import com.example.service.UpdateWeatherService;
import com.example.utils.LogWriter;
import com.example.entity.Data;
import com.example.weather.R;
import com.example.entity.WeatherEntity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final String urlStr = "http://wthrcdn.etouch.cn/weather_mini?city=";
    Toolbar toolbar;
    TextView cityTv;
    TextView wenduTv;
    TextView ganmaoTv;
    TextView fengxiangTtv;
    String finalUrlStr;
    private WeatherDB weatherDB;
    private LocationManager locationManager;
    private String provider;
    private ViewPager viewPager;
    private LayoutInflater layoutInflater;
    private List<View> pageList = new ArrayList<View>();
    private Set<String> citySet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initViews(savedInstanceState);
//        loadWeatherData("http://wthrcdn.etouch.cn/weather_mini?city=" + getCityInLocation(getLocation()));//每次onCreate时获取当前城市天气；
//        loadLocalWeatherData("http://wthrcdn.etouch.cn/weather_mini?city=上海");//先默认当前位置是郑州
        if(isFirstIn()){
            SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putBoolean("isFirstIn",false);
            citySet.add("上海");
            editor.putStringSet("citys",citySet);
            editor.commit();

            loadLocalWeatherData(urlStr + "上海");
            loadCitysFromUrl();
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
            citySet = sharedPreferences.getStringSet("citys",null);
            if(citySet!=null){
                for(String city: citySet){
                    loadWeatherData(urlStr + city);
                }
            }
            Intent in = new Intent(this, UpdateWeatherService.class);
            startService(in);

            //将更新的天气数据显示出来；
            SharedPreferences sh = getSharedPreferences("data",MODE_PRIVATE);
            Set<String> dataSet = sh.getStringSet("weather",null);

            List<View> newViewList = new ArrayList<View>();
            for(String data: dataSet){
                WeatherEntity entity = JSON.parseObject(data,WeatherEntity.class);
                Data data1 = entity.getData();
                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
                TextView cityTv1 = (TextView) weatherPage.findViewById(R.id.cityNameTv);
                TextView wenduTv1 = (TextView) weatherPage.findViewById(R.id.wenduTv);
                TextView ganmaoTv1 = (TextView)weatherPage.findViewById(R.id.ganmaoTv);
                cityTv1.setText(data1.getCity());
                wenduTv1.setText("更新后：" + data1.getWendu() + "℃");
                ganmaoTv1.setText("更新后：" + data1.getGanmao());
                newViewList.add(weatherPage);
            }
            pageList = newViewList;
            pagerAdapter.notifyDataSetChanged();
        }
    }

    public void initVariables() {
        weatherDB = WeatherDB.getInstance(this);
    }

    public void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layoutInflater = getLayoutInflater().from(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        setSupportActionBar(toolbar);
    }

    public void loadLocalWeatherData(String url){
        GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "Status:1002\nDesc:Invalid city!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                WeatherEntity weatherEntity = JSON.parseObject(s, WeatherEntity.class);
                Data data = weatherEntity.getData();//这里面包含了好几天的温度，所以需要一个控件来放列表
                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
                TextView cityTv1 = (TextView) weatherPage.findViewById(R.id.cityNameTv);
                TextView wenduTv1 = (TextView) weatherPage.findViewById(R.id.wenduTv);
                TextView ganmaoTv1 = (TextView)weatherPage.findViewById(R.id.ganmaoTv);
                cityTv1.setText(data.getCity());
                wenduTv1.setText(data.getWendu() + "℃");
                ganmaoTv1.setText(data.getGanmao());
                pageList.add(weatherPage);
                pagerAdapter.notifyDataSetChanged();
//                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
//                citys.add(data.getCity());
//                editor.putStringSet("citys",citys);
//                editor.commit();
            }
        };
        getWholeJsonAsyncTask.execute(url);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {

        int count;

        @Override
        public int getCount() {
            return pageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageList.get(position));
            return pageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pageList.get(position));
        }

        @Override
        public void notifyDataSetChanged() {
            count = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
//            if(count>0){
//                count--;
//                return POSITION_NONE;
//            }
            return super.getItemPosition(object);
        }
    };

    public void loadWeatherData(String url) {
        GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "Status:1002\nDesc:Invalid city!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                WeatherEntity weatherEntity = JSON.parseObject(s, WeatherEntity.class);
                Data data = weatherEntity.getData();//这里面包含了好几天的温度，所以需要一个控件来放列表
                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
                TextView tv1 = (TextView) weatherPage.findViewById(R.id.cityNameTv);
                TextView tv2 = (TextView) weatherPage.findViewById(R.id.wenduTv);
                TextView tv3 = (TextView)weatherPage.findViewById(R.id.ganmaoTv);
                tv1.setText(data.getCity());
                tv2.setText(data.getWendu() + "℃");
                tv3.setText(data.getGanmao());
                pageList.add(weatherPage);
                pagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(pageList.size()-1);
            }
        };
        getWholeJsonAsyncTask.execute(url);
    }


    public Location getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//获取可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器！", Toast.LENGTH_SHORT).show();
        }
        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return location;
    }

    public String getCityInLocation(Location location) {
        String city = null;
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            //test:http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.983424,116.392987&output=json&pois=1&ak=KAGTao2QAxDtpnecmFgaGBks3aLEZ00d&mcode=C3:F9:9D:F3:B8:BB:65:3C:CE:79:10:7E:54:21:00:AB:F9:28:E1:88;com.example.weather
            String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng
                    + "&output=json&pois=1&ak=" + "KAGTao2QAxDtpnecmFgaGBks3aLEZ00d" + "&mcode=" + "C3:F9:9D:F3:B8:BB:65:3C:CE:79:10:7E:54:21:00:AB:F9:28:E1:88;com.example.weather";
            GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
                @Override
                public void onFail() {
                    Toast.makeText(MainActivity.this, "getCityInLocation() Failed!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(String s) {
                    //解析地址，只取出省市县即可
                }
            };
            getWholeJsonAsyncTask.execute(url);
        }
        return city;
    }



    //http://lbsyun.baidu.com/apiconsole/key/create
    //似乎需要先生成密钥keystore文件，然后用keytool命令生成SHA1指纹；
    //keytool -list -keystore C:\Users\Diak\.android\debug.keystore,密钥库口令默认android
    //C3:F9:9D:F3:B8:BB:65:3C:CE:79:10:7E:54:21:00:AB:F9:28:E1:88;com.example.weather
    //KAGTao2QAxDtpnecmFgaGBks3aLEZ00d
    //Latitude 纬度 Longitude 经度 Altitude 海拔


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.addItem:
                Intent intent = new Intent(MainActivity.this, AddCity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.deleteItem:
                int i = viewPager.getCurrentItem();
                TextView tv = (TextView)(pageList.get(i).findViewById(R.id.cityNameTv));
                String name = (String)tv.getText();
                if(citySet!=null){
                    citySet.remove(name);
                }
                SharedPreferences.Editor edi = getSharedPreferences("data",MODE_PRIVATE).edit();
                edi.remove("citys");
                edi.putStringSet("citys",citySet);
                pageList.remove(i);
                pagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(pageList.size()-1);
                Toast.makeText(this, "删除城市成功！", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                String string = urlStr + data.getStringExtra("cityName");
                Log.d("MainActivityLog", string);
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                citySet.add(data.getStringExtra("cityName"));
                editor.putStringSet("citys",citySet);
                editor.commit();
                loadWeatherData(string);
                break;
            default:
                break;
        }

    }

    public boolean isFirstIn(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        return sharedPreferences.getBoolean("isFirstIn",true);
    }

    public void loadCitysFromUrl() {
        String url = "http://files.heweather.com/china-city-list.json";
        GetAllCitysAsyncTask getAllCitysAsyncTask = new GetAllCitysAsyncTask() {
            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "loadCitysFromUrl() Failed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                saveCitysToDB(s);
            }
        };
        getAllCitysAsyncTask.execute(url);
    }

    public void saveCitysToDB(String rslt) {
        List<CityEntity> cityEntityList = JSON.parseArray(rslt, CityEntity.class);
        int i = 0;
        long start = System.currentTimeMillis();
        weatherDB.beginTransaction();
        for (CityEntity cityEntity : cityEntityList) {
//            Log.d("SaveCityEntitys:", cityEntity.getCityZh());
            weatherDB.saveCity(cityEntity);
        }
        weatherDB.setTransactionSuccessful();
        weatherDB.endTransaction();
        long end = System.currentTimeMillis();
        Log.d("cityEntityCount",String.valueOf(cityEntityList.size()));
        Log.d("saveCitysToDB time", String.valueOf(end - start));
    }
}
