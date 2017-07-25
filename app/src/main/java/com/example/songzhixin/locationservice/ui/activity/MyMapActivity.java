package com.example.songzhixin.locationservice.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.example.songzhixin.locationservice.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songzhixin on 2017/7/16.
 */

public class MyMapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter {
    private MapView mMapView;
    private AMap mAMap;
    private UiSettings mUiSettings;
    MarkerOptions options;
    Marker marker;
    PolylineOptions options1;
    Polyline polyline;
    List<LatLng> latLngs = new ArrayList<LatLng>();
    float l = 39;

    @BindView(R.id.marker)
    Button addMarker;

    @BindView(R.id.download)
    Button drawLine;

    @BindView(R.id.location)
    Button startLocation;

    @BindView(R.id.night)
    Button btn_normal;

    @BindView(R.id.traffic)
    Button btn_tra;

    @BindView(R.id.satellite)
    Button btn_addPoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map);
        ButterKnife.bind(this);

        initView();
        initConfiguration();
        setListener();
        doBusiness();

        mMapView.onCreate(savedInstanceState);

    }
    /**
     * 初始化控件或者其他类
     */
    private void initView() {
        mMapView = (MapView) findViewById(R.id.map);
        mAMap = mMapView.getMap();
        mUiSettings = mAMap.getUiSettings();
    }

    /**
     * 初始化配置
     */
    private void initConfiguration() {
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
//        mAMap.setMyLocationEnabled(true);
    }


    /**
     * 设置各种监听器
     */
    private void setListener() {
        addMarker.setOnClickListener(this);
        drawLine.setOnClickListener(this);
        btn_normal.setOnClickListener(this);
        btn_tra.setOnClickListener(this);
        btn_addPoint.setOnClickListener(this);
        startLocation.setOnClickListener(this);
        mAMap.setOnMarkerClickListener(this);
        mAMap.setOnInfoWindowClickListener(this);
        mAMap.setInfoWindowAdapter(this);

    }

    /**
     * 处理业务逻辑
     */
    private void doBusiness() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.marker:
                LatLng latLng = new LatLng(39.906901, 116.397972);
                options = new MarkerOptions().position(latLng).title("北*京").draggable(true);
                marker = mAMap.addMarker(options);
                break;
            case R.id.download:

//                options.position();
//                marker.setPosition(new LatLng(39.999, 116.397972));
                latLngs.add(new LatLng(l, 116));
                latLngs.add(new LatLng(l, 117));
//                latLngs.add(new LatLng(l+1, 118));
//                if(options1 == null)
                options1 = new PolylineOptions().
                        addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1));
                if (l == 39)
                    polyline = mAMap.addPolyline(options1);
//                mAMap.line
//                polyline.setPoints();
//                options1.add(new LatLng(l++, 118));
//                latLngs.add(new LatLng(l, 117));
//                polyline.setPoints(latLngs);
//                l
//                options1.addAll(latLngs);
//                l += 1;
                break;
            case R.id.night:
//                latLngs = new ArrayList<>();
                latLngs.add(new LatLng(++l, 117));
                latLngs.add(new LatLng(++l, 118));

                polyline.setPoints(latLngs);
                break;
            case R.id.satellite:
                latLngs.add(new LatLng(++l, 118));
                break;
            case R.id.traffic:
//                if (polyline.isVisible())
                 polyline.setVisible(!polyline.isVisible());
            case R.id.location:
                openLocation();
                break;
        }
    }

    private void openLocation() {
        AMapLocationClient client = new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setInterval(2000);
        option.setNeedAddress(true);
        option.setHttpTimeOut(30 * 1000);
        option.setLocationCacheEnable(false);
        client.setLocationOption(option);
        client.startLocation();
        client.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        Log.w("我的天", aMapLocation.getLocationType() + "");//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        Log.w("我的天", aMapLocation.getLatitude() + "");
                        Log.w("我的天", aMapLocation.getLongitude() + "");
                        Log.w("我的天", aMapLocation.getAccuracy() + "");
                        Log.w("我的天", aMapLocation.getAddress() + "");
                        Log.w("我的天", aMapLocation.getCountry() + "");
                        Log.w("我的天", aMapLocation.getProvince() + "");
                        Log.w("我的天", aMapLocation.getCity() + "");
                        Log.w("我的天", aMapLocation.getDistrict() + "");
                        Log.w("我的天", aMapLocation.getStreet() + "");
                        Log.w("我的天", aMapLocation.getStreetNum() + "");
                        Log.w("我的天", aMapLocation.getCityCode() + "");
                        Log.w("我的天", aMapLocation.getAdCode() + "");
                        Log.w("我的天", aMapLocation.getAoiName() + "");
                        Log.w("我的天", aMapLocation.getBuildingId() + "");
                        Log.w("我的天", aMapLocation.getFloor() + "");


                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "infowindow clicked" + marker.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (!marker.isInfoWindowShown()) {
            marker.showInfoWindow();
        } else {
            marker.hideInfoWindow();
            Toast.makeText(this, "hide", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = LayoutInflater.from(this).inflate(R.layout.info_window, null);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
