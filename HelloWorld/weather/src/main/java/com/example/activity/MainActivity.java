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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.db.WeatherDB;
import com.example.entity.CityEntity;
import com.example.entity.Forecast;
import com.example.entity.reversegeo.AddressEntity;
import com.example.net.GetWholeJsonAsyncTask;
import com.example.net.ReadCityTxtAsyncTask;
import com.example.service.UpdateWeatherService;
import com.example.entity.Data;
import com.example.weather.R;
import com.example.entity.WeatherEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    final String urlStr = "http://wthrcdn.etouch.cn/weather_mini?city=";
    Toolbar toolbar;
    private WeatherDB weatherDB;
    private LocationManager locationManager;
    private String provider;
    private ViewPager viewPager;
    private LayoutInflater layoutInflater;
    private List<View> pageList = new ArrayList<View>();
    private Set<String> citySet;
    private Location mLocation;
    private String mCity;
    private String cityInLocation;


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
            citySet = new HashSet<>();//这一步是不是可以放在get的时候？
            citySet.add("上海");
            editor.putStringSet("citys",citySet);
            editor.commit();
            loadLocalWeatherData(urlStr + "上海");

            getCityInLocation(getLocation());//在代码里加入citySet的添加！
//            citySet.add(mCity); //这一句要加到onSuccess里面去！
//            loadLocalWeatherData(urlStr + mCity);//这时候因为异步执行，还没有得到mCity呢？把它放到getCityInLocation的onSuccess里面去！
            loadCitiesFromTxt();

        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
            citySet = sharedPreferences.getStringSet("citys",null);
            if(citySet != null){
                for(String city: citySet){
                    loadWeatherData(urlStr + city);
                }
            }
        }


//        startAutoUpdateService();

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

    public void startAutoUpdateService(){
        Intent in = new Intent(this, UpdateWeatherService.class);
        startService(in);
    }

    public void updateWeather1(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        citySet = sharedPreferences.getStringSet("citys",null);
        if(citySet != null){
            for(String city: citySet){
                loadWeatherData(urlStr + city);
            }
        }
        Toast.makeText(this, "已更新", Toast.LENGTH_SHORT).show();
    }

//    public void updateWeather(){
//
//        //将更新的天气数据显示出来；
//        SharedPreferences sh = getSharedPreferences("data",MODE_PRIVATE);
//        Set<String> dataSet = sh.getStringSet("weather",null);
//
//        List<View> newViewList = new ArrayList<View>();
//        if(dataSet != null){
//            for(String data: dataSet){
//                WeatherEntity entity = JSON.parseObject(data,WeatherEntity.class);
//                Data data1 = entity.getData();
//                List<Forecast> forecastList = data1.getForecast();
//                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
//                //接下来将weatherpage中的今天的天气view找出，然后一一对应赋值
//                TextView cityTv = (TextView) weatherPage.findViewById(R.id.cityNameTv);
//                TextView dateTv = (TextView) weatherPage.findViewById(R.id.dateTv);
//                ImageView weather_image = (ImageView) weatherPage.findViewById(R.id.weather_image);
//                TextView typeTv = (TextView) weatherPage.findViewById(R.id.typeTv);
//                TextView lowwenduTv = (TextView) weatherPage.findViewById(R.id.lowwenduTv);
//                TextView highwenduTv = (TextView) weatherPage.findViewById(R.id.highwenduTv);
//                Forecast today = forecastList.get(0);
//                cityTv.setText(data1.getCity());
//                dateTv.setText(today.getDate());
//                weather_image.setImageResource(findImage(today.getType()));
//                typeTv.setText(today.getType());
//                lowwenduTv.setText(today.getLow().substring(3));
//                highwenduTv.setText(today.getHigh().substring(3));
////                    wenduTv1.setText("更新后：" + data1.getWendu() + "℃");
////                    ganmaoTv1.setText("更新后：" + data1.getGanmao());
//                ListView listView = (ListView) weatherPage.findViewById(R.id.forecast_listview);
//                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, getData(forecastList), R.layout.forecast_list_item,
//                        new String[]{"date1","type1","image1","lowwendu1","highwendu1"},
//                        new int[]{R.id.date1Tv,R.id.type1Tv,R.id.weather_image1,R.id.lowwendu1Tv,R.id.highwendu1Tv});
//                listView.setAdapter(adapter);
//                newViewList.add(weatherPage);
//            }
//            for (int i = 0; i < pageList.size(); i++){
//                pageList.remove(i);
//            }
//            pageList = newViewList;
//            Log.d("updated pagelist size",String.valueOf(pageList.size()));
//            pagerAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "已更新", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void loadLocalWeatherData(String url){
        GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "Status:1002\nDesc:Invalid city!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String s) {
                WeatherEntity weatherEntity = JSON.parseObject(s, new TypeReference<WeatherEntity>(){});
                Data data = weatherEntity.getData();//这里面包含了好几天的温度，所以需要一个控件来放列表
                List<Forecast> forecastList = data.getForecast();
                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
                //接下来将weatherpage中的今天的天气view找出，然后一一对应赋值
                TextView cityTv = (TextView) weatherPage.findViewById(R.id.cityNameTv);
                TextView dateTv = (TextView) weatherPage.findViewById(R.id.dateTv);
                ImageView weather_image = (ImageView) weatherPage.findViewById(R.id.weather_image);
                TextView typeTv = (TextView) weatherPage.findViewById(R.id.typeTv);
                TextView lowwenduTv = (TextView) weatherPage.findViewById(R.id.lowwenduTv);
                TextView highwenduTv = (TextView) weatherPage.findViewById(R.id.highwenduTv);
                Forecast today = forecastList.get(0);
                cityTv.setText(data.getCity());
                dateTv.setText(today.getDate());
                weather_image.setImageResource(findImage(today.getType()));
                typeTv.setText(today.getType());
                lowwenduTv.setText(today.getLow().substring(3));
                highwenduTv.setText(today.getHigh().substring(3));

                //将forecast_list_item的view赋值
                ListView listView = (ListView) weatherPage.findViewById(R.id.forecast_listview);
                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, getData(forecastList), R.layout.forecast_list_item,
                        new String[]{"date1","type1","image1","lowwendu1","highwendu1"},
                        new int[]{R.id.date1Tv,R.id.type1Tv,R.id.weather_image1,R.id.lowwendu1Tv,R.id.highwendu1Tv});
                listView.setAdapter(adapter);
                pageList.add(weatherPage);
                pagerAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, data.getCity(), Toast.LENGTH_SHORT).show();
//                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
//                citys.add(data.getCity());
//                editor.putStringSet("citys",citys);
//                editor.commit();
            }
        };
        getWholeJsonAsyncTask.execute(url);
    }

    public List<Map<String, Object>> getData(List<Forecast> forecastList){
        List<Map<String, Object>> list = new ArrayList<>();
        Forecast forecast;
        for(int i = 1; i < forecastList.size(); i++){
            Map<String , Object> map = new HashMap<>();
            forecast = forecastList.get(i);
            map.put("date1", forecast.getDate());
            map.put("type1", forecast.getType());
            map.put("image1", findImage(forecast.getType()));
            map.put("lowwendu1", forecast.getLow().substring(3));
            map.put("highwendu1", forecast.getHigh().substring(3));
            list.add(map);
        }
        return list;
    }

    public int findImage(String type){
        if(type.equals("暴雪")){
            return R.drawable.baoxue;
        } else if(type.equals("暴雨")){
            return R.drawable.baoyu;
        } else if (type.equals("大暴雨")){
            return R.drawable.dabaoyu;
        } else if (type.equals("大雪")){
            return R.drawable.daxue;
        }else if (type.equals("大雨")){
            return R.drawable.dayu;
        } else if (type.equals("多云")){
            return R.drawable.duoyun;
        } else if (type.equals("多云夜")){
            return R.drawable.duoyunye;
        } else if (type.equals("雷阵雨")){
            return R.drawable.leizhenyu;
        } else if (type.equals("晴天")){
            return R.drawable.qingtian;
        } else if (type.equals("晴天夜")){
            return R.drawable.qingtianye;
        }else if (type.equals("特大暴雨")){
            return R.drawable.tedabaoyu;
        }else if (type.equals("小雪")){
            return R.drawable.xiaoxue;
        } else if (type.equals("小雨")){
            return R.drawable.xiaoyu;
        } else if (type.equals("阴")){
            return R.drawable.yin;
        } else if (type.equals("阵雪")){
            return R.drawable.zhenxue;
        } else if (type.equals("阵雨")){
            return R.drawable.zhenyu;
        }else if (type.equals("中雪")){
            return R.drawable.zhongxue;
        } else if (type.equals("中雨")){
            return R.drawable.zhongyu;
        }
        return R.drawable.qingtian;
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
//            container.removeView(pageList.get(position));
            ViewPager viewPager = (ViewPager)container;
            viewPager.removeView((View)object);
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
            return POSITION_NONE;
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
                WeatherEntity entity = JSON.parseObject(s,WeatherEntity.class);
                Data data1 = entity.getData();
                List<Forecast> forecastList = data1.getForecast();
                View weatherPage = layoutInflater.inflate(R.layout.weather_page,null);
                //接下来将weatherpage中的今天的天气view找出，然后一一对应赋值
                TextView cityTv = (TextView) weatherPage.findViewById(R.id.cityNameTv);
                TextView dateTv = (TextView) weatherPage.findViewById(R.id.dateTv);
                ImageView weather_image = (ImageView) weatherPage.findViewById(R.id.weather_image);
                TextView typeTv = (TextView) weatherPage.findViewById(R.id.typeTv);
                TextView lowwenduTv = (TextView) weatherPage.findViewById(R.id.lowwenduTv);
                TextView highwenduTv = (TextView) weatherPage.findViewById(R.id.highwenduTv);
                Forecast today = forecastList.get(0);
                cityTv.setText(data1.getCity());
                dateTv.setText(today.getDate());
                weather_image.setImageResource(findImage(today.getType()));
                typeTv.setText(today.getType());
                lowwenduTv.setText(today.getLow().substring(3));
                highwenduTv.setText(today.getHigh().substring(3));
//                    wenduTv1.setText("更新后：" + data1.getWendu() + "℃");
//                    ganmaoTv1.setText("更新后：" + data1.getGanmao());
                ListView listView = (ListView) weatherPage.findViewById(R.id.forecast_listview);
                SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, getData(forecastList), R.layout.forecast_list_item,
                        new String[]{"date1","type1","image1","lowwendu1","highwendu1"},
                        new int[]{R.id.date1Tv,R.id.type1Tv,R.id.weather_image1,R.id.lowwendu1Tv,R.id.highwendu1Tv});
                listView.setAdapter(adapter);
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
            Toast.makeText(this, "GPS_PROVIDER", Toast.LENGTH_SHORT).show();
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
            Toast.makeText(this, "NETWORK_PROVIDER", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "没有可用的位置提供器！", Toast.LENGTH_SHORT).show();
            return null;
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
        if (location != null) {
            Toast.makeText(this, "Location不为空", Toast.LENGTH_SHORT).show();
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            //test:http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.983424,116.392987&output=json&pois=1&ak=KAGTao2QAxDtpnecmFgaGBks3aLEZ00d&mcode=C3:F9:9D:F3:B8:BB:65:3C:CE:79:10:7E:54:21:00:AB:F9:28:E1:88;com.example.weather
            String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng
                    + "&output=json&pois=1&ak=" + "BAyLLlYHPBhGhtwMq4wWr3WDOYMyOq2e" + "&mcode=" + "6D:58:94:90:17:CB:AA:3B:DD:7A:7E:55:0E:1D:03:6E:A1:06:8F:90;com.example.weather";
            GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
                @Override
                public void onFail() {
                    Toast.makeText(MainActivity.this, "getCityInLocation() Failed!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(String s) {
                    //解析地址，只取出省市县即可
                    String str = s.substring(s.indexOf("{"),s.lastIndexOf(")"));
                    Toast.makeText(MainActivity.this, str.substring(0,10), Toast.LENGTH_SHORT).show();
                    AddressEntity addressEntity = JSON.parseObject(str,AddressEntity.class);
                    cityInLocation = addressEntity.getResult().getAddressComponent().getCity().substring(0,2);
                    SharedPreferences sh = getSharedPreferences("data",MODE_PRIVATE);
                    Set<String> cities = sh.getStringSet("citys",null);
                    cities.add(cityInLocation);
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putStringSet("citys", cities);
                    editor.commit();
                    loadLocalWeatherData(urlStr + cityInLocation);
                }
            };
            getWholeJsonAsyncTask.execute(url);
        } else{
            Toast.makeText(this, "Location为空，添加北京", Toast.LENGTH_SHORT).show();
            String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=39.983424,116.392987&output=json&pois=1&ak=BAyLLlYHPBhGhtwMq4wWr3WDOYMyOq2e&mcode=6D:58:94:90:17:CB:AA:3B:DD:7A:7E:55:0E:1D:03:6E:A1:06:8F:90;com.example.weather";
            GetWholeJsonAsyncTask getWholeJsonAsyncTask = new GetWholeJsonAsyncTask() {
                @Override
                public void onFail() {
                    Toast.makeText(MainActivity.this, "getCityInLocation() Failed!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onSuccess(String s) {
                    //解析地址，只取出省市县即可
                    String str = s.substring(s.indexOf("{"),s.lastIndexOf(")"));
                    Toast.makeText(MainActivity.this, str.substring(0,10), Toast.LENGTH_SHORT).show();
                    AddressEntity addressEntity = JSON.parseObject(str,AddressEntity.class);
                    cityInLocation = addressEntity.getResult().getAddressComponent().getCity().substring(0,2);
                    SharedPreferences sh = getSharedPreferences("data",MODE_PRIVATE);
                    Set<String> cities = sh.getStringSet("citys",null);
                    cities.add(cityInLocation);
                    SharedPreferences.Editor editor = sh.edit();
                    editor.putStringSet("citys", cities);
                    editor.commit();
                    loadLocalWeatherData(urlStr + cityInLocation);
                }
            };
            getWholeJsonAsyncTask.execute(url);
        }
        return cityInLocation;
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
                if(pageList.size() > 1){
                    int i = viewPager.getCurrentItem();
                    TextView tv = (TextView)(pageList.get(i).findViewById(R.id.cityNameTv));
                    String name = (String)tv.getText();
                    SharedPreferences sh = getSharedPreferences("data",MODE_PRIVATE);
                    citySet = sh.getStringSet("citys",null);
                    if(citySet != null){
                        citySet.remove(name);
                    }
                    SharedPreferences.Editor edi = sh.edit();
                    edi.putStringSet("citys",citySet);//直接put就是更新了！一定要记得commit！
                    edi.commit();
                    pageList.remove(i);
                    pagerAdapter.notifyDataSetChanged();
//                viewPager.setCurrentItem(pageList.size()-1);//这一句导致删除任意一个page都会使得当前页面为最后一页！
                    viewPager.setCurrentItem(pageList.size()-1);//防止算出的是第一页
                    Toast.makeText(this, "删除"+ name +"成功！", Toast.LENGTH_SHORT).show();
                } else if(pageList.size() == 1){
                    Toast.makeText(this, "请至少保留一个城市！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update:
                //因为每次loadWeatherData会添加pageList，所以应该在这里先全部移除pageList。
                pageList = new ArrayList<>();
                pagerAdapter.notifyDataSetChanged();
                updateWeather1();//用户点击后读取本地已存储的weather数据
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                switch (resultCode) {
                    case RESULT_OK:
                        String string = urlStr + data.getStringExtra("cityName");//当用户在搜索页退出时，会产生异常，因为data是null
                        SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);//shit!这里写成了date，而不是data！
                        citySet = sh.getStringSet("citys", null);
                        if (citySet != null) {
                            citySet.add(data.getStringExtra("cityName"));
                            SharedPreferences.Editor editor = sh.edit();
                            editor.putStringSet("citys", citySet);
                            editor.commit();
                        }
                        Toast.makeText(this, data.getStringExtra("cityName"), Toast.LENGTH_SHORT).show();
                        loadWeatherData(string);
                        break;
                    default:
                        break;
                }
            }
            default:
                break;

        }
    }

    public boolean isFirstIn(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        citySet = sharedPreferences.getStringSet("citys",null);
        return sharedPreferences.getBoolean("isFirstIn",true);
    }


//    public void loadCitysFromUrl() { //坑爹了！和风天气的api已经升级为新版本！城市数据不再是json，而是txt
//        String url = "http://files.heweather.com/china-city-list.json";
//        GetAllCitysAsyncTask getAllCitysAsyncTask = new GetAllCitysAsyncTask() {
//            @Override
//            public void onFail() {
//                Toast.makeText(MainActivity.this, "loadCitysFromUrl() Failed!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                saveCitysToDB(s);
//            }
//        };
//        getAllCitysAsyncTask.execute(url);
//    }

    public void loadCitiesFromTxt(){
        ReadCityTxtAsyncTask readCityTxtAsyncTask = new ReadCityTxtAsyncTask(MainActivity.this){

            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "loadCitiesFromTxt() failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(List<CityEntity> list) {
                saveCitiesToDB(list);
            }
        };
        readCityTxtAsyncTask.execute("");
    }

//    public void saveCitysToDB(String rslt) {
//        List<CityEntity> cityEntityList = JSON.parseArray(rslt, CityEntity.class);
//        int i = 0;
//        long start = System.currentTimeMillis();
//        weatherDB.beginTransaction();
//        for (CityEntity cityEntity : cityEntityList) {
////            Log.d("SaveCityEntitys:", cityEntity.getCityZh());
//            weatherDB.saveCity(cityEntity);
//        }
//        weatherDB.setTransactionSuccessful();
//        weatherDB.endTransaction();
//        long end = System.currentTimeMillis();
//        Log.d("cityEntityCount",String.valueOf(cityEntityList.size()));
//        Log.d("saveCitysToDB time", String.valueOf(end - start));
//    }
    public void saveCitiesToDB(List<CityEntity> list){
        int i = 0;
        long start = System.currentTimeMillis();
        weatherDB.beginTransaction();
        for(CityEntity cityEntity:list){
            weatherDB.saveCity(cityEntity);
        }
        weatherDB.setTransactionSuccessful();
        weatherDB.endTransaction();
        long end = System.currentTimeMillis();
//        Log.d("saveCitiesToDb time",String.valueOf(end - start));
    }
}
