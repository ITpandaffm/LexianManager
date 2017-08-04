/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.lexianmarket.R;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 王子龙
 * @version 1.0
 */
public class ForgetActivity extends BaseActivity {
  private Button forgetNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        forgetNext =(Button) findViewById(R.id.forget_next);
        forgetNext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent it=new Intent(ForgetActivity.this,ForgetNextActivity.class);
                startActivity(it);
            }
        });
    }
}
