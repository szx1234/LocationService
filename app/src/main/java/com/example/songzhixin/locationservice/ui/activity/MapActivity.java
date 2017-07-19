package com.example.songzhixin.locationservice.ui.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.example.songzhixin.locationservice.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by songzhixin on 2017/6/13.
 */
public class MapActivity extends AppCompatActivity implements View.OnClickListener, AMap.OnMyLocationChangeListener, AMap.InfoWindowAdapter {

    String TAG = "MapActivity";

    OfflineMapManager manager;

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.normal)
    Button mBtn_normal;
    @BindView(R.id.night)
    Button mBtn_night;
    @BindView(R.id.satellite)
    Button mBtn_satellite;
    AMap aMap = null;

    MyLocationStyle myLocationStyle;

    @BindView(R.id.linear)
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map);
        ButterKnife.bind(this);


        mBtn_night.setOnClickListener(this);
        mBtn_normal.setOnClickListener(this);
        mBtn_satellite.setOnClickListener(this);
        linearLayout.removeView(mMapView);

        reLoad();
        initMapAndStyle();
        mMapView.onCreate(savedInstanceState);
        linearLayout.addView(mMapView);
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(15);
        aMap.moveCamera(mCameraUpdate);

    }

    private void reLoad() {
        LatLng latLng = new LatLng(34.120912, 108.832704);
        AMapOptions aMapOptions = new AMapOptions();
        aMapOptions.camera(new CameraPosition(latLng, 10f, 0, 0));
        mMapView = new MapView(this, aMapOptions);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMapView.setLayoutParams(params);
        aMap = mMapView.getMap();
    }

    private void initMapAndStyle() {
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setOnMyLocationChangeListener(this);

        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(MapActivity.this, "click" + marker.getId(), Toast.LENGTH_SHORT).show();
            }
        });
//        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Toast.makeText(MapActivity.this, marker.getId(), Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {
        Log.w(TAG, "latitude= " + location.getLatitude());
        Log.w(TAG, "longitude" + location.getLongitude());

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

    @Override
    public void onClick(View v) {
        int type;
        switch (v.getId()) {
            case R.id.normal:
                type = AMap.MAP_TYPE_NORMAL;
                break;
            case R.id.night:
                type = AMap.MAP_TYPE_NIGHT;
                break;
            case R.id.satellite:
                type = AMap.MAP_TYPE_SATELLITE;
                break;
            default:
                type = AMap.MAP_TYPE_NORMAL;
                break;
        }
        aMap.setMapType(type);
    }

    Marker marker;

    @OnClick(R.id.marker)
    public void addMarker() {
        LatLng latLng = new LatLng(34.12080, 108.832730);
//        aMap.addMarker(new MarkerOptions().position(latLng).title("学校").snippet("DefaultMarker").visible(true));

        MarkerOptions markerOption = new MarkerOptions()
                .position(latLng)
                .title("标题我是")
                .draggable(true);
        marker = aMap.addMarker(markerOption);
        aMap.setInfoWindowAdapter(this);
    }

    @OnClick(R.id.traffic)
    public void showTraffic() {

        aMap.setTrafficEnabled(!aMap.isTrafficEnabled());
    }

    @OnClick(R.id.location)
    public void location() {
        aMap.setMyLocationEnabled(!aMap.isMyLocationEnabled());
        aMap.getUiSettings().setMyLocationButtonEnabled(aMap.isMyLocationEnabled());
        if (aMap.isMyLocationEnabled()) {
            aMap.setMyLocationStyle(myLocationStyle);
            CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(15);
            aMap.moveCamera(mCameraUpdate);

        }
    }

    @OnClick(R.id.download)
    public void download() {

        if (manager == null) {
            manager = new OfflineMapManager(MapActivity.this, new OfflineMapManager.OfflineMapDownloadListener() {
                @Override
                public void onDownload(int i, int i1, String s) {
                    Log.w(TAG+"haha", "onDownload: 下载中" + s);
                    Log.w(TAG+"haha", "i = " + i);
                    Log.w(TAG+"haha", "i1 = " + i1);
                }

                @Override
                public void onCheckUpdate(boolean b, String s) {
                    Log.w(TAG, "onCheckUpdate: 检查更新中");
                }

                @Override
                public void onRemove(boolean b, String s, String s1) {
                    Log.w(TAG, "onRemove: 移除中");
                }
            });
        }
        if (manager.getDownloadingCityList().size() > 0) {
            Toast.makeText(this, "正在下载西安的地图", Toast.LENGTH_SHORT).show();
        } else {
            try {
                manager.downloadByCityName("阿勒泰地区");
            } catch (AMapException e) {
                e.printStackTrace();
            }
        }

    }

}
