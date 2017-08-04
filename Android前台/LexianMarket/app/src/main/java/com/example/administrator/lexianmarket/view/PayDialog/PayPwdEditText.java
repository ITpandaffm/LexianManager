/**
 *  Copyright 2017  Chinasofti , Inc. All rights reserved.
 */
package com.example.administrator.lexianmarket.view.PayDialog;

    import android.content.Context;
    import android.text.Editable;
    import android.text.InputFilter;
    import android.text.InputType;
    import android.text.Selection;
    import android.text.TextWatcher;
    import android.text.method.HideReturnsTransformationMethod;
    import android.text.method.PasswordTransformationMethod;
    import android.util.AttributeSet;
    import android.view.Gravity;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.inputmethod.InputMethodManager;
    import android.widget.EditText;
    import android.widget.LinearLayout;
    import android.widget.RelativeLayout;
    import android.widget.TextView;
/**
 * <p>Title: 乐鲜生活</p>
 * <p>Description: 乐鲜生活购物系统</p>
 * <p>Copyright: Copyright (c) 200x</p>
 * <p>Company: 中软国际</p>
 * @author 陈浩
 * @version 1.0
 */
public class PayPwdEditText extends RelativeLayout{

    private EditText editText; //文本编辑框
    private Context context;

    private LinearLayout linearLayout; //文本密码的文本
    private TextView[] textViews; //文本数组

    private int pwdLength = 6; //密码长度， 默认6

    private OnTextFinishListener onTextFinishListener;


    public PayPwdEditText(Context context) {
        this(context, null);
    }

    public PayPwdEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayPwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * @param bgDrawable 背景drawable
     * @param pwdLength 密码长度
     * @param splilinewidth 分割线宽度
     * @param splilinecolor 分割线颜色
     * @param pwdColor 密码字体颜色
     * @param pwdSize 密码字体大小
     */
    public void initStyle(int bgDrawable, int pwdLength,
                          float splilinewidth, int splilinecolor, int pwdColor, int pwdSize)
    {
        this.pwdLength = pwdLength;
        initEdit(bgDrawable);
        initShowInput(bgDrawable, pwdLength, splilinewidth, splilinecolor, pwdColor, pwdSize);
    }

    /**
     * 初始化编辑框
     * @param bgColor
     */
    private void initEdit(int bgColor)
    {
        editText = new EditText(context);
        editText.setBackgroundResource(bgColor);
        editText.setCursorVisible(false);
        editText.setTextSize(0);
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD
                | InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(pwdLength)});
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Editable etext = editText.getText();
                Selection.setSelection(etext, etext.length());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initDatas(s);
                if(s.length() == pwdLength)
                {
                    if(onTextFinishListener != null)
                    {
                        onTextFinishListener.onFinish(s.toString().trim());
                    }
                }
            }
        });
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        addView(editText, lp);

    }

    /**
     * @param bgColor 背景drawable
     * @param pwdLength 密码长度
     * @param slpilineWidth 分割线宽度
     * @param splilineColor 分割线颜色
     * @param pwdColor 密码字体颜色
     * @param pwdSize 密码字体大小
     */
    public void initShowInput(int bgColor, int pwdLength,
                              float slpilineWidth, int splilineColor, int pwdColor, int pwdSize)
    {
        //添加密码框父布局
        linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundResource(bgColor);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

        //添加密码框
        textViews = new TextView[pwdLength];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams params2 = new LinearLayout
                .LayoutParams(dip2px(context, slpilineWidth),LayoutParams.MATCH_PARENT);
        for(int i = 0; i < textViews.length; i++)
        {
            final int index = i;
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textViews[i] = textView;
            textViews[i].setTextSize(pwdSize);
            textViews[i].setTextColor(context.getResources().getColor(pwdColor));
            textViews[i].setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD
                    | InputType.TYPE_CLASS_NUMBER);
            linearLayout.addView(textView, params);


            if(i < textViews.length - 1)
            {
                View view = new View(context);
                view.setBackgroundColor(context.getResources().getColor(splilineColor));
                linearLayout.addView(view, params2);
            }
        }
    }

    /**
     * 是否显示明文
     * @param showPwd
     */
    public void setShowPwd(boolean showPwd) {
        int length = textViews.length;
        for(int i = 0; i < length; i++) {
            if (showPwd) {
                textViews[i].setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                textViews[i].setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }

    /**
     * 设置显示类型
     * @param type
     */
    public void setInputType(int type)
    {
        int length = textViews.length;
        for(int i = 0; i < length; i++) {
            textViews[i].setInputType(type);
        }
    }

    /**
     * 清除文本框
     */
    public void clearText()
    {
        editText.setText("");
        for(int i = 0; i < pwdLength; i++)
        {
            textViews[i].setText("");
        }
    }

    public void setOnTextFinishListener(OnTextFinishListener onTextFinishListener) {
        this.onTextFinishListener = onTextFinishListener;
    }

    /**
     * 根据输入字符，显示密码个数
     * @param s
     */
    public void initDatas(Editable s)
    {
        if(s.length() > 0)
        {
            int length = s.length();
            for(int i = 0; i < pwdLength; i++)
            {
                if(i < length)
                {
                    for(int j = 0; j < length; j++)
                    {
                        char ch = s.charAt(j);
                        textViews[j].setText(String.valueOf(ch));
                    }
                }
                else
                {
                    textViews[i].setText("");
                }
            }
        }
        else
        {
            for(int i = 0; i < pwdLength; i++)
            {
                textViews[i].setText("");
            }
        }
    }

    public String getPwdText()
    {
        if(editText != null)
            return editText.getText().toString().trim();
        return "";
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public interface OnTextFinishListener
    {
        void onFinish(String str);
    }

    public void setFocus()
    {
        editText.requestFocus();
        editText.setFocusable(true);
        showKeyBord(editText);
    }

    /**
     * 显示键盘
     * @param view
     */
    public void showKeyBord(View view)
    {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

    }

}
