package com.example.cheers.Activity.PersonActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.cheers.Adapter.MessageAdapter;
import com.example.cheers.Adapter.PersonalFragmentPagerAdapter;
import com.example.cheers.Adapter.SchoolFragmentPagerAdapter;
import com.example.cheers.Infos.Message;
import com.example.cheers.MyView.MyScrollView;
import com.example.cheers.MyView.MyViewPager;
import com.example.cheers.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalPageActivity extends AppCompatActivity {

    private MyScrollView myScrollView;
    private ImageView background;
    private ImageView avatar;
    private TextView title;
    private TextView name;
    private TextView school;
    private ImageView sex;
    private TextView age;

    RelativeLayout relativeLayout;
    ImageView back;
    ImageView add;

    private TabLayout mTabLayout;
    private MyViewPager mViewPager;
    private PersonalFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;

    private String username;
    private int imgId;

    int listLength; //chat list length
    private String url = "http://192.168.1.8:8081/chat_list?user_name=jimmy"; //network interface

    Handler handler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("listLength", listLength+"");
                    break;
            }
        }
    };


    private int imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);
        getSupportActionBar().hide();



        //get list length first
        getDataFromHttp();
        Log.d("lennnnnnnn", listLength+"");

        //bind views
        myScrollView = (MyScrollView) findViewById(R.id.scrollview);
        background = (ImageView) findViewById(R.id.personal_back);
        avatar = (ImageView) findViewById(R.id.personal_head);
        title = (TextView) findViewById(R.id.personal_title);

        name = (TextView) findViewById(R.id.personal_name);
        school = (TextView) findViewById(R.id.personal_school);
        sex = (ImageView) findViewById(R.id.personal_sex);
        age = (TextView) findViewById(R.id.personal_age);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_title_layout);
        back = (ImageView) findViewById(R.id.personal_back_arrow);
        add = (ImageView) findViewById(R.id.personal_add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // receive parameters
        Bundle bundle = getIntent().getExtras();

        username = bundle.getString("name");
        imgId = bundle.getInt("avatar");

        //avatar.setImageResource(bundle.getInt("avatar"));
        //background.setImageResource(bundle.getInt("avatar"));
        name.setText(username);
        age.setText(bundle.getInt("age") + "");
        sex.setImageResource(bundle.getInt("sex"));

        //set image styles
        Glide.with(this)
                .load(imgId)
                .bitmapTransform(new BlurTransformation(this, 10), new CenterCrop(this))
                .into(background);
        Glide.with(this)
                .load(imgId)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(avatar);



        //bind viewpager

        //bind viewpager with fragment
        mViewPager= (MyViewPager) findViewById(R.id.personal_viewPager);
        myFragmentPagerAdapter = new PersonalFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //bind viewpager with tablayout
        mTabLayout = (TabLayout) findViewById(R.id.personal_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);


        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);



        // init
        initListeners();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToHttp(); //add chat list record
                Toast.makeText(PersonalPageActivity.this, "添加成功", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getDataName() {
        return username;
    }

    public int getDataImgId() {
        return imgId;
    }

    private void initListeners() {

        ViewTreeObserver vto = background.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                background.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                imageHeight = background.getHeight();

                myScrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                        // TODO Auto-generated method stub
                        if (y <= 0) {
                            back.setColorFilter(Color.argb((int) 255, 255, 255, 255));
                            add.setColorFilter(Color.argb((int) 255, 255, 255, 255));

                            relativeLayout.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));

                            //title.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));

                            title.setTextColor(Color.argb((int) 0, 255, 255, 255));

                        } else if (y > 0 && y <= imageHeight) {
                            float scale = (float) y / imageHeight;

                            float alpha = (255 * scale);

                            back.setColorFilter(Color.argb((int) alpha, 0, 0, 0));
                            add.setColorFilter(Color.argb((int) alpha, 0, 0, 0));

                            relativeLayout.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                            //title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                            title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                        } else {
                            back.setColorFilter(Color.argb((int) 255, 0, 0, 0));
                            add.setColorFilter(Color.argb((int) 255, 0, 0, 0));

                            relativeLayout.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));

                            //title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));

                            title.setTextColor(Color.argb((int) 255, 0, 0, 0));
                        }
                    }
                });
            }
        });
    }

    private void getDataFromHttp() {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(getActivity(), "GET failed", Toast.LENGTH_SHORT).show();
                Log.d("Http GET", "GET FALIED");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    listLength = json.getInt("chat_num");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        android.os.Message message = new android.os.Message();
        message.what = 1;
        handler.sendMessage(message);

    }

    private void postDataToHttp() {

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("user_name", "Jimmy")
                .add("chat_name", username)
                .add("img_url", String.valueOf(imgId))
                .add("chat_info", "cheers bro!")
                .add("time", "10:00")
                .add("chat_id", String.valueOf((listLength + 1)))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("POST FAILED", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Response", "onResponse: " + response.body().string());
            }
        });
    }

}
