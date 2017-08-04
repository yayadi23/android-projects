package com.example.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.db.WeatherDB;
import com.example.entity.CityEntity;
import com.example.net.GetAllCitysAsyncTask;
import com.example.weather.R;

import java.util.ArrayList;
import java.util.List;

public class AddCity extends AppCompatActivity {
    //AutoCompleteTextView根据输入提示城市；后期要增加一个列表来显示已经添加的城市
    //adapter,click监听
    //增加一个acticity要在manifest文件里声明的
    //通过sharedPreferences来保存页面之间需要共享的数据
    //酷毙了！用acTv.setOnItemClickListener(new AdapterView.OnItemClickListener()监听点击item事件
    //用acTv.addTextChangedListener(new TextWatcher()监听输入事件；
    //此时可以直接在click事件中写跳转逻辑了!
    private WeatherDB weatherDB;
    private ArrayList<CityEntity> cityEntityList;
    private ArrayList<String> cityList;
    private AutoCompleteTextView acTv;
//    String[] citys = {"郑州", "开封", "洛阳", "平顶山", "漯河"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initViews(savedInstanceState);
        addCity();
    }

    @Override
    protected void onStart() {
        super.onStart();//需要判断一次是否已经存储了city数据，如果没有，才发送http请求.可以使用SharedPreference
//        if (load()) {
//            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
//            editor.putBoolean("load", false);
//            editor.commit();
//            loadCitysFromUrl();
//        }
        String cityItem;
        String cityName;
        String leaderName;
        String provinceName;
        int i = 0;
        cityList = new ArrayList<String>();
        cityEntityList = (ArrayList<CityEntity>) weatherDB.loadCitys();
        Log.d("dbCitysCount",String.valueOf(cityEntityList.size()));
        for (CityEntity cityEntity : cityEntityList) {
            cityName = cityEntity.getCityZh();
            leaderName = cityEntity.getLeaderZh();
            provinceName = cityEntity.getProvinceZh();
            if ("北京，上海，重庆，天津".contains(leaderName)) {
                if (cityName.endsWith("区")) {
                    cityItem = cityName + "," + leaderName + "市," + leaderName + "市";
                } else if ("北京，上海，重庆，天津".contains(cityName)) {
                    cityItem = cityName + "市," + leaderName + "市," + leaderName + "市";
                } else {
                    cityItem = cityName + "区，" + leaderName + "市，" + leaderName + "市";
                }
            } else {
                if(cityName.equals(leaderName)){
                    cityItem = cityName + "市," + leaderName + "市，" + provinceName + "省";
                } else {
                    cityItem = cityName + "县，" + leaderName + "市，" + provinceName + "省";
                }
            }
            cityList.add(cityItem);
            i++;
        }
        Log.d("loadedCitys count", String.valueOf(i++));
        //接下来需要将cityList这种ArrayList做适配器；
        acTv = (AutoCompleteTextView) findViewById(R.id.acTv);
        //需要将所有的city数据以什么县（其它城市）/区（北京天津上海重庆），什么市，什么省都load进来
        //先去把数据save到数据库里再说load；
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(AddCity.this, R.layout.support_simple_spinner_dropdown_item, cityList);
        acTv.setAdapter(cityAdapter);
        acTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCity = (String) adapterView.getItemAtPosition(i);
                Log.d("selectedCity", selectedCity);

//                int index1 = selectedCity.indexOf(",");
//                int index2 = selectedCity.lastIndexOf(",");
//                String sub1 = selectedCity.substring(0, index1);
//                String sub2 = selectedCity.substring(index1+1,index2-1);
//                Log.d("sub1", sub1);
//                Log.d("sub2", sub2);//这里不做具体的泛解析了，直接利用上海的数据和闵行的数据；
                String sub1 = selectedCity.substring(0,selectedCity.indexOf(",")-1);
                Intent intent = getIntent();
                intent.putExtra("cityName", sub1);
                setResult(RESULT_OK, intent);
                finish();//跳转回来用finish；
            }
        });

    }

//    public boolean load() {
//        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);//data是存储的文件名
//        return sharedPreferences.getBoolean("load", true);//如果toLoad为真，就需要下载；
//    }

    public void initVariables() {
        weatherDB = WeatherDB.getInstance(this);
    }

    public void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.acticity_addcity);


//        acTv.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                Log.d("EditableLog",editable.toString());
//            }
//        });
    }

    public void addCity() {

    }
}
